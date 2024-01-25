package br.com.agidoc.agiDoc.controller.contact;

import br.com.agidoc.agiDoc.model.contact.Contact;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IContactController {
    @Operation(summary = "Create Contact", description = "Create a contact in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return created contact."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    Contact create(@RequestBody Contact contact) throws Exception;

    @Operation(summary = "List Contacts", description = "List all contacts in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return a list of contacts."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    List<Contact> list() throws Exception;

    @Operation(summary = "Update Contact", description = "Update a contact in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return updated contact."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Contact not found."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    Contact update(@PathVariable Integer id, @RequestBody Contact contact) throws Exception;

    @Operation(summary = "Delete Contact", description = "Delete a contact in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contact deleted successfully."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Contact not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    void delete(@PathVariable Integer id) throws Exception;
}
