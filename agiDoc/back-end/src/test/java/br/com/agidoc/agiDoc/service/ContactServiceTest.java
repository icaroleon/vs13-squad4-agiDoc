package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.contact.ContactCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactUpdateDTO;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.model.contact.ContactPhoneType;
import br.com.agidoc.agiDoc.model.contact.entity.ContactEntity;
import br.com.agidoc.agiDoc.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("ContactService - Test")
class ContactServiceTest {
    @InjectMocks
    private ContactService contactService;
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private CompanyService companyService;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private ContactAssociationService contactAssociationService;

    @Test
    @DisplayName("Should create an new Contact")
    public void shouldCreateAnContactSuccessfully() throws Exception{
        Integer idCompany = new Random().nextInt();
        ContactDTO contactDTOMock = returnContactDTOMock();
        ContactEntity contactEntityMock = returnContactEntityMock();
        ContactCreateDTO contactCreateDTOMock = returnContactCreateMock();

        when(objectMapper.convertValue(contactCreateDTOMock, ContactEntity.class)).thenReturn(contactEntityMock);
        when(objectMapper.convertValue(contactEntityMock, ContactDTO.class)).thenReturn(contactDTOMock);
        when(companyService.returnCompanyId(anyInt())).thenReturn(new Company());
        when(contactRepository.save(any())).thenReturn(contactEntityMock);

        ContactDTO contactDTOCreated = contactService.create(idCompany, contactCreateDTOMock);

        assertNotNull(contactDTOCreated);
        assertEquals(contactDTOCreated, contactDTOMock);
    }

    @Test
    @DisplayName("You should update an contact")
    public void shouldUpdateAnContactSuccessfully() throws Exception{
        Integer idContact = new Random().nextInt();

        ContactEntity contactEntityMock = returnContactEntityMock();
        ContactUpdateDTO contactUpdateDTOMock = returnContactUpdateMock();

        ContactDTO oldContact = returnContactDTOMock();
        ContactDTO newContact = returnNewContactUpdateMock();

        when(contactRepository.findById(anyInt())).thenReturn(Optional.of(contactEntityMock));
        when(contactRepository.save(any())).thenReturn(contactEntityMock);

        when(objectMapper.convertValue(contactEntityMock, ContactDTO.class)).thenReturn(newContact);

        ContactDTO contactDTOCurrent = contactService.update(idContact, contactUpdateDTOMock);
        assertNotNull(contactDTOCurrent);
        assertEquals(contactDTOCurrent, newContact);
        assertNotEquals(contactDTOCurrent, oldContact);
    }

    @Test
    @DisplayName("It should list all the contacts")
    public void shouldListAllTheContactsSuccessfully() throws Exception{
        List<ContactEntity> listEntityMock = new ArrayList<>(Collections.nCopies(2, returnContactEntityMock()));

        when(contactRepository.findAll()).thenReturn(listEntityMock);
        List<ContactDTO> listContactDTO = contactService.listAll();

        assertNotNull(listContactDTO);
        assertEquals(listContactDTO.size(), listEntityMock.size());
    }

    @Test
    @DisplayName("It should return an contact")
    public void shouldReturnAnContactSuccessfully() throws Exception {
        Integer idContact = new Random().nextInt();

        ContactEntity contactEntityMock = returnContactEntityMock();
        ContactDTO contactDTOMock = returnNewContactUpdateMock();

        when(contactRepository.findById(anyInt())).thenReturn(Optional.of(contactEntityMock));
        when(objectMapper.convertValue(contactEntityMock, ContactDTO.class)).thenReturn(contactDTOMock);

        ContactDTO contactDTOCurrent = contactService.listOne(idContact);

        assertNotNull(contactDTOCurrent);
        assertEquals(contactDTOMock, contactDTOCurrent);
    }

