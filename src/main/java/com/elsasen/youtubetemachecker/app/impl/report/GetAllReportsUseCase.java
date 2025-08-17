package com.elsasen.youtubetemachecker.app.impl.report;

import com.elsasen.youtubetemachecker.app.api.client.repo.ClientRepository;
import com.elsasen.youtubetemachecker.app.api.report.GetAllReportsInbound;
import com.elsasen.youtubetemachecker.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllReportsUseCase implements GetAllReportsInbound {
    private final ClientRepository clientRepository;
    private final ReportService reportService;

    @Override
    public byte[] execute() throws IOException {
        List<Client> clients = clientRepository.findAll();
        return reportService.getZipOfReports(clients).toByteArray();
    }
}
