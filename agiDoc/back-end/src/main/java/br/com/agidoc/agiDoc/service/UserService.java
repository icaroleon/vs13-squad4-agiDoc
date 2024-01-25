package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(User user) throws DatabaseException {
        return this.userRepository.create(user);
    }

    public List<User> listById(Integer id) throws Exception {
        return this.userRepository.listUser(id);
    }

    public List<User> list() throws DatabaseException {
        return this.userRepository.list();
    }

    public User update(Integer id, User user) throws Exception {
        return this.userRepository.update(id, user);
    }

    public void delete(Integer id) throws Exception {
        this.userRepository.delete(id);
    }
}
