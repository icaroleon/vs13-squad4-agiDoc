package br.com.agidoc.agiDoc.controller.log;

import br.com.agidoc.agiDoc.dto.log.LogCounterDTO;
import br.com.agidoc.agiDoc.dto.log.LogDTO;
import br.com.agidoc.agiDoc.model.log.LogType;
import br.com.agidoc.agiDoc.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class LogController {
    private final LogService logService;

    @GetMapping()
    public List<LogDTO> list() {
        return logService.listAllLogs();
    }

    @GetMapping("/pageable")
    public Page<LogDTO> listPageable(@PageableDefault(size = 10, page = 0, sort = {"date"}) Pageable pageable) {
        return logService.listAllLogsPageable(pageable);
    }

    @GetMapping("/by-id")
    public LogDTO listById(String id) throws Exception {
        return logService.listById(id);
    }

    @GetMapping("/by-tipolog")
    public List<LogDTO> listByLogType(LogType logType) {
        return logService.listAllLogsByLogType(logType);
    }

    @GetMapping("/group-by-tipolog-and-count")
    public List<LogCounterDTO> groupByLogTypeAndCount() {
        return logService.groupByLogTypeAndCount();
    }

    @GetMapping("/group-by-date-and-count-tipolog")
    public List<LogCounterDTO> groupByDateAndCountLogType(String date) {
        return logService.groupByDateAndCountLogType(date);
    }

    @GetMapping("/find-all-by-date")
    public List<LogDTO> listByDate(String date) throws Exception {
        return logService.listAllByDate(date);
    }

    @GetMapping("/count-all-by-date")
    public Integer countLogsByDate(String date) {
        return logService.countLogsByDate(date);
    }

    @GetMapping("/count-all-of-today")
    public Integer countLogsOfToday() {
        String currentDate = LocalDate.now().toString();
        return logService.countLogsByDate(currentDate);
    }

    @GetMapping("/return-all-after-date")
    public List<LogDTO> returnAllAfterDate(String date) {
        return logService.findAllAfterDate(date);
    }
}
