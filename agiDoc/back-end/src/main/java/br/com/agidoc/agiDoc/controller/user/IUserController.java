package br.com.agidoc.agiDoc.controller.user;

import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface IUserController {
    @Operation(summary = "Create User", description = "Create a user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return created user."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    User create(User user) throws Exception;

    @Operation(summary = "List Users", description = "List all users in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return a list of users."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    List<User> list() throws Exception;

    @Operation(summary = "List Users by id", description = "List users by id in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return a list of users with a given id."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    List<User> listById(Integer id) throws Exception;

    @Operation(summary = "Update User", description = "Update a user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return updated user."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "User not found."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    User update(Integer id, User user) throws Exception;

    @Operation(summary = "Delete User", description = "Delete a user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User deleted successfully."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "User not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    void delete(Integer id) throws Exception;
}
