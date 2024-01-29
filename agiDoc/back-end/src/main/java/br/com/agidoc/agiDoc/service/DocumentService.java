package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentUpdateInfosDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.repository.DocumentRepository;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ProcessRepository processRepository;
    private final ProcessService processService;
    private final ObjectMapper objectMapper;
    private final ProcessAndDocumentsEmailService emailService;

    public DocumentDTO create(Integer idProcess, DocumentCreateDTO documentCreateDTO) throws Exception {
        Process process = processService.findProcessById(idProcess);
        Document document = objectMapper.convertValue(documentCreateDTO, Document.class);
        document = documentRepository.create(document);

        DocumentDTO documentDTO = findDocByIdAndConvertedToDto(document.getId());
        this.emailService.sendEmail(documentDTO, "createDocument");

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
                    ProcessDTO processDTO = objectMapper.convertValue(process, ProcessDTO.class);
                    documentDTO.setProcessDTO(processDTO);
                }
            }
            documentsDtoList.add(documentDTO);
        }
        return documentsDtoList;
    }

        public DocumentDTO update(Integer idDocument, DocumentUpdateInfosDTO documentUpdateInfosDTO) throws Exception {

        Document document = objectMapper.convertValue(documentUpdateInfosDTO, Document.class);
        documentRepository.update(idDocument, document);
        DocumentDTO documentDTO = this.findDocByIdAndConvertedToDto(idDocument);

        return documentDTO;
    }

    public void delete(Integer id) throws Exception {
        documentRepository.delete(id);
        //TODO RETORNAR METODO BOOILEAN EM VEZ DE EXCEPTION
    }

    public DocumentDTO sign(Integer idDocument, Integer userId) throws Exception {
        boolean signature =  documentRepository.sign(idDocument, userId);

        return findDocByIdAndConvertedToDto(idDocument);
    }

    public DocumentDTO findDocByIdAndConvertedToDto(Integer idDocument) throws Exception {

        Document document = documentRepository.findById(idDocument);
        Process process = processService.findProcessById(document.getProcessId());

        DocumentDTO documentDTO = objectMapper.convertValue(document, DocumentDTO.class);
        ProcessDTO processDTO = objectMapper.convertValue(process, ProcessDTO.class);
        documentDTO.setProcessDTO(processDTO);

        return documentDTO;
    }
}