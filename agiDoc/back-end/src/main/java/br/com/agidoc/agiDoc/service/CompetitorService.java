package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.competitor.CompetitorCreateDTO;
import br.com.agidoc.agiDoc.dto.competitor.CompetitorDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.competitor.Competitor;

import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.repository.CompetitorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CompetitorService {
    private final CompetitorRepository competitorRepository;
    private final ObjectMapper objectMapper;

    public CompetitorDTO create(CompetitorCreateDTO competitorCreateDTO) throws RegraDeNegocioException, DatabaseException {
        Competitor competitor = convertToEntity(competitorCreateDTO);
        log.info("Criando competidor...");
        return returnDTO(competitorRepository.save(competitor));
    }

    public CompetitorDTO getById(Integer id) throws RegraDeNegocioException {
        Competitor competitor = competitorRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Competitor not found"));
        log.info("Listando competidor por id...");

        return returnDTO(competitor);
    }

    public List<CompetitorDTO> list() throws DatabaseException {
        log.info("Listando competidores...");
        return competitorRepository.findAll().stream().map(this::returnDTO)
                .collect(Collectors.toList());
    }

    public CompetitorDTO update(Integer id, CompetitorCreateDTO competitorCreateDTO) throws RegraDeNegocioException {
        Competitor competitorToUpdate = returnCompetitorById(id);

        competitorToUpdate.setIdCompetitor(id);
        competitorToUpdate.setCompanyName(competitorCreateDTO.getCompanyName());
        competitorToUpdate.setCnpj(competitorCreateDTO.getCnpj());

        log.info("Atualizando competidor...");

        return returnDTO(competitorRepository.save(competitorToUpdate));
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Competitor competitorToDelete = returnCompetitorById(id);

        log.info("Deletando competidor...");

        competitorRepository.delete(competitorToDelete);
    }

    public Competitor convertToEntity(CompetitorCreateDTO dto) {
        return objectMapper.convertValue(dto, Competitor.class);
    }

    public CompetitorDTO returnDTO(Competitor entity) {
        return objectMapper.convertValue(entity, CompetitorDTO.class);
    }

    public Competitor returnCompetitorById(Integer id) throws RegraDeNegocioException {
        return competitorRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Competitor não encontrado"));
    }
}
