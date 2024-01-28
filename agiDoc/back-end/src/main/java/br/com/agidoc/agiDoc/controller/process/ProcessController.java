package br.com.agidoc.agiDoc.controller.process;

import br.com.agidoc.agiDoc.dto.process.ProcessCreateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.service.ProcessService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/process")
@AllArgsConstructor
@RestController
@Validated
public class ProcessController {

    private final ProcessService processService;

    @GetMapping
    public ResponseEntity<List<ProcessDTO>> list() throws Exception {
        return new ResponseEntity<>(this.processService.list(), HttpStatus.OK);
    }

    @GetMapping("/{idProcess}")
    public ResponseEntity<ProcessDTO> findById(@NotNull @PathVariable("idProcess") Integer idProcess) throws Exception {
        return new ResponseEntity<>(this.processService.findById(idProcess), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProcessDTO> create(@Valid @RequestBody ProcessCreateDTO processCreateDto) {
        return new ResponseEntity<>(this.processService.create(processCreateDto), HttpStatus.OK);
    }

    @PutMapping("/{idProcess}")
    public ResponseEntity<ProcessDTO> update(@NotNull @PathVariable("idProcess") Integer idProcess, @Valid @RequestBody ProcessCreateDTO processCreateDTO) throws Exception {
        return new ResponseEntity<>(this.processService.update(idProcess, processCreateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idProcess}")
    public ResponseEntity<Void> delete(@NotNull @PathVariable("idProcess") Integer idProcess) throws Exception {
        this.processService.delete(idProcess);
        return ResponseEntity.ok().build();
    }

}

