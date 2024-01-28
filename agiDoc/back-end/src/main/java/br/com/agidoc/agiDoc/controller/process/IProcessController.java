package br.com.agidoc.agiDoc.controller.process;

import br.com.agidoc.agiDoc.dto.process.ProcessCreateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.process.Process;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IProcessController {

    @Operation(summary = "List Processes", description = "Retrieve a list of all processes")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of process list"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception")
            }
    )
    ResponseEntity<List<ProcessDTO>> list() throws Exception;

    @Operation(summary = "Find Process by ID", description = "Retrieve a single process by its ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of the process"),
                    @ApiResponse(responseCode = "404", description = "Process with the given ID not found"),
                    @ApiResponse(responseCode = "500", description = "Unhandled exception")
            }
    )
    @GetMapping("/{idProcess}")
    ResponseEntity<ProcessDTO> findById(@NotNull @PathVariable("idProcess") Integer idProcess) throws Exception;

    @Operation(summary = "Create Process", description = "Create a new process in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Process successfully created"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Invalid data provided, check the error in response"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception")
            }
    )
    ResponseEntity<ProcessDTO> create(@Valid @RequestBody ProcessCreateDTO processCreateDto) throws Exception;

    @Operation(summary = "Update Process", description = "Update an existing process with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Process successfully updated"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Invalid data or ID provided, check the error in response"),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)), description = "Process with the given ID not found"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception")
            }
    )
    ResponseEntity<ProcessDTO> update(@NotNull @PathVariable Integer idProcess, @Valid @RequestBody ProcessUpdateDTO processUpdateDTO) throws Exception;

    @Operation(summary = "Delete Process", description = "Delete a process from the database using its ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Process successfully deleted"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Invalid ID provided, check the error in response"),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)), description = "Process with the given ID not found"),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception")
            }
    )
    ResponseEntity<Void> delete(@NotNull @PathVariable("idProcess") Integer idProcess) throws Exception;
}
