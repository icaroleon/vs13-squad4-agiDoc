package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.contact.ContactAssociationCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactAssociationDTO;
import br.com.agidoc.agiDoc.model.contact.Entity.ContactAssociationEntity;
import br.com.agidoc.agiDoc.repository.ContactAssociationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactAssociationService {
    private final ContactAssociationRepository contactAssociationRepository;
    private final ObjectMapper objectMapper;

    public ContactAssociationDTO create(ContactAssociationCreateDTO contactAssociationCreateDTO) throws Exception{
        ContactAssociationEntity contactAssociationEntity = returnEntity(contactAssociationCreateDTO);
        ContactAssociationDTO contactAssociationDTO = returnDTO(this.contactAssociationRepository.save(contactAssociationEntity));
        return contactAssociationDTO;
    }

    public void delete(Integer idContact, Integer idCompany) throws Exception{
        List<ContactAssociationEntity> list = this.contactAssociationRepository.findAll();
        ContactAssociationEntity contactAssociationEntity = list.stream()
                .filter(entity ->
                entity.getContactAssociationPk().getIdContact().equals(idContact) &&
                entity.getContactAssociationPk().getIdCompany().equals(idCompany)).findFirst().get();
        this.contactAssociationRepository.delete(contactAssociationEntity);
    }

    public boolean findById(Integer idContact, Integer idCompany) throws Exception{
        List<ContactAssociationEntity> listAll = this.contactAssociationRepository.findAll();
        // Search for the entity by id, either with both ids or with just one, and returns boolean.
        boolean result = false;
        if(idContact != null && idCompany != null){
            result = listAll.stream().anyMatch(entity ->
                    entity.getContactAssociationPk().getIdContact().equals(idContact) &&
                    entity.getContactAssociationPk().getIdCompany().equals(idCompany));
        }
        else if(idContact != null){
            result = listAll.stream().anyMatch(entity ->
                    entity.getContactAssociationPk().getIdContact().equals(idContact));
        }
        else if(idCompany != null){
            result = listAll.stream().anyMatch(entity ->
                    entity.getContactAssociationPk().getIdCompany().equals(idCompany));

        }
        return result;
    }

    public ContactAssociationDTO returnDTO(Object object) {
        ContactAssociationDTO contactAssociationDTO = null;
        if(object instanceof ContactAssociationEntity){
            contactAssociationDTO = this.objectMapper.convertValue((ContactAssociationEntity) object, ContactAssociationDTO.class);
        }
        else if(object instanceof ContactAssociationCreateDTO){
            contactAssociationDTO = this.objectMapper.convertValue((ContactAssociationCreateDTO) object, ContactAssociationDTO.class);
        }
        return contactAssociationDTO;
    }

    public ContactAssociationEntity returnEntity(Object object){
        ContactAssociationEntity contactAssociationEntity = null;
        if(object instanceof ContactAssociationCreateDTO){
            contactAssociationEntity = this.objectMapper.convertValue((ContactAssociationCreateDTO) object,
                    ContactAssociationEntity.class);
        }
        else if(object instanceof ContactAssociationDTO){
            contactAssociationEntity = this.objectMapper.convertValue((ContactAssociationDTO) object, ContactAssociationEntity.class);
        }
        return contactAssociationEntity;
    }
}