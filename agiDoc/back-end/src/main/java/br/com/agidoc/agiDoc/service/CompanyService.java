package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.company.CompanyCreateDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
import br.com.agidoc.agiDoc.dto.company.CompanyUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Company;

import br.com.agidoc.agiDoc.repository.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ObjectMapper objectMapper;


    public CompanyDTO create(CompanyCreateDTO companyCreateDTO) throws RegraDeNegocioException, DatabaseException {
        Company company = convertToEntity(companyCreateDTO);
        log.info("Criando empresa...");
        company.setStatus(Status.ACTIVE);
        return returnDTO(companyRepository.save(company));
    }

    public CompanyDTO getById(Integer id) throws RegraDeNegocioException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Company not found"));
        log.info("Listando empresa por id...");

        return returnDTO(company);
    }

    public List<CompanyDTO> list() throws DatabaseException {
        log.info("Listando empresas...");
        return companyRepository.findAll().stream().map(this::returnDTO)
                .collect(Collectors.toList());
    }

    public List<CompanyDTO> listByStatusActive() throws DatabaseException {
        log.info("Listando empresas pelo status ativo...");
        return companyRepository.findAllByStatus(Status.ACTIVE).stream().map(this::returnDTO)
                .collect(Collectors.toList());
    }

    public List<CompanyDTO> listByStatusInactive() throws DatabaseException {
        log.info("Listando empresas pelo status inativo...");
        return companyRepository.findAllByStatus(Status.INACTIVE).stream().map(this::returnDTO)
                .collect(Collectors.toList());
    }

    public CompanyDTO update(Integer id, CompanyUpdateDTO companyUpdateDTO) throws RegraDeNegocioException {
        Company companyToUpdate = returnCompanyId(id);

        log.info("Atualizando empresa...");

        if (companyToUpdate.getStatus().ordinal() == 0) {
            companyToUpdate.setCompanyId(companyToUpdate.getCompanyId());
            companyToUpdate.setCnpj(companyUpdateDTO.getCnpj());
            companyToUpdate.setType(companyToUpdate.getType());
            companyToUpdate.setStatus(companyToUpdate.getStatus());
            companyToUpdate.setCompanyName(companyUpdateDTO.getCompanyName());
            return returnDTO(companyRepository.save(companyToUpdate));
        } else {
            new RegraDeNegocioException("Empresa não existe");
            return null;
        }
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Company companyToDelete = returnCompanyId(id);

        log.info("Deletando empresa...");
        companyToDelete.setStatus(Status.INACTIVE);
        companyRepository.save(companyToDelete);

    }

    public Company convertToEntity(CompanyCreateDTO dto) {
        return objectMapper.convertValue(dto, Company.class);
    }

    public CompanyDTO returnDTO(Company entity) {
        return objectMapper.convertValue(entity, CompanyDTO.class);
    }

    public Company returnCompanyId(Integer id) throws RegraDeNegocioException {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Empresa não encontrada"));
    }
}
