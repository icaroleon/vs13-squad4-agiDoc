package br.com.agidoc.agiDoc.controller.contact;

import br.com.agidoc.agiDoc.controller.contact.IContactController;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.service.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Contact", description = "CRUD of contacts")
@RestController
@Validated
@RequestMapping("/contact")
public class ContactController implements IContactController {
    private final ContactService contactService;


    @PostMapping
    public Contact create(@RequestBody Contact contact) throws Exception{
        return this.contactService.create(contact);
    }

    @GetMapping
    public List<Contact> list() throws Exception{
        return this.contactService.list();
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Integer id, @RequestBody Contact contact) throws Exception{
        return this.contactService.update(id, contact);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) throws Exception{
        return this.contactService.delete(id);
    }
}
