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
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.repository.DocumentRepository;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Proc;
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

    public List<ProcessDTO> list() throws DatabaseException {
        List<Process> processesList = processRepository.findAll();

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

    public ProcessDTO create(ProcessCreateDTO processCreateDto) throws Exception {
        Process process = convertToEntity(processCreateDto);
        process = processRepository.save(process);
        return convertToDTO(process);
    }

    public ProcessDTO update(Integer idProcess, ProcessUpdateDTO processToUpdateDTO) throws Exception {

        Process process = processRepository.findById(idProcess)
                .orElseThrow(() -> new RegraDeNegocioException("Process not found with the provided ID"));

        process.setTitle(processToUpdateDTO.getTitle());
        process.setDescription(processToUpdateDTO.getDescription());
        processRepository.save(process);

        return convertToDTO(process);
    }

    public void setStatus(Integer idProcess, Integer statusWanted) throws Exception {
        Process process = processRepository.findById(idProcess)
                .orElseThrow(() -> new RegraDeNegocioException("Process not found with the provided ID"));

        switch (statusWanted) {
            case 1:
                process.setProcessStatus(ProcessStatus.IN_PROGRESS);
            case 2:
                process.setProcessStatus(ProcessStatus.COMPLETED);
            case 3:
                process.setProcessStatus(ProcessStatus.SUSPENDED);
            case 4:
                process.setProcessStatus(ProcessStatus.ARCHIVED);
            case 5:
                process.setProcessStatus(ProcessStatus.INACTIVE);

        }
        processRepository.save(process);
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