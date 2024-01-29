package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.address.AddressCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactUpdateDTO;
import br.com.agidoc.agiDoc.dto.institution.InstitutionCreateDTO;
import br.com.agidoc.agiDoc.dto.institution.InstitutionDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.Associated;
import br.com.agidoc.agiDoc.model.address.Address;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.model.institution.Institution;
import br.com.agidoc.agiDoc.repository.InstitutionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final ContactService contactService;
    private final ObjectMapper objectMapper;

    public InstitutionDTO create(InstitutionCreateDTO institutionCreateDTO) throws Exception{
        try{
            Institution institution = this.objectMapper.convertValue(institutionCreateDTO, Institution.class);
            institution.setContact(this.objectMapper.convertValue(institutionCreateDTO.getContactCreateDTO(), Contact.class));
            institution.setAddress(this.objectMapper.convertValue(institutionCreateDTO.getAddressCreateDTO(), Address.class));


            InstitutionDTO institutionDTO = this.objectMapper.convertValue(this.institutionRepository.create(institution), InstitutionDTO.class);
            institutionCreateDTO.getContactCreateDTO().setAssociatedId(institutionDTO.getId());
            ContactDTO contactDTO = this.contactService.create(Associated.INSTITUTION, institutionCreateDTO.getContactCreateDTO());

            institutionDTO.setContact(this.objectMapper.convertValue(contactDTO, Contact.class));
            return institutionDTO;
        }catch(Exception erro){
            throw new DatabaseException(new Throwable("Ocorreu um erro ao tentar criar á instituição."
            +"\nInformações sobre o erro: " + erro.getCause()));
        }
    }

    public InstitutionDTO update(Integer idInstitution, InstitutionCreateDTO institutionCreateDTO) throws Exception{
        try{
            Institution institution = objectMapper.convertValue(institutionCreateDTO,Institution.class);
            institution.setAddress(this.objectMapper.convertValue(institutionCreateDTO.getAddressCreateDTO(), Address.class));
            institution.setContact(this.objectMapper.convertValue(institutionCreateDTO.getContactCreateDTO(), Contact.class));

            ContactUpdateDTO contactUpdateDTO = this.objectMapper.convertValue(institutionCreateDTO.getContactCreateDTO(), ContactUpdateDTO.class);
            Integer idContact = this.contactService.getByIdInstitution(idInstitution);
            this.contactService.update(idContact, contactUpdateDTO);
            InstitutionDTO institutionDTO = objectMapper.convertValue(this.institutionRepository.update(idInstitution, institution), InstitutionDTO.class);
            institutionDTO.setId(idInstitution);
            institutionDTO.getContact().setId(idContact);

            return institutionDTO;
        }catch(Exception erro){
            throw new DatabaseException(new Throwable("Ocorreu um erro ao tentar atualizar á instituição."
            +"\nInformações sobre o erro: " + erro.getCause()));
        }
    }

    public ArrayList<InstitutionDTO> listAll() throws Exception{
        try{
            ArrayList<Institution> registeredList = this.institutionRepository.list();
            ArrayList<InstitutionDTO> listAll = new ArrayList<>();
            registeredList.forEach(institution -> listAll.add(
                    objectMapper.convertValue(institution, InstitutionDTO.class)
            ));
            return listAll;
        }catch(Exception erro){
            throw new DatabaseException(
            new Throwable("Ocorreu um erro ao tentar listar todas ás instituições."
            +"\nInformações sobre o erro: " + erro.getCause()));
        }

    }

    public void delete(Integer idInstitution) throws Exception{
        try{
            this.contactService.delete(this.contactService.getByIdInstitution(idInstitution));
            this.institutionRepository.delete(idInstitution);

        }catch(Exception erro){
            throw new DatabaseException(
                    new Throwable("Ocorreu um erro ao tentar deletar á instituição."
                            +"\nInformações sobre o erro: " + erro.getCause()));
        }
    }
}