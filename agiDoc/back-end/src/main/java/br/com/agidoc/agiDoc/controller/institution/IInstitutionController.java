package br.com.agidoc.agiDoc.controller.institution;

import br.com.agidoc.agiDoc.dto.institution.InstitutionCreateDTO;
import br.com.agidoc.agiDoc.dto.institution.InstitutionDTO;
import br.com.agidoc.agiDoc.model.institution.Institution;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.ArrayList;

public interface IInstitutionController {
    @Operation(summary = "Create a new institution.", description = "Create a new institution and save it to the database.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Returns the new institution created in the database."),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this feature."),
                    @ApiResponse(responseCode = "500", description = "An exception was thrown.")
            }
    )
    public ResponseEntity<InstitutionDTO> create(@Valid @RequestBody InstitutionCreateDTO institutionCreateDTO) throws Exception;

    @Operation(summary = "Update institution.", description = "Update a institution in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return updated institution."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Institution not found."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<InstitutionDTO> update(@PathVariable("idInstitution") Integer idInstitution,
    @Valid @RequestBody InstitutionCreateDTO institutionCreateDTO) throws Exception;

    @Operation(summary = "List institutions.", description = "List all institution in database.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return a list of institutions."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<ArrayList<InstitutionDTO>> listAll() throws Exception;

    @Operation(summary = "Delete institution.", description = "Delete a institution in database.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Institution deleted successfully."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Institution not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable("idInstitution") Integer idInstitution) throws Exception;
}
