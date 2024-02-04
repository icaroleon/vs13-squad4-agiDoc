package br.com.agidoc.agiDoc.service;


import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentListDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentUpdateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.pk.DocumentAssociation;
import br.com.agidoc.agiDoc.model.pk.DocumentsAssociationsPk;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.repository.DocumentAssociationRepository;
import br.com.agidoc.agiDoc.repository.DocumentRepository;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentAssociationRepository daRepository;
    private final ProcessRepository processRepository;
    private final ObjectMapper objectMapper;
    private final ProcessService processService;

    public List<DocumentDTO> list() throws DatabaseException {
        List<Document> documentsList = documentRepository.findAll();

        return convertListToDTO(documentsList);
    }

    public DocumentListDTO findById(Integer idDocument) throws RegraDeNegocioException {
        Document documentInfos = documentRepository.returnAllInfosByDocumentId(idDocument)
                .orElseThrow(() -> new RegraDeNegocioException("Document not found with the provided ID"));

        DocumentDTO documentDTO = convertToDTO(documentInfos);

        DocumentListDTO documentListDTO = new DocumentListDTO();
        documentListDTO.setDocument(documentDTO);
        documentListDTO.setProcessId(documentInfos.getProcesses().getProcessId());
        documentListDTO.setProcessId(documentInfos.getProcesses().getProcessId());
        documentListDTO.setProcessNumber(documentInfos.getProcesses().getProcessNumber());
        documentListDTO.setTitle(documentInfos.getProcesses().getTitle());
        documentListDTO.setDescription(documentInfos.getProcesses().getDescription());
        documentListDTO.setProcessStatus(documentInfos.getProcesses().getProcessStatus());

        return documentListDTO;
    }

    public DocumentDTO create(Integer idProcess, DocumentCreateDTO documentCreateDto) throws Exception {
        processRepository.findById(idProcess)
                .orElseThrow(() -> new RegraDeNegocioException("Process not found with the provided ID"));

//        DocumentsAssociationsPk pk = new DocumentsAssociationsPk();
        DocumentAssociation da = new DocumentAssociation();

        Document document = convertToEntity(documentCreateDto);
        Process process = processService.addDocumentToProcess(idProcess, document);

        document = documentRepository.save(document);

//        pk.setDocumentId(document.getDocumentId());

//        pk.setProcessId(idProcess);

        da.setDocumentId(document.getDocumentId());
        da.setProcessId(process.getProcessId());
        daRepository.save(da);

//        da.setDocumentsAssociationId(pk);
//        da.setDocument(document);
//        da.setProcess(process);
//        daRepository.save(da);

        return convertToDTO(document);
    }

    public DocumentDTO update(Integer idDocument, DocumentUpdateDTO documentUpdateDateDTO) throws Exception {

        Document document = documentRepository.findById(idDocument)
                .orElseThrow(() -> new RegraDeNegocioException("Document not found with the provided ID"));

        //document.setFile(documentUpdateDateDTO.getFile());
        documentRepository.save(document);

        return convertToDTO(document);
    }

//    public void delete(Integer id) throws Exception {
//        documentRepository.delete(id);
//        //TODO decidir se haverá essa possibilidade
//    }

    public DocumentDTO sign(Integer idDocument, Integer userId) throws Exception {
        Document document = documentRepository.findById(idDocument)
                .orElseThrow(() -> new RegraDeNegocioException("Document not found with the provided ID"));

        document.setSigned(true);
        documentRepository.save(document);

        return convertToDTO(document);
    }

    public List<DocumentDTO> convertListToDTO(List<Document> documentsList) {
        return documentsList.stream()
                .map(document -> objectMapper.convertValue(document, DocumentDTO.class))
                .collect(Collectors.toList());
    }

    private Document convertToEntity(DocumentCreateDTO documentCreateDTO) {
        return objectMapper.convertValue(documentCreateDTO, Document.class);
    }

    private DocumentDTO convertToDTO(Document entity) {
        return objectMapper.convertValue(entity, DocumentDTO.class);
    }
}