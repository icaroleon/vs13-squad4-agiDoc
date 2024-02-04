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
    @Operation(summary = "Criar empresa", description = "Cria uma empresa no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cria um empresa"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<CompanyDTO> create (@Valid @RequestBody CompanyCreateDTO companyCreateDTO) throws Exception;

    @Operation(summary = "Listar empresa", description = "Lista todas as empresa do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todas as empresas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Empresas não encontradas"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<CompanyDTO>> list() throws Exception;

    @Operation(summary = "Listar empresas ativas", description = "Lista todas as empresa ativas no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todas as empresas ativas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Empresas não encontradas"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/actives")
    public ResponseEntity<List<CompanyDTO>> listAtivos() throws Exception;

    @Operation(summary = "Listar empresas inativas", description = "Lista todas as empresa inativas no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todas as empresas inativas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Empresas não encontradas"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/inactives")
    public ResponseEntity<List<CompanyDTO>> listInativos() throws Exception;

    @Operation(summary = "Listar uma empresa por id", description = "Lista apenas uma empresa do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista uma empresa"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idCompany}")
    ResponseEntity<CompanyDTO> getById(@NotNull @PathVariable("idCompany") Integer id) throws Exception;


    @Operation(summary = "Atualiza empresa", description = "Atualiza uma empresa no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza uma empresa"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idCompany}")
    ResponseEntity<CompanyDTO> update(@NotNull @PathVariable("idCompany") Integer id, @Valid @RequestBody CompanyUpdateDTO companyUpdateDTO) throws Exception;

    @Operation(summary = "Deletar uma empresa por id", description = "Deleta apenas uma empresa do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta uma empresa"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idCompany}")
    ResponseEntity<Void> delete(@NotNull @PathVariable("idCompany") Integer id) throws Exception;
}
