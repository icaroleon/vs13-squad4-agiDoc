package br.com.agidoc.agiDoc.controller.contact;

import br.com.agidoc.agiDoc.database.DBConnection;
import br.com.agidoc.agiDoc.dto.contact.ContactCreateDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactUpdateDTO;
import br.com.agidoc.agiDoc.service.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Contact", description = "CRUD of contacts")
@RestController
@Validated
@RequestMapping("/contact")
@Slf4j
public class ContactController implements IContactController {
    private final ContactService contactService;

    @GetMapping()
    @Override
    public ResponseEntity<List<ContactDTO>> listAll() throws Exception {
        try{
            log.info(DBConnection.getConnection().toString());
            log.info("Searching for all contacts to list.");
            List<ContactDTO> listAll = this.contactService.listALl();
            log.info("All contacts have been successfully listed.");
            return new ResponseEntity<List<ContactDTO>>(listAll, HttpStatus.OK);
        }catch (Exception erro){
            log.info("An error occurred while trying to search for all contacts."
                    +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/byid")
    @Override
    public ResponseEntity<ContactDTO> listOne(@RequestParam("idContact") Integer idContact) throws Exception {
        try{
            log.info("Searching for contact by id.");
            ContactDTO contactDTO = this.contactService.listOne(idContact);
            log.info("Contact found successfully.");
            return new ResponseEntity<>(contactDTO, HttpStatus.OK);
        }catch (Exception erro){
            log.info("An error occurred while trying to search for the contact by id."
                    +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping()
    @Override
    public ResponseEntity<ContactDTO> create(@RequestParam("idCompany")Integer idCompany, @RequestBody @Valid ContactCreateDTO contactCreateDTO) throws Exception {
        try{
            log.info("Creating new contact.");
            ContactDTO contactDTO = this.contactService.create(idCompany, contactCreateDTO);
            log.info("New contact created successfully.");
            return new ResponseEntity<ContactDTO>(contactDTO, HttpStatus.CREATED);
        }catch (Exception erro){
            log.info("An error occurred while trying to create a new contact."
                    +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping()
    public ResponseEntity<ContactDTO> update(@RequestParam("idContact") Integer idContact, @RequestBody ContactUpdateDTO contactUpdateDTO) throws Exception{
        try{
            log.info("Updating contact.");
            ContactDTO contactDTO = this.contactService.update(idContact, contactUpdateDTO);
            log.info("Contact updated successfully.");
            return new ResponseEntity<>(contactDTO, HttpStatus.ACCEPTED);
        }catch (Exception erro){
            log.info("An error occurred while trying to update the contact."
                    +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<Void> delete(@RequestParam("idContact") Integer idContact, @RequestParam("idCompany") Integer idCompany) throws Exception{
        try{
            log.info("Deleting contact.");
            this.contactService.delete(idContact, idCompany);
            log.info("Contact deleted successfully.");
            return ResponseEntity.ok().build();
        }catch (Exception erro){
            log.info("An error occurred while trying to delete the contact."
            +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }
}