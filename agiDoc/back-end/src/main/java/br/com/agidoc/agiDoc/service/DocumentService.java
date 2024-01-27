package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.repository.DocumentRepository;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ProcessRepository processRepository;
    private final ProcessService processService;
    private final ObjectMapper objectMapper;

    public DocumentDTO create(Integer idProcess, DocumentCreateDTO documentCreateDTO) throws Exception {
        Process process = processService.findById(idProcess);
        if (process == null) {
            throw new RegraDeNegocioException("Pessoa para o id " + idProcess + " não encontrado.");
        }
        Document document = objectMapper.convertValue(documentCreateDTO, Document.class);
        document = documentRepository.create(document);
        DocumentDTO documentDTO = objectMapper.convertValue(document, DocumentDTO.class);
        documentDTO.setProcess(process);
        return documentDTO;
    }

    public List<DocumentDTO> list() throws Exception {
        ArrayList<Document> documentsList = documentRepository.list();
        ArrayList<Process> processList = processRepository.list();

        ArrayList<DocumentDTO> documentsDtoList = new ArrayList<>();
        for (Document document : documentsList) {
            DocumentDTO documentDTO = objectMapper.convertValue(document, DocumentDTO.class);
            for (Process process : processList) {
                if (process.getProcessId().equals(document.getProcessId())) {
                    documentDTO.setProcess(process);
                }
            }
            documentsDtoList.add(documentDTO);
        }
        return documentsDtoList;
    }

    public Document update(Integer idDocument, Document document) throws DatabaseException {
        return this.documentRepository.update(idDocument, document);
    }

    public void delete(Integer id) throws DatabaseException {
        documentRepository.delete(id);
    }

    public DocumentDTO findById(Integer idDocument) throws Exception {

        Document document = documentRepository.findById(idDocument);
        Process process = processService.findById(document.getProcessId());

        DocumentDTO documentDTO = objectMapper.convertValue(document, DocumentDTO.class);

        documentDTO.setProcess(process);

        return documentDTO;
    }
}
