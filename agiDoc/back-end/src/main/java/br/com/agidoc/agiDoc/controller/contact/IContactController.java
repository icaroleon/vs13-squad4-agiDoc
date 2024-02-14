package br.com.agidoc.agiDoc.controller.contact;

import br.com.agidoc.agiDoc.dto.contact.ContactCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IContactController {
    @Operation(summary = "List Contacts", description = "List all contacts in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return a list of contacts."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<List<ContactDTO>> listAll() throws Exception;


    @Operation(summary = "Returns by id.", description = "Returns the specific contact by id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Returns the contact with that id."),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this feature."),
                    @ApiResponse(responseCode = "500", description = "An exception was thrown.")
            }
    )
    @GetMapping("/byid")
    public ResponseEntity<ContactDTO> listOne(@RequestParam("idContact") Integer idContact) throws Exception;
    @Operation(summary = "Create new contact", description = "Create a new contact and save it to the bank.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Returns the data of the contact that was created."),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this feature."),
                    @ApiResponse(responseCode = "500", description = "An exception was thrown.")
            }
    )

    @PostMapping
    public ResponseEntity<ContactDTO> create(@RequestParam("idCompany")Integer idCompany, @RequestBody @Valid ContactCreateDTO contactCreateDTO) throws Exception;

    @Operation(summary = "Update Contact", description = "Update a contact in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return updated contact."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Contact not found."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<ContactDTO> update(@PathVariable Integer id, @RequestBody @Valid ContactUpdateDTO contactUpdateDTO) throws Exception;

    @Operation(summary = "Delete Contact", description = "Delete a contact in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Contact deleted successfully."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Contact not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<Void> delete(@RequestParam("idContact") Integer idContact, @RequestParam("idCompany") Integer idCompany) throws Exception;
}
