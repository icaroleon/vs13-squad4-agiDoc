package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.contact.ContactDTO;
import br.com.agidoc.agiDoc.dto.user.UserCreateDTO;
import br.com.agidoc.agiDoc.dto.user.UserDTO;
import br.com.agidoc.agiDoc.dto.user.UserUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
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

    public UserDTO create(UserCreateDTO userCreateDTO) throws DatabaseException {
        User user = this.objectMapper.convertValue(userCreateDTO, User.class);

        user = this.userRepository.create(user);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }

    public List<UserDTO> listById(Integer id) throws Exception {
        return this.userRepository.listUser(id).stream().map(user -> this.objectMapper.convertValue(user, UserDTO.class)).collect(Collectors.toList());
    }

    public List<UserDTO> list() throws DatabaseException {
        return this.userRepository.list().stream().map(user -> this.objectMapper.convertValue(user, UserDTO.class)).collect(Collectors.toList());
    }

    public UserDTO update(Integer id, UserUpdateDTO userUpdateDTO) throws Exception {
        User user = this.objectMapper.convertValue(userUpdateDTO, User.class);

        user = this.userRepository.update(id, user);

        return this.objectMapper.convertValue(user, UserDTO.class);
    }

    public void delete(Integer id) throws Exception {
        this.userRepository.delete(id);
    }
}
