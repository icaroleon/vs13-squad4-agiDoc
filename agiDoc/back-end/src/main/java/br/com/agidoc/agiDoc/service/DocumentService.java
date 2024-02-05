package br.com.agidoc.agiDoc.service;


import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentListDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation.DocumentsWithProcessesAssociation;
import br.com.agidoc.agiDoc.model.associations.pk.documentsWithProcesses.documentsWithProcessesAssociation.DocumentsWithProcessesAssociationPk;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.repository.DocumentRepository;
import br.com.agidoc.agiDoc.repository.DocumentsWithProcessesAssociationRepository;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentsWithProcessesAssociationRepository documentsProcessesAssociationRepository;
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
        documentListDTO.setProcessId(documentInfos.getProcess().getProcessId());
        documentListDTO.setProcessId(documentInfos.getProcess().getProcessId());
        documentListDTO.setProcessNumber(documentInfos.getProcess().getProcessNumber());
        documentListDTO.setTitle(documentInfos.getProcess().getTitle());
        documentListDTO.setDescription(documentInfos.getProcess().getDescription());
        documentListDTO.setProcessStatus(documentInfos.getProcess().getProcessStatus());

        return documentListDTO;
    }

    public DocumentDTO create(Integer idProcess, DocumentCreateDTO documentCreateDto) throws Exception {
        // Busca um processo pelo ID fornecido. Lança uma exceção customizada se não encontrado.
        Process process = processRepository.findById(idProcess)
                .orElseThrow(() -> new RegraDeNegocioException("Process not found with the provided ID"));

        // Cria uma chave primária composta para a associação entre documentos e processos.
        DocumentsWithProcessesAssociationPk pk = new DocumentsWithProcessesAssociationPk();
        // Cria uma nova instância da entidade que representa a associação entre documentos e processos.
        DocumentsWithProcessesAssociation documentsProcessesAssociation = new DocumentsWithProcessesAssociation();

        // Converte o DTO do documento recebido para a entidade Document.
        Document document = convertToEntity(documentCreateDto);

        // Salva o documento no repositório e retorna a entidade salva.
        Document savedDocument = documentRepository.save(document);
        // Adiciona o documento salvo ao conjunto de documentos do processo.
        process.getDocuments().add(savedDocument);
        // Salva o processo, o que deve atualizar a coleção de documentos associada ao processo.
        processRepository.save(process);

        // Define os IDs do documento e do processo na chave primária composta da associação.
        pk.setDocumentId(savedDocument.getDocumentId());
        pk.setProcessId(process.getProcessId());

        // Configura a chave primária composta na entidade de associação.
        documentsProcessesAssociation.setDocumentsAssociationsPk(pk);
        // Salva a entidade de associação no repositório correspondente.
        documentsProcessesAssociationRepository.save(documentsProcessesAssociation);

        // Converte o documento salvo para um DTO para ser retornado.
        DocumentDTO documentDTO = convertToDTO(savedDocument);
        // Configura o ID do processo no DTO do documento.
        documentDTO.setProcessId(process.getProcessId());

        // Retorna o DTO do documento.
        return documentDTO;
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