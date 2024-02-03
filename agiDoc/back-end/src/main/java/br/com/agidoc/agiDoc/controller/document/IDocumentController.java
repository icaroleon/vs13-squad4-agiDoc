package br.com.agidoc.agiDoc.controller.document;

import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IDocumentController {

    @Operation(summary = "List Documents", description = "Retrieve a list of all documents")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of document list."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<List<DocumentDTO>> list() throws Exception ;

    @Operation(summary = "Find Process by ID", description = "Retrieve a single process by its ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of the process"),
                    @ApiResponse(responseCode = "404", description = "Process with the given ID not found"),
                    @ApiResponse(responseCode = "500", description = "Unhandled exception")
            }
    )
    ResponseEntity<DocumentDTO> findById(@NotNull @PathVariable("idProcess") Integer idProcess) throws Exception;

    @Operation(summary = "Create Document", description = "Create a new document in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return created document."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Invalid data provided, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<DocumentDTO> create(@NotNull @PathVariable("idProcess") Integer idProcess, @Valid @RequestBody DocumentCreateDTO documentCreateDTO) throws Exception;

    @Operation(summary = "Update Document", description = "Update an existing document with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Document successfully updated."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Invalid data or ID provided, check the error in response."),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)), description = "Document with the given ID not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<DocumentDTO> update(@NotNull @PathVariable("idDocument") Integer idDocument, @Valid @RequestBody DocumentUpdateDTO documentUpdateInfosDTO) throws Exception;

    @Operation(summary = "Delete Document", description = "Delete a document from the database using its ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Document successfully deleted."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Invalid ID provided, check the error in response."),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)), description = "Document with the given ID not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<String> delete(@PathVariable("idDocument") Integer idDocument) throws Exception;
}
