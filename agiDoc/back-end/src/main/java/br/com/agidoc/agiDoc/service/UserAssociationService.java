package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.user.UserAssociationCreateDTO;
import br.com.agidoc.agiDoc.model.user.entity.UserAssociationEntity;
import br.com.agidoc.agiDoc.repository.UserAssociationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAssociationService {
    private final ObjectMapper objectMapper;
    private final UserAssociationRepository userAssociationRepository;

    public void createAssociation(UserAssociationCreateDTO userAssociationCreateDTO) {
        this.userAssociationRepository.save(returnEntity(userAssociationCreateDTO));
    }

    public void deleteAssociation(Integer idUsuario, Integer idCompany){
        List<UserAssociationEntity> list = this.userAssociationRepository.findAll();
        list.stream()
                .filter(entity -> entity.getUserAssociationPK().getIdUser().equals(idUsuario)
                && entity.getUserAssociationPK().getIdCompany().equals(idCompany)).map(this::deleteEntity);
    }
    public boolean deleteEntity(UserAssociationEntity userAssociationEntity){
        this.userAssociationRepository.delete(userAssociationEntity);
        return true;
    }
    public UserAssociationEntity returnEntity(UserAssociationCreateDTO userAssociationCreateDTO){
        return this.objectMapper.convertValue(userAssociationCreateDTO, UserAssociationEntity.class);
    }
}
