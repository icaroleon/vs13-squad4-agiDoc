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
        User user = convertToEntity(userCreateDTO);

        return returnDTO(userRepository.save(user));
    }

    public UserDTO getById(Integer id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("User not found"));

        return returnDTO(user);
    }

    public List<UserDTO> list() throws DatabaseException {
        return userRepository.findAll().stream().map(this::returnDTO).collect(Collectors.toList());
    }

    public UserDTO update(Integer id, UserUpdateDTO userUpdateDTO) throws Exception {
        User userToUpdate = returnUserById(id);

        userToUpdate.setName(userUpdateDTO.getName());
        userToUpdate.setPassword(userUpdateDTO.getPassword());
        userToUpdate.setRole(userUpdateDTO.getRole());
        userToUpdate.setPosition(userUpdateDTO.getPosition());

        return returnDTO(userRepository.save(userToUpdate));
    }

    public void delete(Integer id) throws Exception {
        User userToDelete =returnUserById(id);
        userRepository.delete(userToDelete);
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        User user = userRepository.findUserByUser(username);

        if(user == null) {
           new RegraDeNegocioException("User not found");
        }
        if (!user.getPassword().equals(password)) {
            new RegraDeNegocioException("Username or password is incorrect.");
        }

        return user.getPassword().equals(password);
    }

    public User convertToEntity(UserCreateDTO dto) {
        return objectMapper.convertValue(dto, User.class);
    }

    public UserDTO returnDTO(User entity) {
        return objectMapper.convertValue(entity, UserDTO.class);
    }

    public User returnUserById(Integer id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
    }
}
