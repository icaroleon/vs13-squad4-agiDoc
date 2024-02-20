package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.company.CompanyCreateDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactAssociationCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactAssociationDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactDTO;
import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.company.Type;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.model.contact.ContactPhoneType;
import br.com.agidoc.agiDoc.model.contact.entity.ContactAssociationEntity;
import br.com.agidoc.agiDoc.repository.CompanyRepository;
import br.com.agidoc.agiDoc.repository.ContactAssociationRepository;
import br.com.agidoc.agiDoc.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Contact Associations Service - Test")
public class ContactAssociationsServiceTest {

    @Mock
    private ContactAssociationRepository contactAssociationRepository;

    @Mock
    private CompanyService companyService;

    @Mock
    private ContactService contactService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Spy
    @InjectMocks
    private ContactAssociationService contactAssociationService;

    @Test
    @DisplayName("Should create a contact association successfully")
    public void shouldCreateContactAssociationSuccessfully() throws Exception {

        CompanyCreateDTO companyCreateDTOMock = returnCompanyCreateDTO();
        CompanyDTO companyDTOMock = returnCompanyDTO();

        when(companyService.create(any(CompanyCreateDTO.class))).thenReturn(companyDTOMock);


        CompanyDTO companyDTOCreated = companyService.create(companyCreateDTOMock);

        ContactCreateDTO contactCreateDTOMock = returnContactCreateDTO();
        ContactDTO contactDTOMock = returnContactDTO();

        when(contactService.create(companyDTOCreated.getCompanyId(), returnContactCreateDTO())).thenReturn(contactDTOMock);

        ContactDTO contactDTO = contactService.create(companyDTOCreated.getCompanyId(), contactCreateDTOMock);


        ContactAssociationCreateDTO contacAssociationtCreateDTOMock = returnContactAssociationCreateDTO();
        ContactAssociationEntity contactAssociationEntityMock = returnContactAssociation();
        ContactAssociationDTO contactAssociationDTOMock = returnContactAssociationDTO();


        when(objectMapper.convertValue(contacAssociationtCreateDTOMock, ContactAssociationEntity.class)).thenReturn(contactAssociationEntityMock);
        when(contactAssociationRepository.save(any(ContactAssociationEntity.class))).thenReturn(contactAssociationEntityMock);
        when(objectMapper.convertValue(contactAssociationEntityMock, ContactAssociationDTO.class)).thenReturn(contactAssociationDTOMock);

        ContactAssociationDTO contactAssociationDTOCreated = contactAssociationService.create(contacAssociationtCreateDTOMock);

        assertNotNull(contactAssociationDTOCreated);
        assertEquals(contactAssociationDTOMock, contactAssociationDTOCreated);
    }


    @Test
    @DisplayName("Should delete a contact association successfully")
    public void shouldDeleteContactAssociationSuccessfully() throws Exception {
        Integer idContact = 1;
        Integer idCompany = 1;


        ContactAssociationEntity entity = new ContactAssociationEntity();
        entity.setIdContact(idContact);
        entity.setIdCompany(idCompany);


        List<ContactAssociationEntity> list = Arrays.asList(entity);


        when(contactAssociationRepository.findAll()).thenReturn(list);


        contactAssociationService.delete(idContact, idCompany);


        verify(contactAssociationRepository, times(1)).delete(entity);

    }

    @Test
    @DisplayName("Should return a contact or company successfully")
    public void testFindByIdContactOrIdCompany() throws Exception {
        Integer idContact = 1;
        Integer idCompany = 1;

        ContactAssociationEntity entity = new ContactAssociationEntity();
        entity.setIdContact(idContact);
        entity.setIdCompany(idCompany);

        List<ContactAssociationEntity> list = Arrays.asList(entity);

        when(contactAssociationRepository.findAll()).thenReturn(list);

        boolean result = contactAssociationService.findByIdContactOrIdCompany(idContact, idCompany);

        assertTrue(result);

        result = contactAssociationService.findByIdContactOrIdCompany(idContact, null);
        assertTrue(result);

        result = contactAssociationService.findByIdContactOrIdCompany(null, idCompany);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should convert to DTO successfully")
    public void shouldConvertToDTO() {
        ContactAssociationCreateDTO contacAssociationtCreateDTOMock = returnContactAssociationCreateDTO();
        ContactAssociationDTO contactAssociationDTOMock = returnContactAssociationDTO();

        when(objectMapper.convertValue(contacAssociationtCreateDTOMock, ContactAssociationDTO.class)).thenReturn(contactAssociationDTOMock);

        ContactAssociationDTO contactAssociationReturnedDTO = contactAssociationService.returnDTO(contacAssociationtCreateDTOMock);

        assertEquals(contactAssociationDTOMock, contactAssociationReturnedDTO);
    }

    @Test
    @DisplayName("Should convert to Entity successfully")
    public void shouldConvertToEntity() {
        ContactAssociationDTO contactAssociationDTOMock = returnContactAssociationDTO();
        ContactAssociationEntity expectedEntity = returnContactAssociation();

        when(objectMapper.convertValue(contactAssociationDTOMock, ContactAssociationEntity.class)).thenReturn(expectedEntity);

        ContactAssociationEntity contactAssociationReturnedEntity = contactAssociationService.returnEntity(contactAssociationDTOMock);

        assertEquals(expectedEntity, contactAssociationReturnedEntity);
    }




    private static Contact returnContact() {
        Contact contact = new Contact();

        contact.setEmail("fulano@email.com");
        contact.setId(1);
        contact.setPhone("22999335522");
        contact.setPhoneType(ContactPhoneType.MOBILE);
        contact.setName("Fulano");

        return contact;
    }

    private static ContactDTO returnContactDTO() {
        ContactDTO contactDTO = new ContactDTO(1, "Fulano", "fulano@email.com", "22999335522", ContactPhoneType.MOBILE);

        return contactDTO;
    }

    private static ContactCreateDTO returnContactCreateDTO() {
        ContactCreateDTO contactCreateDTO = new ContactCreateDTO("Fulano", "fulano@email.com", "22999335522", 2);

        return contactCreateDTO;
    }

    private static Company returnCompany() {
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

    private static ContactAssociationEntity returnContactAssociation() {
        ContactAssociationEntity contactAssociation = new ContactAssociationEntity();
        contactAssociation.setIdContactAssociation(1);
        contactAssociation.setIdCompany(returnCompany().getCompanyId());
        contactAssociation.setIdContact(returnContact().getId());

        return contactAssociation;
    };

    private static ContactAssociationDTO returnContactAssociationDTO() {
        ContactAssociationDTO contactAssociationDTO = new ContactAssociationDTO(1, 1,1 );
        return contactAssociationDTO;
    }

    private static ContactAssociationCreateDTO returnContactAssociationCreateDTO() {
        ContactAssociationCreateDTO contactAssociationCreateDTO = new ContactAssociationCreateDTO(1, 1);
        return contactAssociationCreateDTO;
    }


}
