package br.com.agidoc.agiDoc.controller.process;

import br.com.agidoc.agiDoc.dto.process.ProcessCreateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessUpdateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessesDocumentsDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.service.ProcessService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/process")
@Tag(name = "Process", description = "CRUD of process")
@AllArgsConstructor
@RestController
@Validated
//TODO IMPLEMENTAR PROCESS CONTROLLER
public class ProcessController{

    private final ProcessService processService;

    @GetMapping
    public ResponseEntity<List<ProcessDTO>> list() throws Exception {
        return new ResponseEntity<>(processService.list(), HttpStatus.OK);
    }

    @GetMapping("/{idProcess}")
    public ResponseEntity<ProcessDTO> findById(@NotNull @PathVariable("idProcess") Integer idProcess) throws Exception {
        return new ResponseEntity<>(processService.findById(idProcess), HttpStatus.OK);
    }

    @PostMapping("/{idCompany}")
    public ResponseEntity<ProcessDTO> create(@NotNull @PathVariable ("idCompany") Integer idCompany,
                                                 @Valid @RequestBody ProcessCreateDTO processCreateDto) throws Exception {
        return new ResponseEntity<>(this.processService.create(idCompany, processCreateDto), HttpStatus.OK);
    }

    @PutMapping("/{idProcess}")
    public ResponseEntity<ProcessDTO> update(@NotNull @PathVariable("idProcess") Integer idProcess,
                                             @Valid @RequestBody ProcessUpdateDTO processUpdateDTO) throws Exception {
        return new ResponseEntity<>(this.processService.update(idProcess, processUpdateDTO), HttpStatus.OK);
    }

    @PutMapping("/{idProcess}/{status}")
    public ResponseEntity<ProcessDTO> setStatus(@NotNull @PathVariable("idProcess") Integer idProcess,
                                              @NotNull @PathVariable("status") Integer statusWanted) throws Exception {
        return new ResponseEntity<>(processService.setStatus(idProcess, statusWanted), HttpStatus.OK);
    }
}

