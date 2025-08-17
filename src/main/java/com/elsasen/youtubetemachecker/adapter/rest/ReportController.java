package com.elsasen.youtubetemachecker.adapter.rest;

import com.elsasen.youtubetemachecker.app.api.report.GetAllReportsInbound;
import com.elsasen.youtubetemachecker.app.impl.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin(origins = "https://seny-pnz2003.fvds.ru")
@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final GetAllReportsInbound getAllReportsInbound;

    @GetMapping
    public ResponseEntity<byte[]> getReports() throws IOException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reports.zip")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(getAllReportsInbound.execute());
    }
}
