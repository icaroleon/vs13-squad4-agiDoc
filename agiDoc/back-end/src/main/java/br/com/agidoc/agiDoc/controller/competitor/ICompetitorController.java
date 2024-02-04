package br.com.agidoc.agiDoc.controller.competitor;

import br.com.agidoc.agiDoc.dto.competitor.CompetitorCreateDTO;
import br.com.agidoc.agiDoc.dto.competitor.CompetitorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ICompetitorController {
    @Operation(summary = "Criar competidores", description = "Cria um competidor no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cria um competidor"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<CompetitorDTO> create (@Valid @RequestBody CompetitorCreateDTO competitorCreateDTO) throws Exception;

    @Operation(summary = "Listar competidores", description = "Lista todos os competidores do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista todos os competidores"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Competidores não encontrados"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<CompetitorDTO>> list() throws Exception;

    @Operation(summary = "Listar um competidor por id", description = "Lista apenas um competidor do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista um competidor"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Competidor não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idCompetitor}")
    ResponseEntity<CompetitorDTO> getById(@NotNull @PathVariable("idCompetitor") Integer id) throws Exception;


    @Operation(summary = "Atualiza competidor", description = "Atualiza um competidor no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualiza um competidor"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Competidor não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idCompetitor}")
    ResponseEntity<CompetitorDTO> update(@NotNull @PathVariable("idCompetitor") Integer id, @Valid @RequestBody CompetitorCreateDTO competitorCreateDTO) throws Exception;

    @Operation(summary = "Deletar um competidor por id", description = "Deleta apenas um competidor do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta um competidor"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "404", description = "Competidor não encontrado"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idCompetitor}")
    ResponseEntity<Void> delete(@NotNull @PathVariable("idCompetitor") Integer id) throws Exception;
}
