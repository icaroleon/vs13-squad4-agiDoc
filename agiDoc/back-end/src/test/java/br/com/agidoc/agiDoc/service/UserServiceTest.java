package br.com.agidoc.agiDoc.service;
import br.com.agidoc.agiDoc.dto.company.CompanyCreateDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
import br.com.agidoc.agiDoc.dto.user.UserCreateDTO;
import br.com.agidoc.agiDoc.dto.user.UserDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.company.Type;
import br.com.agidoc.agiDoc.model.permission.Permission;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.user.Department;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.repository.CompanyRepository;

import br.com.agidoc.agiDoc.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("PessoaService - Test")
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyService companyService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UserAssociationService userAssociationService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    UserCreateDTO userCreateDTO;
    User user;
    UserDTO userDTO;
    Company company;


    @Test
    @DisplayName("Should create a new user successfully")
    public void shouldCreateUserSuccessfully() throws RegraDeNegocioException, DatabaseException {
        CompanyCreateDTO companyCreateDTOMock = returnCompanyCreateDTO();
        Company companyEntityMock = returnCompany();
        CompanyDTO companyDTOMock = returnCompanyDTO();

        when(objectMapper.convertValue(companyCreateDTOMock, Company.class)).thenReturn(companyEntityMock);
        when(companyRepository.save(any())).thenReturn(companyEntityMock);
        when(objectMapper.convertValue(companyEntityMock, CompanyDTO.class)).thenReturn(companyDTOMock);
        when(companyService.create(any(CompanyCreateDTO.class))).thenReturn(companyDTOMock);


        CompanyDTO companyDTOCreated = companyService.create(companyCreateDTOMock);

        UserCreateDTO userCreateDTOMock = returnUserCreateDTO();
        User userEntityMock = returnUser();
        UserDTO userDTOMock = returnUserDTO();

        when(companyService.getById(companyDTOMock.getCompanyId())).thenReturn(companyDTOMock);

        when(objectMapper.convertValue(userCreateDTOMock, User.class)).thenReturn(userEntityMock);
        when(userRepository.save(any(User.class))).thenReturn(userEntityMock);
        when(objectMapper.convertValue(userEntityMock, UserDTO.class)).thenReturn(userDTOMock);
        when(companyRepository.save(any(Company.class))).thenReturn(returnCompany());

        UserDTO userDTOCreated = userService.create(userCreateDTOMock, companyDTOCreated.getCompanyId());

        assertNotNull(userDTOCreated);
        assertEquals(userDTOMock, userDTOCreated);
    }

    @Test
    @DisplayName("Should return Users")
    public void shouldReturnUsersSuccessfully() throws RegraDeNegocioException, DatabaseException {
        List<User> listMock = List.of(returnUser(), returnUser(), returnUser());

        when(userRepository.findAll()).thenReturn(listMock);

        List<UserDTO> returnedDTOList = userService.list();

        assertNotNull(returnedDTOList);
        assertEquals(listMock.size(), returnedDTOList.size());
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

        return userEntity;
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
}
