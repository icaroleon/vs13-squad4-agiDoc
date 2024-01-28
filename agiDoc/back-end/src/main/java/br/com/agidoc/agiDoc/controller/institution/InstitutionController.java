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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Institution", description = "CRUD of institutions")
@RequestMapping("/institution")
public class InstitutionController implements IInstitutionController{
    private InstitutionService institutionService;
    @GetMapping
    @Override
    public ResponseEntity<InstitutionDTO> create(InstitutionCreateDTO institutionCreateDTO) throws Exception {
        log.info("Creating Institution.");
        ResponseEntity<InstitutionDTO> responseEntity = new ResponseEntity<>(this.institutionService.create(institutionCreateDTO), HttpStatus.CREATED);
        log.info("Institution created.");
        return responseEntity;
    }

    @Override
    public ResponseEntity<InstitutionDTO> update(Integer idInstitution, InstitutionCreateDTO institutionCreateDTO) throws Exception {
        log.info("Updating Institution.");
        ResponseEntity<InstitutionDTO> responseEntity = new ResponseEntity<>(this.institutionService.update(idInstitution, institutionCreateDTO), HttpStatus.OK);
        log.info("Institution updated.");
        return responseEntity;
    }

    @Override
    public ResponseEntity<ArrayList<InstitutionDTO>> listAll() throws Exception {
        log.info("listing all institutions.");
        ResponseEntity<ArrayList<InstitutionDTO>> responseEntity = new ResponseEntity<>(this.institutionService.listAll(), HttpStatus.OK);
        log.info("listed all institutions.");
        return responseEntity;
    }

    @Override
    public ResponseEntity<Void> delete(Integer idInstitution) throws Exception {
        log.info("Deleting institution.");
        this.institutionService.delete(idInstitution);
        log.info("Institution deleted.");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
