package br.com.agidoc.agiDoc.controller.contact;

import br.com.agidoc.agiDoc.controller.contact.IContactController;
import br.com.agidoc.agiDoc.dto.contact.ContactCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactUpdateDTO;
import br.com.agidoc.agiDoc.model.contact.Contact;
import br.com.agidoc.agiDoc.service.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<ContactDTO> list() throws Exception{
        return this.contactService.list();
    }

    @PutMapping("/{id}")
    public ContactDTO update(@PathVariable Integer id, @RequestBody ContactUpdateDTO contactUpdateDTO) throws Exception{
        return this.contactService.update(id, contactUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws Exception{
        this.contactService.delete(id);
    }
}
