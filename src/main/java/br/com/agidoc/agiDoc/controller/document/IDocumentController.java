package br.com.agidoc.agiDoc.controller.document;

import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.document.Document;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IDocumentController {

    @Operation(summary = "List Documents", description = "Retrieve a list of all documents")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of document list."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    List<DocumentDTO> list() throws Exception;

    @Operation(summary = "Create Document", description = "Create a new document in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return created document."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Invalid data provided, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    Document create(@Valid @RequestBody Document document) throws DatabaseException;

    @Operation(summary = "Update Document", description = "Update an existing document with a given ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Document successfully updated."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Invalid data or ID provided, check the error in response."),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)), description = "Document with the given ID not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    Document update(@PathVariable Integer id, @RequestBody Document document) throws DatabaseException;

    @Operation(summary = "Delete Document", description = "Delete a document from the database using its ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Document successfully deleted."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Invalid ID provided, check the error in response."),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)), description = "Document with the given ID not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    void delete(@PathVariable Integer id) throws DatabaseException;
}
