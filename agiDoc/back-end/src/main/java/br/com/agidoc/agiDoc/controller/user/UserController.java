package br.com.agidoc.agiDoc.controller.user;

import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
import br.com.agidoc.agiDoc.dto.user.UserCreateDTO;
import br.com.agidoc.agiDoc.dto.user.UserDTO;
import br.com.agidoc.agiDoc.dto.user.UserLoginDTO;
import br.com.agidoc.agiDoc.dto.user.UserUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.service.TokenService;
import br.com.agidoc.agiDoc.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Tag(name = "User", description = "CRUD of users")
@RestController
@Validated
@RequestMapping("/user")
public class UserController implements IUserController{
    private final UserService userService;
    private final TokenService tokenService;
    public final AuthenticationManager authenticationManager;
    public final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserCreateDTO userCreateDTO) throws Exception {
        String encryptedPassword = passwordEncoder.encode(userCreateDTO.getPassword());
        userCreateDTO.setPassword(encryptedPassword);
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

    @GetMapping("/actives")
    public ResponseEntity<List<UserDTO>> listActives() throws Exception {
        return new ResponseEntity<>(userService.listByStatusActive(), HttpStatus.OK);
    }

    @GetMapping("/inactives")
    public ResponseEntity<List<UserDTO>> listInactives() throws Exception {
        return new ResponseEntity<>(userService.listByStatusInactive(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) throws Exception {
        return new ResponseEntity<>(this.userService.update(id, userUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        this.userService.delete(id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/login")
//    public String login(@Valid @RequestBody UserLoginDTO userLoginDTO) throws Exception {
//        Optional<User> byUsernameAndPassword = userService.login(userLoginDTO.getUser(), userLoginDTO.getPassword());
//        if (byUsernameAndPassword.isPresent()) {
//            return tokenService.getToken(byUsernameAndPassword.get());
//        } else {
//            throw new RegraDeNegocioException("User or password is not valid");
//        }
//    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDTO userLoginDTO) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUser(),
                        userLoginDTO.getPassword()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);

        User usuarioValidado = (User) authentication.getPrincipal();

        return tokenService.generateToken(usuarioValidado);
    }

    @GetMapping("/get-logged-user")
    public ResponseEntity<Optional<User>> getLoggerUser() throws RegraDeNegocioException {
        return new ResponseEntity<>(userService.getLoggedUser(), HttpStatus.OK);
    }
}
