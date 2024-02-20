package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.log.LogDTO;
import br.com.agidoc.agiDoc.dto.processStatusReport.ProcessStatusReportDTO;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.log.Log;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import br.com.agidoc.agiDoc.model.report.ProcessStatusReport;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.repository.ProcessStatusReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessStatusReportService {

    private String NOT_FOUND_MESSAGE = "Process Status Report id not found";

    private final ProcessStatusReportRepository processStatusReportRepository;
    private final ObjectMapper objectMapper;

    public void generateProcessStatusReport(Process process, Integer statusWanted, User user) {

        String oldStatus = String.valueOf(process.getProcessStatus());
        ProcessStatusReport processStatusReport = new ProcessStatusReport();
        processStatusReport.setUserUsername(user.getUsername());
        processStatusReport.setProcessNumber(process.getProcessNumber());
        processStatusReport.setOldStatus(String.valueOf(process.getProcessStatus()));
        processStatusReport.setNewStatus(String.valueOf(ProcessStatus.ofType(statusWanted)));
        processStatusReport.setDate(String.valueOf(LocalDateTime.now()));
        processStatusReport.setDescription("Process status changed from " + oldStatus + " to " +
                ProcessStatus.ofType(statusWanted) + " in " + LocalDateTime.now()
        );
        processStatusReportRepository.save(processStatusReport);
    }

    public List<ProcessStatusReportDTO> listAllLogs() {
        return processStatusReportRepository.findAll().stream().map(log ->
                objectMapper.convertValue(log, ProcessStatusReportDTO.class)).collect(Collectors.toList());
    }

    public ProcessStatusReportDTO listById(String id) throws Exception {
        return objectMapper.convertValue(returnedById(id), ProcessStatusReportDTO.class);
    }

    public ProcessStatusReport returnedById(String id) throws RegraDeNegocioException {
        return processStatusReportRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));
    }
}
