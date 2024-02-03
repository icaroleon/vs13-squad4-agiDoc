package br.com.agidoc.agiDoc.service;


import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
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
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ProcessRepository processRepository;
    //private final DocumentAssociationRepository daRepository;
    private final ObjectMapper objectMapper;

    public List<DocumentDTO> list() throws DatabaseException {
        List<Document> documentsList = documentRepository.findAll();

        return convertListToDTO(documentsList);
    }

    public DocumentDTO findById(Integer idDocument) throws RegraDeNegocioException {
        Document document = documentRepository.findById(idDocument)
                .orElseThrow(() -> new RegraDeNegocioException("Document not found with the provided ID"));

        return convertToDTO(document);
    }

    public DocumentDTO create(Integer idProcess, DocumentCreateDTO documentCreateDto) throws Exception {
        Process process = processRepository.findById(idProcess)
                .orElseThrow(() -> new RegraDeNegocioException("Process not found with the provided ID"));

        Document document = convertToEntity(documentCreateDto);
        documentRepository.save(document);

        process.getDocuments().add(document);
        processRepository.save(process);

        document = documentRepository.save(document);
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

    private List<DocumentDTO> convertListToDTO(List<Document> processList) {
        return processList.stream()
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