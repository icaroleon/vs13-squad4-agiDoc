package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.contact.ContactDTO;
import br.com.agidoc.agiDoc.dto.user.UserCreateDTO;
import br.com.agidoc.agiDoc.dto.user.UserDTO;
import br.com.agidoc.agiDoc.dto.user.UserLoginDTO;
import br.com.agidoc.agiDoc.dto.user.UserUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserDTO create(UserCreateDTO userCreateDTO) throws Exception {
        User user = this.objectMapper.convertValue(userCreateDTO, User.class);

        user = this.userRepository.create(user);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }

    public UserDTO getById(Integer id) throws Exception {
        User user = this.userRepository.getUserById(id);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }

    public List<UserDTO> list() throws DatabaseException {
        return this.userRepository.list().stream().map(user -> this.objectMapper.convertValue(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO update(Integer id, UserUpdateDTO userUpdateDTO) throws Exception {
        User user = this.userRepository.getUserById(id);

        this.objectMapper.updateValue(user, userUpdateDTO);
        user = this.userRepository.update(id, user);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }

    public void delete(Integer id) throws Exception {
        this.userRepository.delete(id);
    }

    public boolean login(UserLoginDTO userLoginDTO) throws DatabaseException, RegraDeNegocioException {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        User user = this.userRepository.getUserByUsername(username);

        return user.getPassword().equals(password);
    }
}
