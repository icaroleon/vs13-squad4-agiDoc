package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.user.UserCreateDTO;
import br.com.agidoc.agiDoc.dto.user.UserDTO;
import br.com.agidoc.agiDoc.dto.user.UserLoginDTO;
import br.com.agidoc.agiDoc.dto.user.UserUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.Status;
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

    public UserDTO create(UserCreateDTO userCreateDTO) throws RegraDeNegocioException {
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

    public UserDTO update(Integer id, UserUpdateDTO userUpdateDTO) throws RegraDeNegocioException {
        User userToUpdate = returnUserById(id);

        if (userToUpdate.getStatus().ordinal() == 0) {
            userToUpdate.setIdUser(userToUpdate.getIdUser());
            userToUpdate.setName(userUpdateDTO.getName());
            userToUpdate.setPassword(userUpdateDTO.getPassword());
            userToUpdate.setRole(userUpdateDTO.getRole());
            userToUpdate.setPosition(userUpdateDTO.getPosition());
            userToUpdate.setStatus(userToUpdate.getStatus());
            return returnDTO(userRepository.save(userToUpdate));
        } else {
            new RegraDeNegocioException("Usuário não existe");
            return null;
        }
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        User user = returnUserById(id);

        user.setStatus(Status.INACTIVE);
        user.setUser(null);
    }

    public boolean login(UserLoginDTO userLoginDTO) throws RegraDeNegocioException {
        String username = userLoginDTO.getUser();
        String password = userLoginDTO.getPassword();

        User user = userRepository.findUserByUser(username);

        if(user.getStatus().ordinal() == 1) {
            throw new RegraDeNegocioException("User not found.");
        }

        if(user.getUser() == null) {
           throw new RegraDeNegocioException("User not found.");
        }

        if (!user.getPassword().equals(password)) {
            throw new RegraDeNegocioException("Username or password is incorrect.");
        }

        return true;
    }

    public User convertToEntity(UserCreateDTO dto) throws RegraDeNegocioException {
        return objectMapper.convertValue(dto, User.class);
    }

    public UserDTO returnDTO(User entity) {
        return objectMapper.convertValue(entity, UserDTO.class);
    }

    public User returnUserById(Integer id) throws RegraDeNegocioException{
        return userRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
    }
}
