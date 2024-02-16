package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.company.CompanyCreateDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.company.Type;
import br.com.agidoc.agiDoc.repository.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CompanyService - Test")
class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CompanyService companyService;

    CompanyCreateDTO companyCreateDTO;
    Company company;
    CompanyDTO companyDTO;

    @Test
    @DisplayName("Should create a new Company")
    public void shouldCreateCompanySuccessfully() throws RegraDeNegocioException, DatabaseException {
        CompanyCreateDTO companyCreateDTOMock = returnCompanyCreateDTO();
        Company companyEntityMock = returnCompany();
        CompanyDTO companyDTOMock = returnCompanyDTO();

        when(objectMapper.convertValue(companyCreateDTOMock, Company.class)).thenReturn(companyEntityMock);
        when(companyRepository.save(any())).thenReturn(companyEntityMock);
        when(objectMapper.convertValue(companyEntityMock, CompanyDTO.class)).thenReturn(companyDTOMock);

        CompanyDTO companyDTOCreated = companyService.create(companyCreateDTOMock);

        assertNotNull(companyDTOCreated);
        assertEquals(companyDTOCreated, companyDTOMock);
    }


    @Test
    @DisplayName("Should return Companys")
    public void shouldReturnCompanysSuccessfully() throws RegraDeNegocioException, DatabaseException {
        List<Company> listMock = List.of(returnCompany(), returnCompany(), returnCompany());

        when(companyRepository.findAll()).thenReturn(listMock);

        List<CompanyDTO> returnedDTOList = companyService.list();

        assertNotNull(returnedDTOList);
        assertEquals(listMock.size(), returnedDTOList.size());
    }


    @Test
    @DisplayName("Should update Company Status to INACTIVE")
    public void shouldUpdateStatusCompanySuccessfully() throws RegraDeNegocioException {
        Integer idRandom = new Random().nextInt();

        Company companyEntityMock = returnCompany();

        when(companyRepository.findById(idRandom)).thenReturn(Optional.of(companyEntityMock));

        companyService.delete(idRandom);

        assertEquals(Status.INACTIVE, companyEntityMock.getStatus());
    }


    @Test
    @DisplayName("Should update Company")
    public void shouldUpdateCompany() throws RegraDeNegocioException {
        Company companyMock = new Company();
        companyMock.setCompanyId(1);
        companyMock.setCnpj("12345678911232");
        companyMock.setType(Type.INSTITUTION);
        companyMock.setStatus(Status.ACTIVE);
        companyMock.setCompanyName("Public Company LTDA");

        Company companyEntityOld = new Company();
        BeanUtils.copyProperties(companyMock, companyEntityOld);

        CompanyUpdateDTO companyUpdateDTOMock = returnCompanyUpdateDTO();
        Company alteredCompany = returnCompany();
        CompanyDTO companyDTOMock = returnCompanyDTO();

        when(companyRepository.findById(anyInt())).thenReturn(Optional.of(companyMock));
        when(companyRepository.save(anyObject())).thenReturn(alteredCompany);
        when(objectMapper.convertValue(any(), eq(CompanyDTO.class))).thenReturn(companyDTOMock);

        CompanyDTO companyDTORetornada = companyService.update(companyMock.getCompanyId(), companyUpdateDTOMock);

        assertNotNull(companyDTORetornada);
        assertNotEquals(companyEntityOld, company);
        assertNotEquals(companyEntityOld.getCompanyName(), companyDTORetornada.getCompanyName());

    }

    @Test
    @DisplayName("Should return Companys")
    public void shouldReturnCompanyByIdSuccessfully() throws RegraDeNegocioException {
        Optional<Company> companyEntityMock = Optional.of(returnCompany());
        CompanyDTO companyDTOMock = returnCompanyDTO();
        Integer idRandom = new Random().nextInt();

        when(companyRepository.findById(anyInt())).thenReturn(companyEntityMock);
        when(objectMapper.convertValue(companyEntityMock.get(), CompanyDTO.class)).thenReturn(companyDTOMock);

        CompanyDTO companyDTOReturned = companyService.getById(idRandom);

        assertNotNull(companyDTOReturned);
        assertEquals(companyDTOReturned, companyDTOMock);
    }

    @Test
    @DisplayName("Should return Companys by Status ACTIVE")
    public void shouldReturnCompanyByStatusActiveSuccessfully() throws DatabaseException {
        List<Company> listMock = List.of(returnCompany(), returnCompany(), returnCompany());

        when(companyRepository.findAllByStatus(Status.ACTIVE)).thenReturn(listMock);

        List<CompanyDTO> returnedDTOList = companyService.listByStatusActive();

        assertNotNull(returnedDTOList);
        assertEquals(listMock.size(), returnedDTOList.size());
    }

    @Test
    @DisplayName("Should return Companys by Status INACTIVE")
    public void shouldReturnCompanyByStatusInactiveSuccessfully() throws DatabaseException {
        List<Company> listMock = List.of(returnCompany(), returnCompany(), returnCompany());

        when(companyRepository.findAllByStatus(Status.INACTIVE)).thenReturn(listMock);

        List<CompanyDTO> returnedDTOList = companyService.listByStatusInactive();

        assertNotNull(returnedDTOList);
        assertEquals(listMock.size(), returnedDTOList.size());
    }


    private static CompanyCreateDTO returnCompanyCreateDTO() {
        CompanyCreateDTO companyCreateDTO = new CompanyCreateDTO(
                "Public Company", "12345678910123", Type.INSTITUTION
        );

        return companyCreateDTO;
    }

    private static CompanyUpdateDTO returnCompanyUpdateDTO() {
        CompanyUpdateDTO companyUpdateDTO = new CompanyUpdateDTO("Public Company LDTA", "12345678910222");

        return companyUpdateDTO;
    };


    private Company returnCompany() {
        Company companyEntity = new Company();

        companyEntity.setCompanyId(1);
        companyEntity.setCnpj("12345678910123");
        companyEntity.setType(Type.INSTITUTION);
        companyEntity.setStatus(Status.ACTIVE);

        return companyEntity;
    }


    private static CompanyDTO returnCompanyDTO() {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyId(1);
        companyDTO.setCnpj("12345678910123");
        companyDTO.setCompanyName("Public Company");
        companyDTO.setType(Type.INSTITUTION);
        companyDTO.setStatus(Status.ACTIVE);

        return companyDTO;
    }
}
