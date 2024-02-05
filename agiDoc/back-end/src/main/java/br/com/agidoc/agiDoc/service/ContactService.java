package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.contact.*;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.contact.Entity.ContactEntity;
import br.com.agidoc.agiDoc.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactAssociationService contactAssociationService;
    private final CompanyService companyService;
    private final ObjectMapper objectMapper;

    public ContactDTO create(Integer idCompany, ContactCreateDTO contactCreateDTO) throws Exception {

        // Creating and saving contact:
        ContactEntity contactEnitity = returnEntity(contactCreateDTO);
        ContactDTO contactDTO = returnDTO(this.contactRepository.save(contactEnitity));

        // Checking if the company id exists:
        this.companyService.returnCompanyId(idCompany);

        // Creating contact association with the company:
        ContactAssociationCreateDTO contactAssociationCreateDTO = new ContactAssociationCreateDTO();
        contactAssociationCreateDTO.getContactAssociationPk().setIdContact(contactDTO.getIdContact());
        contactAssociationCreateDTO.getContactAssociationPk().setIdCompany(idCompany);
        this.contactAssociationService.create(contactAssociationCreateDTO);

        return contactDTO;
    }

    public List<ContactDTO> listALl() throws Exception{
        List<ContactDTO> listAll = this.contactRepository.findAll().stream().map(this::returnDTO).toList();
        return listAll;
    }

    public ContactDTO listOne(Integer idContact) throws Exception{
        ContactDTO contactDTO = returnDTO(findByIdContact(idContact));
        return contactDTO;
    }

    public ContactDTO update(Integer idContact, ContactUpdateDTO contactUpdateDTO) throws Exception{
        ContactEntity contactEnitity = findByIdContact(idContact);
        ContactDTO contactDTO = returnDTO(this.contactRepository.save(contactEnitity));
        return contactDTO;
    }

    public void delete(Integer idContact, Integer idCompany) throws Exception {
        this.contactAssociationService.delete(idContact, idCompany);
        this.contactRepository.deleteById(idContact);
    }

    public ContactEntity returnEntity(Object object) throws Exception{
        ContactEntity contactEnitity = null;
        if(object instanceof ContactCreateDTO){
            contactEnitity = this.objectMapper.convertValue((ContactCreateDTO) object, ContactEntity.class);
        }
        else if(object instanceof ContactUpdateDTO){
            contactEnitity = this.objectMapper.convertValue((ContactUpdateDTO) object, ContactEntity.class);
        }
        else if(object instanceof ContactDTO){
            contactEnitity = this.objectMapper.convertValue((ContactDTO) object, ContactEntity.class);
        }
        return contactEnitity;
    }

    public ContactDTO returnDTO(Object object){
        ContactDTO contactDTO = null;
        if(object instanceof ContactCreateDTO){
            contactDTO = this.objectMapper.convertValue((ContactCreateDTO) object, ContactDTO.class);
        }
        else if(object instanceof ContactUpdateDTO){
            contactDTO = this.objectMapper.convertValue((ContactUpdateDTO) object, ContactDTO.class);
        }
        else if(object instanceof ContactEntity){
            contactDTO = this.objectMapper.convertValue((ContactEntity) object,ContactDTO.class);
        }
        return contactDTO;
    }
    public ContactEntity findByIdContact(Integer idContact) throws Exception{
        Optional<ContactEntity> optional = this.contactRepository.findById(idContact);
        if(optional.isPresent()){
            return optional.get();
        }
        else{
            String iDNotFound = "Contact ID not found.";
            throw new RegraDeNegocioException(iDNotFound);
        }
    }
}