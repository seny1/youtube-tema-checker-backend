package com.elsasen.youtubetemachecker.app.impl.report;

import com.elsasen.youtubetemachecker.domain.Channel;
import com.elsasen.youtubetemachecker.domain.Client;
import com.elsasen.youtubetemachecker.domain.Video;
import lombok.RequiredArgsConstructor;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class ReportService {
    public ByteArrayOutputStream getZipOfReports(List<Client> clients) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (Client client : clients) {
                List<Channel> channels = client.getChannels();
                Set<Month> months = channels.stream()
                        .flatMap(channel -> channel.getVideos().stream())
                        .map(Video::getCreatedAt)
                        .map(LocalDateTime::getMonth)
                        .collect(Collectors.toSet());
                try (Workbook workbook = new XSSFWorkbook()) {
                    for (Month month : months) {
                        Sheet sheet = workbook.createSheet(month.getDisplayName(TextStyle.SHORT, Locale.UK));
                        Row channelRow = sheet.createRow(0);
                        CreationHelper creationHelper = workbook.getCreationHelper();
                        for (int i = 0; i < channels.size(); i++) {
                            Channel channel = channels.get(i);
                            Cell channelCell = channelRow.createCell(i);
                            channelCell.setCellValue(channel.getTitle());
                            Hyperlink channelLink = creationHelper.createHyperlink(HyperlinkType.URL);
                            channelLink.setAddress(channel.getLink());
                            channelCell.setHyperlink(channelLink);

                            List<Video> videos = channel.getVideos().stream()
                                    .filter(video -> video.getCreatedAt().getMonth().equals(month))
                                    .toList();
                            channelCell.setCellStyle(getChannelCellStyle(workbook, videos.size()));

                            for (int j = 0; j < videos.size(); j++) {
                                Video video = videos.get(j);
                                Row videoRow = sheet.createRow(j + 1);
                                Cell videoCell = videoRow.createCell(i);
                                videoCell.setCellValue(video.getTitle());
                                Hyperlink videoLink = creationHelper.createHyperlink(HyperlinkType.URL);
                                videoLink.setAddress(video.getLink());
                                videoCell.setHyperlink(videoLink);
                                videoCell.setCellStyle(getVideoCellStyle(workbook));
                            }
                        }
                        formatSize(sheet);
                    }
                    ByteArrayOutputStream excelOut = new ByteArrayOutputStream();
                    workbook.write(excelOut);
                    ZipEntry zipEntry = new ZipEntry(client.getNickname() + "_report.xlsx");
                    zos.putNextEntry(zipEntry);
                    zos.write(excelOut.toByteArray());
                    zos.closeEntry();
                }
            }
        }
        return baos;
    }

    // =================================================================================================================
    // Implementation
    // =================================================================================================================

    private void formatSize(Sheet sheet) {
        int numberOfColumns = sheet.getRow(0).getLastCellNum();
        for (int c = 0; c < numberOfColumns; c++) {
            sheet.autoSizeColumn(c);
        }
    }

    private CellStyle getChannelCellStyle(Workbook workbook, int videosCount) {
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setUnderline(Font.U_NONE);
        style.setFont(font);

        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        if (videosCount >= 25) {
            style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        } else if (videosCount >= 18) {
            style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        } else {
            style.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        }
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    private CellStyle getVideoCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setUnderline(Font.U_SINGLE);
        font.setColor(IndexedColors.BLUE.getIndex());
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);

        style.setWrapText(true);

        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }
}
