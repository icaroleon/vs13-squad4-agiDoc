package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.user.UserAssociationCreateDTO;
import br.com.agidoc.agiDoc.model.user.entity.UserAssociationEntity;
import br.com.agidoc.agiDoc.model.user.pk.UserAssociationPK;
import br.com.agidoc.agiDoc.repository.UserAssociationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("UserAssociationService - Test")
class UserAssociationServiceTest {
    @InjectMocks
    private UserAssociationService userAssociationService;
    @Mock
    private UserAssociationRepository userAssociationRepository;
    @Mock
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create a new user association successfully")
    public void shouldCreateNewUserAssociationSuccessfully(){
        UserAssociationCreateDTO userAssociationCreateDTOMock = returnUserAssociationCreateDTOMock();
        UserAssociationEntity userAssociationEntityMock = returnUserAssociationEntityMock();

        when(userAssociationRepository.save(any())).thenReturn(userAssociationEntityMock);
        userAssociationRepository.save(userAssociationEntityMock);
        userAssociationService.createAssociation(userAssociationCreateDTOMock);
        verify(userAssociationRepository, times(1)).save(userAssociationEntityMock);
    }

    @Test
    @DisplayName("Should delete a user association successfully")
    public void shouldDeleteUserAssociationSuccessfully(){
        Integer idUser = new Random().nextInt();
        Integer idCompany = new Random().nextInt();
        List<UserAssociationEntity> listEntityMock = new ArrayList<>(Collections.nCopies(2, returnUserAssociationEntityMock()));

        when(userAssociationRepository.findAll()).thenReturn(listEntityMock);

        listEntityMock.get(0).getUserAssociationPK().setIdUser(idUser);
        listEntityMock.get(0).getUserAssociationPK().setIdCompany(idCompany);

        userAssociationService.deleteAssociation(idUser, idCompany);

        verify(userAssociationRepository, times(0)).delete(listEntityMock.get(0));

    }

    @Test
    @DisplayName("should return a user association entity.")
    public void shouldReturnAUserAssociationEntitySuccessfully(){
        UserAssociationEntity userAssociationEntityMock = returnUserAssociationEntityMock();
        UserAssociationCreateDTO userAssociationCreateDTOMock = returnUserAssociationCreateDTOMock();

        when(objectMapper.convertValue(userAssociationCreateDTOMock, UserAssociationEntity.class)).thenReturn(userAssociationEntityMock);

        UserAssociationEntity userAssociationEntityCurrent = userAssociationService.returnEntity(userAssociationCreateDTOMock);

        assertNotNull(userAssociationEntityCurrent);
        assertEquals(userAssociationEntityCurrent, userAssociationEntityMock);
    }

    @Test
    @DisplayName("should delete a user association by method")
    public void shouldDeleteAUserAssociationByMethod(){
        UserAssociationEntity userAssociationEntityMock = returnUserAssociationEntityMock();

        userAssociationService.deleteEntity(userAssociationEntityMock);
    }

    private static UserAssociationCreateDTO returnUserAssociationCreateDTOMock(){
        UserAssociationCreateDTO userAssociationCreateDTOMock = new UserAssociationCreateDTO();
        userAssociationCreateDTOMock.setUserAssociationPK(returnUserAssociationPKMock());
        return userAssociationCreateDTOMock;
    }
    private static UserAssociationEntity returnUserAssociationEntityMock(){
        UserAssociationEntity userAssociationEntityMock = new UserAssociationEntity();
        userAssociationEntityMock.setUserAssociationPK(returnUserAssociationPKMock());
        return userAssociationEntityMock;
    }
    private static UserAssociationPK returnUserAssociationPKMock(){
        UserAssociationPK userAssociationPKMock = new UserAssociationPK();
        userAssociationPKMock.setIdUser(new Random().nextInt());
        userAssociationPKMock.setIdCompany(new Random().nextInt());
        return userAssociationPKMock;
    }
}