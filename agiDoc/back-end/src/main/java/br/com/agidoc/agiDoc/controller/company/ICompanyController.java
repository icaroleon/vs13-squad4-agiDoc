package br.com.agidoc.agiDoc.controller.company;

import br.com.agidoc.agiDoc.dto.company.CompanyCreateDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ICompanyController {
    @Operation(summary = "Create company", description = "Create a company in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Creates a company"),
                    @ApiResponse(responseCode = "400", description = "Invalid data"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "500", description = "An exception occurred")
            }
    )
    @PostMapping
    ResponseEntity<CompanyDTO> create (@Valid @RequestBody CompanyCreateDTO companyCreateDTO) throws Exception;

    @Operation(summary = "List all companies", description = "List all companies from the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lists all companies"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "404", description = "Companies not found"),
                    @ApiResponse(responseCode = "500", description = "An exception occurred")
            }
    )
    @GetMapping
    ResponseEntity<List<CompanyDTO>> list() throws Exception;

    @Operation(summary = "List active companies", description = "List all active companies in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lists all active companies"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "404", description = "Companies not found"),
                    @ApiResponse(responseCode = "500", description = "An exception occurred")
            }
    )
    @GetMapping("/actives")
    ResponseEntity<List<CompanyDTO>> listActives() throws Exception;

    @Operation(summary = "List inactive companies", description = "List all inactive companies in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lists all inactive companies"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "404", description = "Companies not found"),
                    @ApiResponse(responseCode = "500", description = "An exception occurred")
            }
    )
    @GetMapping("/inactives")
    ResponseEntity<List<CompanyDTO>> listInactives() throws Exception;

    @Operation(summary = "List a company by id", description = "List only one company from the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lists a company"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "404", description = "Company not found"),
                    @ApiResponse(responseCode = "500", description = "An exception occurred")
            }
    )
    @GetMapping("/{idCompany}")
    ResponseEntity<CompanyDTO> getById(@NotNull @PathVariable("idCompany") Integer id) throws Exception;


    @Operation(summary = "Update company", description = "Update a company in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Updates a company"),
                    @ApiResponse(responseCode = "400", description = "Invalid data"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "404", description = "Company not found"),
                    @ApiResponse(responseCode = "500", description = "An exception occurred")
            }
    )
    @PutMapping("/{idCompany}")
    ResponseEntity<CompanyDTO> update(@NotNull @PathVariable("idCompany") Integer id, @Valid @RequestBody CompanyUpdateDTO companyUpdateDTO) throws Exception;

    @Operation(summary = "Delete a company by id", description = "Delete only one company from the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deletes a company"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "404", description = "Company not found"),
                    @ApiResponse(responseCode = "500", description = "An exception occurred")
            }
    )
    @DeleteMapping("/{idCompany}")
    ResponseEntity<Void> delete(@NotNull @PathVariable("idCompany") Integer id) throws Exception;
}
