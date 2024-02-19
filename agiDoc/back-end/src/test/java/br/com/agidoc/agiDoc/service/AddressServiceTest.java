package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.address.AddressCreateDTO;
import br.com.agidoc.agiDoc.dto.address.AddressDTO;
import br.com.agidoc.agiDoc.dto.address.AddressUpdateDTO;
import br.com.agidoc.agiDoc.model.address.entity.AddressEntity;
import br.com.agidoc.agiDoc.repository.AddressRepository;
import br.com.agidoc.agiDoc.repository.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AddressService - Test")
class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyService companyService;
    @Mock
    private AddressAssociationService addressAssociationService;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private AddressService addressService;

    @Test
    @DisplayName("Should create an new Address")
    public void shouldCreateAnAddressSuccessfully() throws Exception {
        Integer idRandom = new Random().nextInt();

        AddressCreateDTO addressCreateDTOMock = returnCreateAddressMock();
        AddressEntity addressEntityMock = returnCreateAddressEntityMock();
        AddressDTO addressDTO = returnAddressDTO();

        when(objectMapper.convertValue(addressCreateDTOMock, AddressEntity.class)).thenReturn(addressEntityMock);
        when(addressRepository.save(any())).thenReturn(addressEntityMock);
        when(objectMapper.convertValue(addressEntityMock, AddressDTO.class)).thenReturn(addressDTO);
        AddressDTO addressDTOCreated = addressService.create(idRandom, addressCreateDTOMock);

        assertNotNull(addressDTOCreated);
        assertEquals(addressDTOCreated, addressDTO);
    }

    @Test
    @DisplayName("You should update an address")
    public void shouldUpdateAnAddressSuccessfully() throws Exception{
        Integer idRandom = new Random().nextInt();

        AddressEntity addressEntityMock = returnCreateAddressEntityMock();
        AddressUpdateDTO addressUpdateDTOMock = returnAddressUpdateDTOMock();
        AddressDTO oldAddress = returnAddressDTO();
        AddressDTO newAddress = returnNewAddress();

        when(addressRepository.save(any())).thenReturn(addressEntityMock);
        when(addressRepository.findById(anyInt())).thenReturn(Optional.of(addressEntityMock));
        when(objectMapper.convertValue(addressEntityMock, AddressDTO.class)).thenReturn(newAddress);
        AddressDTO addressDTOCurrent = addressService.update(idRandom, addressUpdateDTOMock);

        assertNotNull(addressDTOCurrent);
        assertEquals(addressDTOCurrent, newAddress);
        assertNotEquals(addressDTOCurrent, oldAddress);
    }

    @Test
    @DisplayName("It should list all the addresses")
    public void shouldListAllTheAddressesSuccessfully() throws Exception{
        List<AddressEntity> listEntity = new ArrayList<>(Collections.nCopies(2, returnCreateAddressEntityMock()));

        when(addressRepository.findAll()).thenReturn(listEntity);

        List<AddressDTO> listAllAddress = addressService.listAll();

        assertNotNull(listAllAddress);
        assertEquals(listAllAddress.size(), listEntity.size());
    }

    @Test
    @DisplayName("It should return an address")
    public void shouldReturnAnAddressSuccessfully() throws Exception {
        Integer idRandom = new Random().nextInt();
        AddressEntity addressEntityMock = returnCreateAddressEntityMock();
        AddressDTO addressDTOMock = returnAddressDTO();

        when(addressRepository.findById(anyInt())).thenReturn(Optional.of(addressEntityMock));
        when(objectMapper.convertValue(addressEntityMock, AddressDTO.class)).thenReturn(addressDTOMock);

        AddressDTO addressDTO = addressService.listOne(idRandom);

        assertNotNull(addressDTO);
    }

    @Test
    @DisplayName("You should delete an address")
    public void shouldDeleteAnAddressSuccessfully() throws Exception{
        Integer idCompany = new Random().nextInt();
        Integer idAddress = new Random().nextInt();

        addressService.delete(idAddress, idCompany);

        verify(addressRepository, times(1)).deleteById(idAddress);

    }

    @Test
    @DisplayName("Should return one address per id")
    public void shouldReturnOneAddressPerIdSuccessfully() throws Exception{
        Integer idAddress = new Random().nextInt();
        AddressEntity addressEntityMock = returnCreateAddressEntityMock();

        when(addressRepository.findById(anyInt())).thenReturn(Optional.of(addressEntityMock));

        AddressEntity addressEntityCurrent = addressService.findById(idAddress);

        assertNotNull(addressEntityCurrent);
        assertEquals(addressEntityCurrent, addressEntityMock);
    }

    @Test
    @DisplayName("It should return a dto address")
    public void shouldReturnDTOAddressSuccessfully(){
        AddressEntity addressEntityMock = returnCreateAddressEntityMock();
        AddressDTO addressDTOMock = returnAddressDTO();

        when(objectMapper.convertValue(addressEntityMock, AddressDTO.class)).thenReturn(addressDTOMock);

        AddressDTO addressDTOCurrent = addressService.returnDTO(addressEntityMock);

        assertNotNull(addressDTOCurrent);
        assertEquals(addressDTOMock, addressDTOCurrent);
    }

    @Test
    @DisplayName("Should return an entity address")
    public void shouldReturnAnEntityAddressSuccessfully() throws Exception{
        AddressEntity addressEntityMock = returnCreateAddressEntityMock();
        AddressDTO addressDTOMock = returnAddressDTO();

        when(objectMapper.convertValue(addressDTOMock, AddressEntity.class)).thenReturn(addressEntityMock);

        AddressEntity addressEntityCurrent = addressService.returnEntity(addressDTOMock);

        assertNotNull(addressEntityCurrent);
        assertEquals(addressEntityMock, addressEntityCurrent);
    }


    private AddressDTO returnNewAddress(){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity("São Paulo");
        addressDTO.setStreet("Rua Tenente");
        addressDTO.setState("São Paulo");
        addressDTO.setNum(124);
        addressDTO.setComplement("Proximo a praça");
        addressDTO.setZipCode("18362917");
        addressDTO.setDistrict("Distrito de São Paulo");
        return addressDTO;
    }
    private static AddressUpdateDTO returnAddressUpdateDTOMock(){
        AddressUpdateDTO addressUpdateDTO = new AddressUpdateDTO();
        addressUpdateDTO.setCity("São Paulo");
        addressUpdateDTO.setStreet("Rua Tenente");
        addressUpdateDTO.setState("São Paulo");
        addressUpdateDTO.setNum(124);
        addressUpdateDTO.setComplement("Proximo a praça");
        addressUpdateDTO.setZipCode("18362917");
        addressUpdateDTO.setDistrict("Distrito de São Paulo");
        return addressUpdateDTO;
    }

    private static AddressDTO returnAddressDTO(){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCity("Recife");
        addressDTO.setStreet("Ferreira Silva");
        addressDTO.setNum(500);
        addressDTO.setState("Pernambuco");
        addressDTO.setComplement("Proximo a farmacia");
        addressDTO.setDistrict("Distrito de Recife");
        addressDTO.setZipCode("52948291");
        return addressDTO;
    }
    private static AddressCreateDTO returnCreateAddressMock(){
        AddressCreateDTO addressCreateDTO = new AddressCreateDTO();
        addressCreateDTO.setCity("Recife");
        addressCreateDTO.setStreet("Ferreira Silva");
        addressCreateDTO.setNum(500);
        addressCreateDTO.setState("Pernambuco");
        addressCreateDTO.setComplement("Proximo a farmacia");
        addressCreateDTO.setDistrict("Distrito de Recife");
        addressCreateDTO.setZipCode("52948291");
        return addressCreateDTO;
    }
    private static AddressEntity returnCreateAddressEntityMock(){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity("Recife");
        addressEntity.setStreet("Ferreira Silva");
        addressEntity.setNum(500);
        addressEntity.setState("Pernambuco");
        addressEntity.setComplement("Proximo a farmacia");
        addressEntity.setDistrict("Distrito de Recife");
        addressEntity.setZipCode("52948291");
        return addressEntity;
    }
}