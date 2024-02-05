package br.com.agidoc.agiDoc.controller.Address;

import br.com.agidoc.agiDoc.dto.address.AddressCreateDTO;
import br.com.agidoc.agiDoc.dto.address.AddressDTO;
import br.com.agidoc.agiDoc.dto.address.AddressUpdateDTO;
import br.com.agidoc.agiDoc.service.AddressService;
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
@RestController
@Tag(name = "Address", description = "CRUD of address")
@Slf4j
@Validated
@RequestMapping("/address")
public class AddressController implements IAddressController{
    private final AddressService addressService;
    @GetMapping
    @Override
    public ResponseEntity<List<AddressDTO>> listAll() throws Exception {
        try{
            log.info("Searching for all addresses to list.");
            List<AddressDTO> listAll = this.addressService.listAll();
            log.info("All adresses have been successfully listed.");
            return new ResponseEntity<List<AddressDTO>>(listAll, HttpStatus.OK);
        }catch (Exception erro){
            log.info("An error occurred while trying to search for all adresses."
                    +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/byid")
    @Override
    public ResponseEntity<AddressDTO> listOne(@RequestParam("idAddress") Integer idAddress) throws Exception {
        try{
            log.info("Searching for address by id.");
            AddressDTO addressDTO = this.addressService.listOne(idAddress);
            log.info("Address found successfully.");
            return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.OK);
        }catch (Exception erro){
            log.info("An error occurred while trying to search for the address by id."
                    +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    @Override
    public ResponseEntity<AddressDTO> create(@RequestParam("idCompany") Integer idCompany, @RequestBody @Valid AddressCreateDTO addressCreateDTO) throws Exception {
        try{
            log.info("Creating new address.");
            AddressDTO addressDTO = this.addressService.create(idCompany, addressCreateDTO);
            log.info("New address created successfully.");
            return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.CREATED);
        }catch (Exception erro){
            log.info("An error occurred while trying to create a new address."
                    +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    @Override
    public ResponseEntity<AddressDTO> update(@RequestParam("idAddress") Integer idAddress, @RequestBody AddressUpdateDTO addressUpdateDTO) throws Exception {
        try{
            log.info("Updating address.");
            AddressDTO addressDTO = this.addressService.update(idAddress, addressUpdateDTO);
            log.info("Address updated successfully.");
            return new ResponseEntity<>(addressDTO, HttpStatus.ACCEPTED);
        }catch (Exception erro){
            log.info("An error occurred while trying to update the address."
                    +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    @Override
    public ResponseEntity<Void> delete(@RequestParam("idAddress") Integer idAddress, @RequestParam("idCompany") Integer idCompany) throws Exception {
        try{
            log.info("Deleting address.");
            this.addressService.delete(idAddress, idCompany);
            log.info("Address deleted successfully.");
            return ResponseEntity.ok().build();
        }catch (Exception erro){
            log.info("An error occurred while trying to delete the address."
                    +"\nInformation about the error: ".concat(erro.getCause().toString()));
            return ResponseEntity.badRequest().build();
        }
    }
}
