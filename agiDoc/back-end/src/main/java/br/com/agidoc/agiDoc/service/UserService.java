package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.user.*;
import br.com.agidoc.agiDoc.dto.user.UserCreateDTO;
import br.com.agidoc.agiDoc.dto.user.UserDTO;
import br.com.agidoc.agiDoc.dto.user.UserUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.permission.Permission;
import br.com.agidoc.agiDoc.repository.PermissionRepository;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.model.user.pk.UserAssociationPK;
import br.com.agidoc.agiDoc.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserAssociationService userAssociationService;
    private final CompanyService companyService;
    private final ObjectMapper objectMapper;
    private final PermissionRepository permissionRepository;

    public UserDTO create(UserCreateDTO userCreateDTO, Integer idCompany) throws RegraDeNegocioException {
        if (companyService.getById(idCompany) != null) {
            User user = convertToEntity(userCreateDTO);
            user.setStatus(Status.ACTIVE);

            if (userCreateDTO.getPermission().size() > 1) {
                List<String> permissions = userCreateDTO.getPermission().stream()
                        .map(p -> p.split(","))
                        .flatMap(Arrays::stream)
                        .collect(Collectors.toList());
                for (String permissionName : permissions) {
                    Permission permission = permissionRepository.getPermissionByName(permissionName.trim())
                            .orElseThrow(() -> new RegraDeNegocioException("Permission " + permissionName + " not found"));
                    user.getPermissions().add(permission);
                }
            } else {
                Permission permission = permissionRepository.getPermissionByName
                        (userCreateDTO.getPermission().get(0))
                        .orElseThrow(() -> new RegraDeNegocioException("Permission not found"));
                user.getPermissions().add(permission);
            }

            UserDTO userDTO = returnDTO(userRepository.save(user));
            UserAssociationPK userAssociationPK = new UserAssociationPK();
            userAssociationPK.setIdUser(userDTO.getIdUser());
            userAssociationPK.setIdCompany(idCompany);
            UserAssociationCreateDTO userAssociationCreateDTO = new UserAssociationCreateDTO();
            userAssociationCreateDTO.setUserAssociationPK(userAssociationPK);
            this.userAssociationService.createAssociation(userAssociationCreateDTO);
            return userDTO;
        } else {
            throw new RegraDeNegocioException("Id company not found/exists.");
        }
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUser(username);
    }

    public UserDTO getById(Integer id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new RegraDeNegocioException("User not found"));

        return returnDTO(user);
    }

    public Optional<User> findByIdAndReturnEntity(Integer id) throws RegraDeNegocioException {
        Optional<User> user = userRepository.findById(id);

        return user;
    }

    public List<UserDTO> list() throws DatabaseException {
        return userRepository.findAll().stream().map(this::returnDTO).collect(Collectors.toList());
    }

    public List<UserDTO> listByStatusActive() throws DatabaseException {
        return userRepository.findUserByStatus(Status.ACTIVE).stream().map(this::returnDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> listByStatusInactive() throws DatabaseException {
        return userRepository.findUserByStatus(Status.INACTIVE).stream().map(this::returnDTO)
                .collect(Collectors.toList());
    }

    public UserDTO update(Integer id, UserUpdateDTO userUpdateDTO) throws RegraDeNegocioException {
        User userToUpdate = returnUserById(id);

        if (userToUpdate.getStatus().ordinal() == 0) {
            userToUpdate.setIdUser(userToUpdate.getIdUser());
            userToUpdate.setName(userUpdateDTO.getName());
            userToUpdate.setUser(userToUpdate.getUser());
            userToUpdate.setPassword(userUpdateDTO.getPassword());
//            userToUpdate.setPermissionType(userUpdateDTO.getPermissionType());
            userToUpdate.setPosition(userUpdateDTO.getPosition());
            userToUpdate.setStatus(userToUpdate.getStatus());
            userToUpdate.setDepartment(userToUpdate.getDepartment());
            return returnDTO(userRepository.save(userToUpdate));
        } else {
            new RegraDeNegocioException("Usuário não existe");
            return null;
        }
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        User user = returnUserById(id);

        user.setStatus(Status.INACTIVE);

        userRepository.save(user);
    }

    public Optional<User> login(String username, String password) throws RegraDeNegocioException {

        User user = userRepository.findUserByUser(username);

        if (user.getStatus().ordinal() == 1) {
            throw new RegraDeNegocioException("User not found.");
        }

        return userRepository.findUsersByUserAndPassword(username, password);
    }

    public User convertToEntity(UserCreateDTO dto) throws RegraDeNegocioException {
        return objectMapper.convertValue(dto, User.class);
    }

    public UserDTO returnDTO(User entity) {
        return objectMapper.convertValue(entity, UserDTO.class);
    }

    public User returnUserById(Integer id) throws RegraDeNegocioException {
        return userRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));
    }

    public Optional<User> getLoggedUser() throws RegraDeNegocioException {
        return findByIdAndReturnEntity(getIdLoggedUser());
    }

    public Integer getIdLoggedUser() {
        Integer findUserId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return findUserId;
    }
}
