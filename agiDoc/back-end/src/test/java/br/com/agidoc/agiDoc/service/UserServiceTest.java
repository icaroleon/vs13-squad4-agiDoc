package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.company.CompanyCreateDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyUpdateDTO;
import br.com.agidoc.agiDoc.dto.user.*;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.company.Type;
import br.com.agidoc.agiDoc.model.permission.Permission;
import br.com.agidoc.agiDoc.model.user.Department;
import br.com.agidoc.agiDoc.model.user.User;


import br.com.agidoc.agiDoc.repository.PermissionRepository;
import br.com.agidoc.agiDoc.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService - Test")
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyService companyService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private PermissionRepository permissionRepository;

    @Mock
    private UserAssociationService userAssociationService;

    @Spy
    @InjectMocks
    private UserService userService;

    User user;

    @Test
    @DisplayName("Should create a new user successfully")
    public void shouldCreateUserSuccessfully() throws RegraDeNegocioException, DatabaseException {

        CompanyCreateDTO companyCreateDTOMock = returnCompanyCreateDTO();
        CompanyDTO companyDTOMock = returnCompanyDTO();

        when(companyService.create(any(CompanyCreateDTO.class))).thenReturn(companyDTOMock);

        CompanyDTO companyDTOCreated = companyService.create(companyCreateDTOMock);

        UserCreateDTO userCreateDTOMock = returnUserCreateDTO();
        User userEntityMock = returnUser();
        UserDTO userDTOMock = returnUserDTO();

        when(permissionRepository.getPermissionByName(anyString())).thenReturn(Optional.of(returnPermission()));

        when(objectMapper.convertValue(userCreateDTOMock, User.class)).thenReturn(userEntityMock);
        when(userRepository.save(any(User.class))).thenReturn(userEntityMock);
        when(objectMapper.convertValue(userEntityMock, UserDTO.class)).thenReturn(userDTOMock);

        UserDTO userDTOCreated = userService.create(userCreateDTOMock, companyDTOCreated.getCompanyId());

        assertNotNull(userDTOCreated);
        assertEquals(userDTOMock, userDTOCreated);
    }


    @Test
    @DisplayName("Should return Users")
    public void shouldReturnUsersSuccessfully() throws DatabaseException {
        List<User> listMock = List.of(returnUser(), returnUser(), returnUser());

        when(userRepository.findAll()).thenReturn(listMock);

        List<UserDTO> returnedDTOList = userService.list();

        assertNotNull(returnedDTOList);
        assertEquals(listMock.size(), returnedDTOList.size());
    }


    @Test
    @DisplayName("Should return User by username")
    public void shouldReturnUserByUsername() {
        Optional<User> userEntityMock = Optional.of(returnUser());

        String randomName = String.valueOf(new Random().nextInt());

        when(userRepository.findByUser(anyString())).thenReturn(userEntityMock);

        Optional<User> userReturned = userService.findByUsername(randomName);

        assertNotNull(userReturned);
    }


    @Test
    @DisplayName("Should return User by username")
    public void shouldReturnUserByName() {
        User userEntityMock = returnUser();

        String randomName = String.valueOf(new Random().nextInt());

        when(userRepository.findUserByUser(anyString())).thenReturn(userEntityMock);

        User userReturned = userService.findByName(randomName);

        assertNotNull(userReturned);
    }


    @Test
    @DisplayName("Should return UserDTO by id")
    public void shouldReturnUserDTOById() throws Exception {
        Optional<User> userEntityMock = Optional.of(returnUser());
        UserDTO userDTOmock = returnUserDTO();
        Integer randomId = new Random().nextInt();

        when(userRepository.findById(anyInt())).thenReturn(userEntityMock);
        when(objectMapper.convertValue(userEntityMock.get(), UserDTO.class)).thenReturn(userDTOmock);

        UserDTO userDTOreturned = userService.getById(randomId);


        assertNotNull(userDTOreturned);
        assertEquals(userDTOreturned, userDTOmock);
    }


    @Test
    @DisplayName("Should return UserEntity by id")
    public void shouldReturnUserEntityById() {
        Optional<User> userEntityMock = Optional.of(returnUser());
        Integer randomId = new Random().nextInt();

        when(userRepository.findById(anyInt())).thenReturn(userEntityMock);

        Optional<User> userEntityReturned = userService.findByIdAndReturnEntity(randomId);

        assertNotNull(userEntityReturned);
    }


    @Test
    @DisplayName("Should return Users by Status ACTIVE")
    public void shouldReturnUsersByStatusActive() {
        List<User> listMock = List.of(returnUser(), returnUser(), returnUser());

        when(userRepository.findUserByStatus(Status.ACTIVE)).thenReturn(listMock);

        List<UserDTO> returnedDTOList = userService.listByStatusActive();

        assertNotNull(returnedDTOList);
        assertEquals(listMock.size(), returnedDTOList.size());
    }


    @Test
    @DisplayName("Should return Users by Status INACTIVE")
    public void shouldReturnUsersByStatusInactive() {
        List<User> listMock = List.of(returnUser(), returnUser(), returnUser());

        when(userRepository.findUserByStatus(Status.INACTIVE)).thenReturn(listMock);

        List<UserDTO> returnedDTOList = userService.listByStatusInactive();

        assertNotNull(returnedDTOList);
        assertEquals(listMock.size(), returnedDTOList.size());
    }


    @Test
    @DisplayName("Should return Logged User")
    public void shouldGetLoggedUser() throws RegraDeNegocioException {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("1");

        User userMock = new User();
        userMock.setIdUser(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(userMock));

        Optional<User> loggedUser = userService.getLoggedUser();

        assertTrue(loggedUser.isPresent());
        assertEquals(userMock, loggedUser.get());
    }


    @Test
    @DisplayName("Should update User Status to INACTIVE by the Delete method")
    public void shouldUpdateStatusByDelete() throws RegraDeNegocioException {
        Integer idRandom = new Random().nextInt();

        User userEntityMock = returnUser();

        when(userRepository.findById(idRandom)).thenReturn(Optional.of(userEntityMock));

        userService.delete(idRandom);

        assertEquals(Status.INACTIVE, userEntityMock.getStatus());
    }


    @Test
    @DisplayName("Should update User password")
    public void shouldUpdatePassword() throws Exception {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("1");

        User userMock = new User();
        userMock.setIdUser(1);
        userMock.setPassword("560dbef7d389278231aa6855dc93ec711b54efec5cfc15edbe130bbf021a8baa6893d1c4d8e078b9");

        when(userRepository.findById(1)).thenReturn(Optional.of(userMock));

        UserUpdatePasswordDTO userUpdatePasswordDTOMock = returnUserUpdatePasswordDTO();
        userUpdatePasswordDTOMock.setOldPassword("godMod");
        userUpdatePasswordDTOMock.setNewPassword("560dbef7d389278231aa6855dc93ec711b54efec5cfc15edbe130bbf021a8baa6893d1c4d8e078b9");


        Optional<String> userDTOReturned = userService.updatePassword(userUpdatePasswordDTOMock);


        assertTrue(userDTOReturned.isPresent());
        assertEquals("Password changed successfully.", userDTOReturned.get());
    }


    @Test
    @DisplayName("Should not update User password")
    public void shouldNotUpdatePassword() throws Exception {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("1");

        User userMock = new User();
        userMock.setIdUser(1);
        userMock.setPassword("560dbef7d389278231aa6855dc93ec711b54efec5cfc15edbe130bbf021a8baa6893d1c4d8e078b9");

        when(userRepository.findById(1)).thenReturn(Optional.of(userMock));

        UserUpdatePasswordDTO userWrongPasswordDTOMock = returnUserUpdatePasswordDTO();
        userWrongPasswordDTOMock.setOldPassword("WrongPassword");
        userWrongPasswordDTOMock.setNewPassword("560dbef7d389278231aa6855dc93ec711b54efec5cfc15edbe130bbf021a8baa6893d1c4d8e078b9");

        Optional<String> userDTONotReturned = userService.updatePassword(userWrongPasswordDTOMock);

        assertEquals( "Incorrect password.", userDTONotReturned.orElse(null));
    }


    @Test
    @DisplayName("Should update User")
    public void shouldUpdateUser() throws RegraDeNegocioException {
        User userMock = new User();
        userMock.setIdUser(1);
        userMock.setName("Joao");
        userMock.setUser("Joao11");
        userMock.setPosition("Software Developer");
        userMock.setDepartment(Department.SECRETARIA_SAUDE);

        User userEntityOld = new User();
        BeanUtils.copyProperties(userMock, userEntityOld);

        UserUpdateDTO userUpdateDTOMock = returnUserUpdateDTO();
        User alteredUser = returnUser();
        UserDTO userDTOMock = returnUserDTO();

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(userMock));
        when(userRepository.save(anyObject())).thenReturn(alteredUser);
        when(objectMapper.convertValue(any(), eq(UserDTO.class))).thenReturn(userDTOMock);

        UserDTO userDTOReturned = userService.update(userMock.getIdUser(), userMock.getUser(), userUpdateDTOMock);

        assertNotNull(userDTOReturned);
        assertNotEquals(userEntityOld, user);
        assertNotEquals(userEntityOld.getUser(), userDTOReturned.getUser());

    }

    @Test
    @DisplayName("Should not update User")
    public void shouldNotUpdateUser() throws RegraDeNegocioException {
        User userMock = new User();
        userMock.setIdUser(1);
        userMock.setName("Joao");
        userMock.setUser("Joao11");
        userMock.setPosition("Software Developer");
        userMock.setDepartment(Department.SECRETARIA_SAUDE);

        User userEntityOld = new User();
        BeanUtils.copyProperties(userMock, userEntityOld);

        UserUpdateDTO userUpdateDTOMock = returnUserUpdateDTO();
        User alteredUser = returnUser();
        UserDTO userDTOMock = returnUserDTO();
        userMock.setStatus(Status.INACTIVE);

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(userMock));
        when(userRepository.save(anyObject())).thenReturn(alteredUser);
        when(objectMapper.convertValue(any(), eq(UserDTO.class))).thenReturn(userDTOMock);

        UserDTO userDTOReturned = userService.update(userMock.getIdUser(), userMock.getUser(), userUpdateDTOMock);

        assertNull(userDTOReturned);
