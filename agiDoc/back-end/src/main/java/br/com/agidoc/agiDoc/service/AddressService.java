package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.address.AddressAssociationCreateDTO;
import br.com.agidoc.agiDoc.dto.address.AddressCreateDTO;
import br.com.agidoc.agiDoc.dto.address.AddressDTO;
import br.com.agidoc.agiDoc.dto.address.AddressUpdateDTO;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.address.entity.AddressEntity;
import br.com.agidoc.agiDoc.repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressAssociationService addressAssociationEntity;
    private final CompanyService companyService;
    private final ObjectMapper objectMapper;

    public AddressDTO create(Integer idCompany, AddressCreateDTO addressCreateDTO) throws Exception{
        AddressEntity addressEntity = returnEntity(addressCreateDTO);
        this.companyService.getById(idCompany);
        AddressDTO addressDTO = returnDTO(this.addressRepository.save(addressEntity));
        // falta a parte de criar na association.
        AddressAssociationCreateDTO associationCreateDTO = new AddressAssociationCreateDTO();
        associationCreateDTO.setIdAddress(addressDTO.getId());
        associationCreateDTO.setIdCompany(idCompany);
        this.addressAssociationEntity.create(associationCreateDTO);
        return addressDTO;
    }

    public AddressDTO update(Integer idAddress, AddressUpdateDTO addressUpdateDTO) throws Exception{
        AddressEntity addressEntity = findById(idAddress);

        addressEntity.setStreet(addressUpdateDTO.getStreet());
        addressEntity.setDistrict(addressUpdateDTO.getDistrict());
        addressEntity.setNum(addressUpdateDTO.getNum());
        addressEntity.setComplement(addressUpdateDTO.getComplement());
        addressEntity.setZipCode(addressUpdateDTO.getZipCode());
        addressEntity.setCity(addressUpdateDTO.getCity());
        addressEntity.setState(addressUpdateDTO.getState());

        return returnDTO(this.addressRepository.save(addressEntity));
    }

    public List<AddressDTO> listAll() throws Exception{
        List<AddressDTO> listALl = this.addressRepository.findAll().stream().map(this::returnDTO).toList();
        return listALl;
    }

    public AddressDTO listOne(Integer idAddress) throws Exception{
        return returnDTO(findById(idAddress));
    }

    public void delete(Integer idAddress, Integer idCompany) throws Exception{
        this.addressAssociationEntity.delete(idAddress, idCompany);
        this.addressRepository.deleteById(idAddress);
    }

    public AddressDTO returnDTO(Object object){
        if(object instanceof AddressEntity){
            return this.objectMapper.convertValue((AddressEntity) object, AddressDTO.class);
        }
        else if(object instanceof AddressUpdateDTO){
            return this.objectMapper.convertValue((AddressUpdateDTO) object, AddressDTO.class);
        }
        return null;
    }

    public AddressEntity returnEntity(Object object) throws Exception{
        if(object instanceof AddressCreateDTO){
            return this.objectMapper.convertValue((AddressCreateDTO) object, AddressEntity.class);
        }
        else if(object instanceof AddressUpdateDTO){
            return this.objectMapper.convertValue((AddressUpdateDTO) object, AddressEntity.class);
        }
        return null;
    }

    public AddressEntity findById(Integer idAddress) throws Exception{
        Optional<AddressEntity> optional = this.addressRepository.findById(idAddress);
        if(optional.isPresent()){
            return optional.get();
        }
        else {
            throw new RegraDeNegocioException("Address ID not found.");
        }
    }
}