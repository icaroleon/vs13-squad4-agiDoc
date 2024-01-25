package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public Contact create(Contact contact) throws Exception {
        return this.contactRepository.create(contact);
    }

    public List<Contact> list() throws Exception{
        return this.contactRepository.list();
    }

    public Contact update(Integer id, Contact contact) throws Exception{
        return this.contactRepository.update(id, contact);
    }

    public void delete(Integer id) throws Exception {
        this.contactRepository.delete(id);
    }
}
