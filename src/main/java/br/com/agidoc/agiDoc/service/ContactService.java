package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.contact.ContactCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactUpdateDTO;
import br.com.agidoc.agiDoc.model.Associated;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final ObjectMapper objectMapper;

    public ContactDTO create(Associated associated, ContactCreateDTO contactCreateDTO) throws Exception {
        Contact contact = this.objectMapper.convertValue(contactCreateDTO, Contact.class);

        contact.setAssociated(associated);
        contact = this.contactRepository.create(contact);
    
        return this.objectMapper.convertValue(contact, ContactDTO.class);
    }

    public List<ContactDTO> list() throws Exception{
        return this.contactRepository.list().stream().map(contact -> this.objectMapper.convertValue(contact, ContactDTO.class)).collect(Collectors.toList());
    }

    public ContactDTO update(Integer id, ContactUpdateDTO contactUpdateDTO) throws Exception{
        Contact contact = this.objectMapper.convertValue(contactUpdateDTO, Contact.class);

        contact = this.contactRepository.update(id, contact);

        return this.objectMapper.convertValue(contact, ContactDTO.class);
    }

    public void delete(Integer id) throws Exception {
        this.contactRepository.delete(id);
    }

    public Integer getByIdInstitution(Integer idInstitution) throws Exception{
        return this.contactRepository.getByIdInstitution(idInstitution);
    }
}
