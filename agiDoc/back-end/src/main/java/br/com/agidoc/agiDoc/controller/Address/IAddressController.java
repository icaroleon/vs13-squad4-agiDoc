package br.com.agidoc.agiDoc.controller.Address;

import br.com.agidoc.agiDoc.dto.address.AddressCreateDTO;
import br.com.agidoc.agiDoc.dto.address.AddressDTO;
import br.com.agidoc.agiDoc.dto.address.AddressUpdateDTO;
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

public interface IAddressController {
    @Operation(summary = "List addresses", description = "List all adresses in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return a list of addresses."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<List<AddressDTO>> listAll() throws Exception;


    @Operation(summary = "Returns by id.", description = "Returns the specific address by id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Returns the address with that id."),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this feature."),
                    @ApiResponse(responseCode = "500", description = "An exception was thrown.")
            }
    )
    @GetMapping("/byid")
    public ResponseEntity<AddressDTO> listOne(@RequestParam("idAddress") Integer idAddress) throws Exception;
    @Operation(summary = "Create new address", description = "Create a new address and save it to the bank.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Returns the data of the address that was created."),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this feature."),
                    @ApiResponse(responseCode = "500", description = "An exception was thrown.")
            }
    )

    @PostMapping
    public ResponseEntity<AddressDTO> create(@RequestParam("idCompany") Integer idCompany, @RequestBody @Valid AddressCreateDTO addressCreateDTO) throws Exception;

    @Operation(summary = "Update address", description = "Update a address in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return updated address."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Address not found."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<AddressDTO> update(@RequestParam("idAddress") Integer idAddress, @RequestBody @Valid AddressUpdateDTO addressUpdateDTO) throws Exception;

    @Operation(summary = "Delete address", description = "Delete a address in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Address deleted successfully."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Address not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<Void> delete(@RequestParam("idAddress") Integer idAddress, @RequestParam("idCompany") Integer idCompany) throws Exception;
}
