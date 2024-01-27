package br.com.agidoc.agiDoc.controller.user;

import br.com.agidoc.agiDoc.dto.user.UserCreateDTO;
import br.com.agidoc.agiDoc.dto.user.UserDTO;
import br.com.agidoc.agiDoc.dto.user.UserUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "User", description = "CRUD of users")
@RestController
@Validated
@RequestMapping("/user")
public class UserController implements IUserController{
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO userCreateDTO) throws Exception {
        return new ResponseEntity<>(this.userService.create(userCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> list() throws Exception {
        return new ResponseEntity<>(this.userService.list(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(this.userService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id,@RequestBody UserUpdateDTO userUpdateDTO) throws Exception {
        return new ResponseEntity<>(this.userService.update(id, userUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
