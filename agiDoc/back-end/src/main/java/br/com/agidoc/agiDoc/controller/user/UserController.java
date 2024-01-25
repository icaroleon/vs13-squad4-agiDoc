package br.com.agidoc.agiDoc.controller.user;

import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public User create(@RequestBody User user) throws Exception {
        return this.userService.create(user);
    }

    @GetMapping
    public List<User> list() throws Exception {
        return this.userService.list();
    }

    @GetMapping("/{id}")
    public List<User> listById(@PathVariable Integer id) throws Exception {
        return this.userService.listById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Integer id,@RequestBody User user) throws Exception {
        return this.userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        this.userService.delete(id);
    }
}