    @Test
    @DisplayName("You should delete an contact")
    public void shouldDeleteAnContactSuccessfully() throws Exception{
        Integer idContact = new Random().nextInt();
        Integer idCompany = new Random().nextInt();

        contactService.delete(idContact, idCompany);

        verify(contactRepository, times(1)).deleteById(idContact);
    }

    @Test
    @DisplayName("Should return one contact per id")
    public void shouldReturnOneContactPerIdSuccessfully() throws Exception{
        Integer idContact = new Random().nextInt();
        ContactEntity contactEntityMock = returnContactEntityMock();

        when(contactRepository.findById(anyInt())).thenReturn(Optional.of(contactEntityMock));

        ContactEntity contactEntityCurrent = contactService.findByIdContact(idContact);

        assertNotNull(contactEntityCurrent);
        assertEquals(contactEntityCurrent, contactEntityMock);
    }

    @Test
    @DisplayName("It should return a dto contact")
    public void shouldReturnDTOContactSuccessfully(){
        ContactEntity contactEntityMock = returnContactEntityMock();
        ContactDTO contactDTOMock = returnContactDTOMock();

        when(objectMapper.convertValue(contactEntityMock, ContactDTO.class)).thenReturn(contactDTOMock);

        ContactDTO contactDTOCurrent = contactService.returnDTO(contactEntityMock);

        assertNotNull(contactDTOCurrent);
        assertEquals(contactDTOCurrent, contactDTOMock);
    }

    @Test
    @DisplayName("Should return an entity address")
    public void shouldReturnAnEntityAddressSuccessfully() throws Exception{
        ContactEntity contactEntityMock  = returnContactEntityMock();
        ContactDTO contactDTOMock = returnContactDTOMock();

        when(objectMapper.convertValue(contactDTOMock, ContactEntity.class)).thenReturn(contactEntityMock);

        ContactEntity contactEntityCurrent = contactService.returnEntity(contactDTOMock);

        assertNotNull(contactEntityCurrent);
        assertEquals(contactEntityCurrent, contactEntityMock);
    }

    private static ContactDTO returnContactDTOMock(){
        ContactDTO contactDTOMock = new ContactDTO();
        contactDTOMock.setPhoneType(ContactPhoneType.MOBILE);
        contactDTOMock.setPhone("1938192039");
        contactDTOMock.setName("Jose");
        contactDTOMock.setEmail("teste@jgmail.com");
        return contactDTOMock;
    }
    private static ContactUpdateDTO returnContactUpdateMock(){
        ContactUpdateDTO contactUpdateDTOMock = new ContactUpdateDTO();
        contactUpdateDTOMock.setPhoneType(1);
        contactUpdateDTOMock.setPhone("8371826372");
        contactUpdateDTOMock.setName("Herik");
        contactUpdateDTOMock.setEmail("teste231@jgmail.com");
        return contactUpdateDTOMock;
    }
    private static ContactDTO returnNewContactUpdateMock(){
        ContactDTO contactDTOUpdateMock = new ContactDTO();
        contactDTOUpdateMock.setPhoneType(ContactPhoneType.MOBILE);
        contactDTOUpdateMock.setPhone("8371826372");
        contactDTOUpdateMock.setName("Herik");
        contactDTOUpdateMock.setEmail("teste231@jgmail.com");
        return contactDTOUpdateMock;
    }
    private static ContactCreateDTO returnContactCreateMock(){
        ContactCreateDTO contactCreateDTO = new ContactCreateDTO();
        contactCreateDTO.setPhoneType(1);
        contactCreateDTO.setPhone("1938192039");
        contactCreateDTO.setName("Jose");
        contactCreateDTO.setEmail("teste@jgmail.com");
        return contactCreateDTO;
    }
    private static ContactEntity returnContactEntityMock(){
        ContactEntity contactEntityMock = new ContactEntity();
        contactEntityMock.setPhoneType(1);
        contactEntityMock.setPhone("1938192039");
        contactEntityMock.setName("Jose");
        contactEntityMock.setEmail("teste@jgmail.com");
        return contactEntityMock;
    }
}