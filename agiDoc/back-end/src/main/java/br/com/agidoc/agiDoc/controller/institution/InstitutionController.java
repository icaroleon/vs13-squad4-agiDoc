package br.com.agidoc.agiDoc.controller.institution;

import br.com.agidoc.agiDoc.dto.institution.InstitutionCreateDTO;
import br.com.agidoc.agiDoc.dto.institution.InstitutionDTO;
import br.com.agidoc.agiDoc.service.InstitutionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Institution", description = "CRUD of institutions")
@RequestMapping("/institution")
public class InstitutionController implements IInstitutionController{
    private final InstitutionService institutionService;
    @PostMapping()
    @Override
    public ResponseEntity<InstitutionDTO> create(@Valid @RequestBody InstitutionCreateDTO institutionCreateDTO) throws Exception {
        log.info("Creating Institution.");
        ResponseEntity<InstitutionDTO> responseEntity = new ResponseEntity<>(this.institutionService.create(institutionCreateDTO), HttpStatus.CREATED);
        log.info("Institution created success.");
        return responseEntity;
    }

    @Override
    @PutMapping("{idInstitution}")
    public ResponseEntity<InstitutionDTO> update(@PathVariable("idInstitution") Integer idInstitution, @Valid @RequestBody InstitutionCreateDTO institutionCreateDTO) throws Exception {
        log.info("Updating Institution.");
        ResponseEntity<InstitutionDTO> responseEntity = new ResponseEntity<>(this.institutionService.update(idInstitution, institutionCreateDTO), HttpStatus.OK);
        log.info("Institution updated success.");
        return responseEntity;
    }

    @GetMapping
    @Override
    public ResponseEntity<ArrayList<InstitutionDTO>> listAll() throws Exception {
        log.info("listing all institutions.");
        ResponseEntity<ArrayList<InstitutionDTO>> responseEntity = new ResponseEntity<>(this.institutionService.listAll(), HttpStatus.OK);
        log.info("listed all institutions success.");
        return responseEntity;
    }

    @DeleteMapping("{idInstitution}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable("idInstitution") Integer idInstitution) throws Exception {
        log.info("Deleting institution.");
        this.institutionService.delete(idInstitution);
        log.info("Institution deleted success.");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