//        assertNotEquals(userEntityOld, user);
//        assertNotEquals(userEntityOld.getUser(), userDTOReturned.getUser());

    }



    private static UserCreateDTO returnUserCreateDTO() {
        List<String> permissions = new ArrayList<>();
        permissions.add("ADMIN_INSTITUTION");

        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setName("Joao Silva");
        userCreateDTO.setUser("joao3352");
        userCreateDTO.setPosition("Analista de Software");
        userCreateDTO.setPassword("senhajoao3352");
        userCreateDTO.setEmail("joao@dbccompany.com.br");
        userCreateDTO.setDepartment(Department.SECRETARIA_FAZENDA);
        userCreateDTO.setPermission(permissions);

        return userCreateDTO;
    }


    private static User returnUser() {
        User userEntity = new User();

        userEntity.setIdUser(1);
        userEntity.setRegistration("aabb123");
        userEntity.setName("Joao Silva");
        userEntity.setUser("joao3352");
        userEntity.setPassword("senhajoao3352");
        userEntity.setPosition("Analista de Software");
        userEntity.setStatus(Status.ACTIVE);
        userEntity.setEmail("joao@dbccompany.com.br");
        userEntity.setDepartment(Department.SECRETARIA_FAZENDA);

        userEntity.getPermissions().add(returnPermission());

        return userEntity;
    }


    private static Permission returnPermission() {
        Permission permission = new Permission();
        permission.setIdPermission(2);
        permission.setName("ADMIN_INSTITUTION");

        return permission;
    }


    private static UserDTO returnUserDTO() {
        Permission adminPermission = new Permission();
        adminPermission.setIdPermission(2);
        adminPermission.setName("ADMIN_INSTITUTION");

        UserDTO userDTO = new UserDTO();

        userDTO.setIdUser(1);
        userDTO.setRegistration("aabb123");
        userDTO.setName("Joao Silva");
        userDTO.setUser("joao3352");
        userDTO.setPermission(adminPermission);
        userDTO.setPosition("Analista de Software");
        userDTO.setStatus(Status.ACTIVE);
        userDTO.setEmail("joao@dbccompany.com.br");
        userDTO.setDepartment(Department.SECRETARIA_FAZENDA);

        return userDTO;
    }


    private Company returnCompany() {
        Company companyEntity = new Company();
        Integer randomId = new Random().nextInt();

        companyEntity.setCompanyId(randomId);
        companyEntity.setCnpj("12345678910123");
        companyEntity.setType(Type.INSTITUTION);
        companyEntity.setStatus(Status.ACTIVE);

        return companyEntity;
    }


    private static CompanyDTO returnCompanyDTO() {
        CompanyDTO companyDTO = new CompanyDTO();
        Integer randomId = new Random().nextInt();

        companyDTO.setCompanyId(randomId);
        companyDTO.setCnpj("12345678910123");
        companyDTO.setCompanyName("Public Company");
        companyDTO.setType(Type.INSTITUTION);
        companyDTO.setStatus(Status.ACTIVE);

        return companyDTO;
    }


    private static CompanyCreateDTO returnCompanyCreateDTO() {
        CompanyCreateDTO companyCreateDTO = new CompanyCreateDTO(
                "Public Company", "12345678910123", Type.INSTITUTION
        );

        return companyCreateDTO;
    }


    private static UserUpdatePasswordDTO returnUserUpdatePasswordDTO() {
        UserUpdatePasswordDTO userUpdatePasswordDTO = new UserUpdatePasswordDTO("godMod", "560dbef7d389278231aa6855dc93ec711b54efec5cfc15edbe130bbf021a8baa6893d1c4d8e078b9");

        return userUpdatePasswordDTO;
    }


    private static UserUpdateDTO returnUserUpdateDTO() {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO("Joao","joao33", "Software Developer", "joao33@email.com",Department.SECRETARIA_SAUDE);

        return userUpdateDTO;
    }
}
