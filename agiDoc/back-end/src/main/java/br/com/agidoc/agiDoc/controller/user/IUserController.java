package br.com.agidoc.agiDoc.controller.user;

import br.com.agidoc.agiDoc.dto.user.UserCreateDTO;
import br.com.agidoc.agiDoc.dto.user.UserDTO;
import br.com.agidoc.agiDoc.dto.user.UserLoginDTO;
import br.com.agidoc.agiDoc.dto.user.UserUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
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
    ResponseEntity<UserDTO> create(@Valid UserCreateDTO userCreateDTO) throws Exception;

    @Operation(summary = "List Users", description = "List all users in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return a list of users."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<List<UserDTO>> list() throws Exception;

    @Operation(summary = "Get User by id", description = "Get user by id in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return user with a given id."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<UserDTO> getById(Integer id) throws Exception;

    @Operation(summary = "List active users", description = "List all active users in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List all active users"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "404", description = "Companies not found"),
                    @ApiResponse(responseCode = "500", description = "An exception occurred")
            }
    )
    @GetMapping("/actives")
    ResponseEntity<List<UserDTO>> listActives() throws Exception;

    @Operation(summary = "List inactive users", description = "List all inactive users in the database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "List all inactive users"),
                    @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
                    @ApiResponse(responseCode = "404", description = "Companies not found"),
                    @ApiResponse(responseCode = "500", description = "An exception occurred")
            }
    )
    @GetMapping("/inactives")
    ResponseEntity<List<UserDTO>> listInactives() throws Exception;

    @Operation(summary = "Update User", description = "Update a user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return updated user."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "User not found."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<UserDTO> update(Integer id, @Valid UserUpdateDTO userUpdateDTO) throws Exception;

    @Operation(summary = "Delete User", description = "Delete a user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User deleted successfully."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "User not found."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<Void> delete(Integer id) throws Exception;

    @Operation(summary = "Login", description = "Login with a user")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User logged successfully."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "User not found."),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(hidden = true)), description = "Wrong data inserted, check the error in response."),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(hidden = true)), description = "Invalid password."),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(hidden = true)), description = "Unhandled exception.")
            }
    )
    ResponseEntity<Void> login(@Valid UserLoginDTO userLoginDTO) throws Exception;
}
