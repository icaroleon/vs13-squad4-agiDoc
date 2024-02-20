package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.address.AddressAssociationCreateDTO;
import br.com.agidoc.agiDoc.dto.address.AddressAssociationDTO;
import br.com.agidoc.agiDoc.dto.contact.ContactAssociationDTO;
import br.com.agidoc.agiDoc.model.address.entity.AddressAssociationEntity;
import br.com.agidoc.agiDoc.model.contact.entity.ContactAssociationEntity;
import br.com.agidoc.agiDoc.repository.AddresseAssociationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressAssociationService {
    private final AddresseAssociationRepository addresseAssociationRepository;
    private final ObjectMapper objectMapper;

    public AddressAssociationDTO create(AddressAssociationCreateDTO associationCreateDTO) throws Exception{
        AddressAssociationEntity addressAssociationEntity = returnEntity(associationCreateDTO);
        AddressAssociationDTO addressAssociationDTO = returnDTO(this.addresseAssociationRepository.save(addressAssociationEntity));
        return addressAssociationDTO;
    }
    public void delete(Integer idAddress, Integer idCompany) throws Exception{
        List<AddressAssociationEntity> list = this.addresseAssociationRepository.findAll();
        AddressAssociationEntity addressAssociationEntity = list.stream().filter(entity ->
                entity.getIdAddress().equals(idAddress) &&
                        entity.getIdCompany().equals(idCompany)).findFirst().get();
        if(addressAssociationEntity != null){
            this.addresseAssociationRepository.delete(addressAssociationEntity);
        }
    }
    public AddressAssociationDTO returnDTO(Object object){
        AddressAssociationDTO addressAssociationDTO = null;
        if(object instanceof AddressAssociationEntity){
            return this.objectMapper.convertValue((AddressAssociationEntity)object, AddressAssociationDTO.class);
        }
        else if(object instanceof AddressAssociationCreateDTO){
            return this.objectMapper.convertValue((AddressAssociationCreateDTO)object, AddressAssociationDTO.class);
        }
        return addressAssociationDTO;
    }
    public AddressAssociationEntity returnEntity(Object object){
        AddressAssociationEntity addresstAssociationEntity = null;
        if(object instanceof AddressAssociationCreateDTO){
            return this.objectMapper.convertValue((AddressAssociationCreateDTO)object, AddressAssociationEntity.class);
        }
        else if(object instanceof AddressAssociationDTO){
            return this.objectMapper.convertValue((AddressAssociationDTO) object, AddressAssociationEntity.class);
        }
        return addresstAssociationEntity;
    }
}
