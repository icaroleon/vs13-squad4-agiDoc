package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessCreateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessUpdateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessesDocumentsDTO;
import br.com.agidoc.agiDoc.dto.user.UserCreateDTO;
import br.com.agidoc.agiDoc.dto.user.UserDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation.CompanyWithProcessesAssociation;
import br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation.CompanyWithProcessesAssociationPK;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.repository.CompanyRepository;
import br.com.agidoc.agiDoc.repository.CompanyWithProcessesAssociationRepository;
import br.com.agidoc.agiDoc.repository.DocumentRepository;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Proc;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProcessService {

    private final ProcessRepository processRepository;
    private ObjectMapper objectMapper;
    private final DocumentRepository documentRepository;
    private final CompanyRepository companyRepository;
    private final CompanyWithProcessesAssociationRepository companyWithProcessesAssociationRepository;

    public List<ProcessDTO> list() throws DatabaseException {
        List<Process> processesList = processRepository.findAll();

        processesList.forEach(process -> {
            Hibernate.initialize(process.getCompany());
        });

        return convertListToDTO(processesList);
    }

    public ProcessDTO findById(Integer idProcess) throws RegraDeNegocioException {
        Process process = processRepository.findById(idProcess)
                .orElseThrow(() -> new RegraDeNegocioException("Process not found with the provided ID"));

        ProcessDTO processDTO = convertToDTO(process);

        List<Document> documentList = documentRepository.findAllDocumentsByProcessId(idProcess);

        List<DocumentDTO> documentDTOList = documentList.stream()
                .map(document -> {
                    DocumentDTO documentDTO = objectMapper.convertValue(document, DocumentDTO.class);
//                    documentDTO.setProcessId(processDTO.getProcessId());
                    return documentDTO;
                })
                .collect(Collectors.toList());

        processDTO.setDocumentDTOS(documentDTOList);

        return processDTO;
    }

    public ProcessDTO create(Integer idCompany, ProcessCreateDTO processCreateDto) throws Exception {
        Company company = companyRepository.findById(idCompany)
                .orElseThrow(() -> new RegraDeNegocioException("Company not found with the provided ID"));

        CompanyWithProcessesAssociationPK pk = new CompanyWithProcessesAssociationPK();
        CompanyWithProcessesAssociation companyWithProcessesAssociation = new CompanyWithProcessesAssociation();

        Process process = convertToEntity(processCreateDto);

        Process savedProcess = processRepository.save(process);

        company.getProcess().add(savedProcess);

        companyRepository.save(company);

        pk.setProcessId(savedProcess.getProcessId());
        pk.setCompanyId(company.getCompanyId());

        companyWithProcessesAssociation.setProcessesAssociationPK(pk);

        companyWithProcessesAssociationRepository.save(companyWithProcessesAssociation);

        ProcessDTO processDTO = convertToDTO(savedProcess);

        return processDTO;
    }

    public ProcessDTO update(Integer idProcess, ProcessUpdateDTO processToUpdateDTO) throws Exception {

        Process process = processRepository.findById(idProcess)
                .orElseThrow(() -> new RegraDeNegocioException("Process not found with the provided ID"));

        process.setTitle(processToUpdateDTO.getTitle());
        process.setDescription(processToUpdateDTO.getDescription());
        processRepository.save(process);

        return convertToDTO(process);
    }

    public ProcessDTO setStatus(Integer idProcess, Integer statusWanted) throws Exception {
        Process process = processRepository.findById(idProcess)
                .orElseThrow(() -> new RegraDeNegocioException("Process not found with the provided ID"));

        switch (statusWanted) {
            case 0:
                process.setProcessStatus(ProcessStatus.IN_PROGRESS);
                break;
            case 1:
                process.setProcessStatus(ProcessStatus.COMPLETED);
                break;
            case 2:
                process.setProcessStatus(ProcessStatus.SUSPENDED);
                break;
            case 3:
                process.setProcessStatus(ProcessStatus.ARCHIVED);
                break;
        }
        processRepository.save(process);

        return convertToDTO(process);
    }

    public Process addDocumentToProcess(Integer idProcess, Document document) throws RegraDeNegocioException {
        Process process = processRepository.findById(idProcess)
                .orElseThrow(() -> new RegraDeNegocioException("Process not found with the provided ID"));

        process.getDocuments().add(document);

        return processRepository.save(process);
    }

    private Process convertToEntity(Object dto) {
        return objectMapper.convertValue(dto, Process.class);
    }

    private List<ProcessDTO> convertListToDTO(List<Process> processList) {
        return processList.stream()
                .map(process -> objectMapper.convertValue(process, ProcessDTO.class))
                .collect(Collectors.toList());
    }

    private ProcessDTO convertToDTO(Object object) {
        return objectMapper.convertValue(object, ProcessDTO.class);
    }
}