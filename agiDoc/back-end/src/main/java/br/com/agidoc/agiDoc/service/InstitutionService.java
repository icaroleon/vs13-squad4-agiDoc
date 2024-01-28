package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.institution.InstitutionCreateDTO;
import br.com.agidoc.agiDoc.dto.institution.InstitutionDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.institution.Institution;
import br.com.agidoc.agiDoc.repository.InstitutionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final ObjectMapper objectMapper;

    public InstitutionDTO create(InstitutionCreateDTO institutionCreateDTO) throws Exception{
        try{
            Institution institution = objectMapper.convertValue(institutionCreateDTO, Institution.class);
            InstitutionDTO institutionDTO = objectMapper.convertValue(this.institutionRepository.create(institution), InstitutionDTO.class);
            return institutionDTO;
        }catch(Exception erro){
            throw new DatabaseException(new Throwable("Ocorreu um erro ao tentar criar á instituição."
            +"\nInformações sobre o erro:" + erro.getCause()));
        }
    }

    public InstitutionDTO update(Integer idInstitution, InstitutionCreateDTO institutionCreateDTO) throws Exception{
        try{
            Institution institution = objectMapper.convertValue(institutionCreateDTO,Institution.class);
            InstitutionDTO institutionDTO = objectMapper.convertValue(this.institutionRepository.update(idInstitution, institution), InstitutionDTO.class);
            return institutionDTO;
        }catch(Exception erro){
            throw new DatabaseException(new Throwable("Ocorreu um erro ao tentar atualizar á instituição."
            +"\nInformações sobre o erro:" + erro.getCause()));
        }
    }

    public ArrayList<InstitutionDTO> listAll() throws Exception{
        try{
            ArrayList<Institution> registeredList = this.institutionRepository.list();
            ArrayList<InstitutionDTO> listAll = new ArrayList<>();
            registeredList.forEach(institution -> listAll.add(
                    objectMapper.convertValue(institution, InstitutionDTO.class)
            ));
            return listAll;
        }catch(Exception erro){
            throw new DatabaseException(
            new Throwable("Ocorreu um erro ao tentar listar todas ás instituições."
            +"\nInformações sobre o erro:" + erro.getCause()));
        }

    }

    public void delete(Integer idInstitution) throws Exception{
        try{
            this.institutionRepository.delete(idInstitution);
        }catch(Exception erro){
            throw new DatabaseException(
                    new Throwable("Ocorreu um erro ao tentar deletar á instituição."
                            +"\nInformações sobre o erro:" + erro.getCause()));
        }
    }
}