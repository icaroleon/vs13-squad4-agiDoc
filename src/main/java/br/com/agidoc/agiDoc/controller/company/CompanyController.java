package br.com.agidoc.agiDoc.controller.company;

import br.com.agidoc.agiDoc.dto.company.CompanyCreateDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyUpdateDTO;
import br.com.agidoc.agiDoc.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Company", description = "CRUD of company")
@RequestMapping("/company")
@Validated
@Slf4j
public class CompanyController implements ICompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyDTO> create (@Valid @RequestBody CompanyCreateDTO companyCreateDTO) throws Exception {
        log.info("Empresa criada.");

        return new ResponseEntity<>(companyService.create(companyCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> list() throws Exception {
        log.info("Empresas listadas.");
        return new ResponseEntity<>(companyService.list(), HttpStatus.OK);
    }

    @GetMapping("/actives")
    public ResponseEntity<List<CompanyDTO>> listActives() throws Exception {
        log.info("Empresas listadas.");
        return new ResponseEntity<>(companyService.listByStatusActive(), HttpStatus.OK);
    }

    @GetMapping("/inactives")
    public ResponseEntity<List<CompanyDTO>> listInactives() throws Exception {
        log.info("Empresas listadas.");
        return new ResponseEntity<>(companyService.listByStatusInactive(), HttpStatus.OK);
    }

    @GetMapping("/{idCompany}")
    public ResponseEntity<CompanyDTO> getById(@NotNull @PathVariable("idCompany") Integer id) throws Exception {
        log.info("Empresa listada por id.");
        return new ResponseEntity<>(companyService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{idCompany}")
    public ResponseEntity<CompanyDTO> update(@NotNull @PathVariable("idCompany") Integer id, @Valid @RequestBody CompanyUpdateDTO companyUpdateDTO) throws Exception {
        log.info("Empresa atualizada.");
        return new ResponseEntity<>(companyService.update(id, companyUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idCompany}")
    public ResponseEntity<Void> delete(@NotNull @PathVariable("idCompany") Integer id) throws Exception {
        log.info("Empresa deletada.");
        companyService.delete(id);
        return ResponseEntity.ok().build();
    }
}
