package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.log.LogCounterDTO;
import br.com.agidoc.agiDoc.dto.log.LogDTO;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.log.Log;
import br.com.agidoc.agiDoc.model.log.LogType;
import br.com.agidoc.agiDoc.repository.LogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;
    private final ObjectMapper objectMapper;

    private String NOT_FOUND_MESSAGE = "User id not found";

    public void create(Log log) {
        logRepository.save(log);
    }

    public List<LogDTO> listAllLogs() {
        return logRepository.findAll().stream().map(log -> objectMapper.convertValue(log, LogDTO.class)).collect(Collectors.toList());
    }

    public Page<LogDTO> listAllLogsPageable(Pageable pageable) {
        Page<LogDTO> all = logRepository.findAll(pageable).map(log -> objectMapper.convertValue(log, LogDTO.class));
        return all;
    }

    public List<LogDTO> listAllLogsByLogType(LogType logType) {
        return logRepository.findAllByLogType(logType).stream().map(log -> objectMapper.convertValue(log, LogDTO.class)).collect(Collectors.toList());
    }

    public List<LogCounterDTO> groupByLogTypeAndCount() {
        return logRepository.groupByLogTypeAndCount().stream().map(log -> {
            return new LogCounterDTO(log.getLogType(), log.getQuantity());
        }).collect(Collectors.toList());
    }

    public List<LogCounterDTO> groupByDateAndCountLogType(String date) {
        String dateRegex = ".*" + date + ".*";
        return logRepository.findAllByDateAndCountLogType(dateRegex).stream().map(log -> {
            return new LogCounterDTO(log.getLogType(), log.getQuantity());
        }).collect(Collectors.toList());
    }

    public List<LogDTO> listAllByDate(String date) throws Exception {
        LocalDate dateAtual = LocalDate.now();
        LocalDate dateProcurada = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

        if (dateProcurada.isAfter(dateAtual)) {
            throw new Exception("Não há logs para dates futuras!");
        }

        return logRepository.findAllByDateContains(date).stream().map(log -> objectMapper.convertValue(log, LogDTO.class)).collect(Collectors.toList());
    }

    public LogDTO listById(String id) throws Exception {
        return objectMapper.convertValue(returnedById(id), LogDTO.class);
    }

    public Integer countLogsByDate(String date) {
        return logRepository.countAllByDateContains(date);
    }

    public Log returnedById(String id) throws RegraDeNegocioException {
        return logRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException(NOT_FOUND_MESSAGE));
    }

    public List<LogDTO> findAllAfterDate(String date) {
        return logRepository.findAllAfterDate(date).stream().map(obj -> objectMapper.convertValue(obj, LogDTO.class)).collect(Collectors.toList());
    }
}
