//package br.com.agidoc.agiDoc.service;
//
//import br.com.agidoc.agiDoc.dto.address.AddressAssociationCreateDTO;
//import br.com.agidoc.agiDoc.dto.address.AddressAssociationDTO;
//import br.com.agidoc.agiDoc.dto.address.AddressCreateDTO;
//import br.com.agidoc.agiDoc.dto.address.AddressDTO;
//import br.com.agidoc.agiDoc.dto.company.CompanyCreateDTO;
//import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
//import br.com.agidoc.agiDoc.model.Status;
//import br.com.agidoc.agiDoc.model.address.Address;
//import br.com.agidoc.agiDoc.model.address.entity.AddressAssociationEntity;
//import br.com.agidoc.agiDoc.model.company.Company;
//import br.com.agidoc.agiDoc.model.company.Type;
//
//import br.com.agidoc.agiDoc.repository.AddressRepository;
//import br.com.agidoc.agiDoc.repository.AddresseAssociationRepository;
//import br.com.agidoc.agiDoc.repository.CompanyRepository;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("Address Associations Service - Test")
//public class AddressAssociationsServiceTest {
//    @Mock
//    private AddresseAssociationRepository addressAssociationRepository;
//
//    @Mock
//    private CompanyService companyService;
//
//    @Mock
//    private AddressService addressService;
//
//    @Mock
//    private ObjectMapper objectMapper;
//
//    @Mock
//    private AddressRepository addressRepository;
//
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @Spy
//    @InjectMocks
//    private AddressAssociationService addressAssociationService;
//
//
//    @Test
//    @DisplayName("Should create a address association successfully")
//    public void shouldCreateAddressAssociationSuccessfully() throws Exception {
//
//        CompanyCreateDTO companyCreateDTOMock = returnCompanyCreateDTO();
//        CompanyDTO companyDTOMock = returnCompanyDTO();
//
//        when(companyService.create(any(CompanyCreateDTO.class))).thenReturn(companyDTOMock);
//
//
//        CompanyDTO companyDTOCreated = companyService.create(companyCreateDTOMock);
//
//        AddressCreateDTO addressCreateDTOMock = returnAddressCreateDTO();
//        AddressDTO addressDTOMock = returnAddressDTO();
//
//        when(addressService.create(companyDTOCreated.getCompanyId(), returnAddressCreateDTO())).thenReturn(addressDTOMock);
//
//        AddressDTO addressDTO = addressService.create(companyDTOCreated.getCompanyId(), addressCreateDTOMock);
//
//
//        AddressAssociationCreateDTO addressAssociationtCreateDTOMock = returnAddressAssociationCreateDTO();
//        AddressAssociationEntity addressAssociationEntityMock = returnAddressAssociation();
//        AddressAssociationDTO addressAssociationDTOMock = returnAddressAssociationDTO();
//
//
//        when(objectMapper.convertValue(addressAssociationtCreateDTOMock, AddressAssociationEntity.class)).thenReturn(addressAssociationEntityMock);
//        when(addressAssociationRepository.save(any(AddressAssociationEntity.class))).thenReturn(addressAssociationEntityMock);
//        when(objectMapper.convertValue(addressAssociationEntityMock,AddressAssociationDTO.class)).thenReturn(addressAssociationDTOMock);
//
//        AddressAssociationDTO addressAssociationDTOCreated = addressAssociationService.create(addressAssociationtCreateDTOMock);
//
//        assertNotNull(addressAssociationDTOCreated);
//        assertEquals(addressAssociationDTOMock, addressAssociationDTOCreated);
//    }
//
//    @Test
//    @DisplayName("Should delete a address association successfully")
//    public void shouldDeleteAddressAssociationSuccessfully() throws Exception {
//        Integer idAddress = 1;
//        Integer idCompany = 1;
//
//
//        AddressAssociationEntity entity = new AddressAssociationEntity();
//        entity.setIdAddress(idAddress);
//        entity.setIdCompany(idCompany);
//
//
//        List<AddressAssociationEntity> list = Arrays.asList(entity);
//
//
//        when(addressAssociationRepository.findAll()).thenReturn(list);
//
//
//        addressAssociationService.delete(idAddress, idCompany);
//
//
//        verify(addressAssociationRepository, times(1)).delete(entity);
//
//    }
//
//    @Test
//    @DisplayName("Should convert to DTO successfully")
//    public void shouldConvertToDTO() {
//        AddressAssociationCreateDTO addressAssociationtCreateDTOMock =returnAddressAssociationCreateDTO();
//        AddressAssociationDTO addressAssociationDTOMock = returnAddressAssociationDTO();
//
//        when(objectMapper.convertValue(addressAssociationtCreateDTOMock, AddressAssociationDTO.class)).thenReturn(addressAssociationDTOMock);
//
//        AddressAssociationDTO contactAssociationReturnedDTO = addressAssociationService.returnDTO(addressAssociationtCreateDTOMock);
//
//        assertEquals(addressAssociationDTOMock, contactAssociationReturnedDTO);
//    }
//
//
//    @Test
//    @DisplayName("Should convert to Entity successfully")
//    public void shouldConvertToEntity() {
//        AddressAssociationDTO contactAssociationDTOMock = returnAddressAssociationDTO();
//        AddressAssociationEntity expectedEntity = returnAddressAssociation();
//
//        when(objectMapper.convertValue(contactAssociationDTOMock, AddressAssociationEntity.class)).thenReturn(expectedEntity);
//
//        AddressAssociationEntity contactAssociationReturnedEntity = addressAssociationService.returnEntity(contactAssociationDTOMock);
//
//        assertEquals(expectedEntity, contactAssociationReturnedEntity);
//    }
//
//
//    private static Address returnAddress() {
//        Address address = new Address();
//
//        address.setId(1);
//        address.setState("Rio de Janeiro");
//        address.setCity("Rio de Janeiro");
//        address.setZipCode("32123354");
//        address.setDistrict("Copacabana");
//        address.setStreet("Rua do Sol");
//        address.setNumber(531);
//        address.setComplement("Casa");
//
//        return address;
//    }
//
//
//    private static AddressDTO returnAddressDTO() {
//        AddressDTO addressDTO = new AddressDTO(1, "Rua do Sol", "Copacabana", 531, "Casa", "32123354", "Rio de Janeiro", "Rio de Janeiro");
//
//        return addressDTO;
//    }
//
//
//    private static AddressCreateDTO returnAddressCreateDTO() {
//        AddressCreateDTO addressCreateDTO = new AddressCreateDTO("Rua do Sol", "Copacabana", 531, "Casa", "32123354", "Rio de Janeiro", "Rio de Janeiro");
//
//        return addressCreateDTO;
//    }
//
//
//    private static Company returnCompany() {
//        Company companyEntity = new Company();
//        Integer randomId = new Random().nextInt();
//
//        companyEntity.setCompanyId(randomId);
//        companyEntity.setCnpj("12345678910123");
//        companyEntity.setType(Type.INSTITUTION);
//        companyEntity.setStatus(Status.ACTIVE);
//
//        return companyEntity;
//    }
//
//
//    private static CompanyDTO returnCompanyDTO() {
//        CompanyDTO companyDTO = new CompanyDTO();
//        Integer randomId = new Random().nextInt();
//
//        companyDTO.setCompanyId(randomId);
//        companyDTO.setCnpj("12345678910123");
//        companyDTO.setCompanyName("Public Company");
//        companyDTO.setType(Type.INSTITUTION);
//        companyDTO.setStatus(Status.ACTIVE);
//
//        return companyDTO;
//    }
//
//
//    private static CompanyCreateDTO returnCompanyCreateDTO() {
//        CompanyCreateDTO companyCreateDTO = new CompanyCreateDTO(
//                "Public Company", "12345678910123", Type.INSTITUTION
//        );
//
//        return companyCreateDTO;
//    }
//
//
//    private static AddressAssociationEntity returnAddressAssociation() {
//        AddressAssociationEntity addressAssociation = new AddressAssociationEntity();
//        addressAssociation.setIdAddressAssociation(1);
//        addressAssociation.setIdCompany(returnCompany().getCompanyId());
//        addressAssociation.setIdAddress(returnAddress().getId());
//
//        return addressAssociation;
//    }
//
//
//    private static AddressAssociationDTO returnAddressAssociationDTO() {
//        AddressAssociationDTO addressAssociationDTO = new AddressAssociationDTO(1, 1,1 );
//        return addressAssociationDTO;
//    }
//
//
//    private static AddressAssociationCreateDTO returnAddressAssociationCreateDTO() {
//        AddressAssociationCreateDTO addressAssociationCreateDTO = new AddressAssociationCreateDTO(1, 1);
//        return addressAssociationCreateDTO;
//    }
//}
