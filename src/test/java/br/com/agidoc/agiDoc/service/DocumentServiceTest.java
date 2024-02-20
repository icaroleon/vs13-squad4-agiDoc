//package br.com.agidoc.agiDoc.service;
//
//import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
//import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
//import br.com.agidoc.agiDoc.dto.document.DocumentListDTO;
//import br.com.agidoc.agiDoc.dto.document.DocumentUpdateDTO;
//import br.com.agidoc.agiDoc.dto.process.ProcessCreateDTO;
//import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
//import br.com.agidoc.agiDoc.dto.process.ProcessUpdateDTO;
//import br.com.agidoc.agiDoc.exception.DatabaseException;
//import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
//import br.com.agidoc.agiDoc.model.Status;
//import br.com.agidoc.agiDoc.model.company.Company;
//import br.com.agidoc.agiDoc.model.company.Type;
//import br.com.agidoc.agiDoc.model.document.Document;
//import br.com.agidoc.agiDoc.model.process.Process;
//import br.com.agidoc.agiDoc.model.user.Department;
//import br.com.agidoc.agiDoc.model.user.User;
//import br.com.agidoc.agiDoc.repository.CompanyRepository;
//import br.com.agidoc.agiDoc.repository.DocumentRepository;
//import br.com.agidoc.agiDoc.repository.ProcessRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.BeanUtils;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//import static br.com.agidoc.agiDoc.service.ProcessServiceTest.returnProcessUpdateDTO;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("DocumentService - Test")
//class DocumentServiceTest {
//
//    @Mock
//    private Process process = returnProcess();
//    ;
//
//    @Mock
//    private DocumentRepository documentRepository;
//
//    @Mock
//    private ProcessRepository processRepository;
//
//    @Mock
//    private ObjectMapper objectMapper;
//
//    @Mock
//    private CompanyRepository companyRepository;
//
//    @InjectMocks
//    private DocumentService documentService;
//
//    private static DocumentCreateDTO returnDocumentCreateDTO() {
//        Integer randomId = new Random().nextInt();
//        DocumentCreateDTO documentCreateDTO = new DocumentCreateDTO();
//        documentCreateDTO.setExpirationDate(LocalDate.parse("2025-04-04"));
//        documentCreateDTO.setTitle("Edital de Abertura de Processo Licitatório");
//        documentCreateDTO.setFile("document.pdf");
//
//        return documentCreateDTO;
//    }
//
//    private static DocumentDTO returnDocumentDTO() {
//        Integer randomId = new Random().nextInt();
//        DocumentDTO documentDTO = new DocumentDTO();
//
//        documentDTO.setDocumentId(randomId);
//        documentDTO.setProtocol(UUID.randomUUID().toString().substring(0, 6));
//        documentDTO.setExpirationDate(LocalDate.parse("22-05-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//        documentDTO.setSigned(false);
//        documentDTO.setFile("document.pdf");
//        documentDTO.setProcessId(randomId);
//
//        return documentDTO;
//    }
//
//    private static Document returnDocument() {
//        Integer randomId = new Random().nextInt();
//        Document document = new Document();
//
//        document.setDocumentId(randomId);
//        document.setProtocol(UUID.randomUUID().toString().substring(0, 6));
//        document.setExpirationDate(LocalDate.parse("22-05-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//        document.setSigned(false);
//        document.setAttachment(null);
//        document.setStatus(Status.ACTIVE);
//
//        return document;
//    }
//
//    private static DocumentUpdateDTO returnDocumentUpdateDTO() {
//        return new DocumentUpdateDTO(
//                LocalDate.parse("22-05-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")
//                ), "newFile");
//    }
//
//    @Test
//    public void shouldListDocumentsSuccessfully() throws DatabaseException {
//        DocumentDTO documentDTO = returnDocumentDTO();
//        Document document = returnDocument();
//
//        List<Document> documentMockList = new ArrayList<>(Collections.nCopies(3, document));
//        List<DocumentDTO> documentsDTOMockList = new ArrayList<>(Collections.nCopies(3, documentDTO));
//
//        when(documentRepository.findAll()).thenReturn(documentMockList);
//
//        List<DocumentDTO> documentDTOList = documentService.list();
//
//        assertNotNull(documentDTOList);
//        assertEquals(documentDTOList.size(), documentsDTOMockList.size());
//    }
//
//    @Test
//    public void shouldFindDocumentById() throws RegraDeNegocioException {
//        Document document = returnDocument();
//        DocumentDTO documentDTO = returnDocumentDTO();
//        Process process = returnProcess();
//        document.setProcess(process);
//
//        Integer randomId = new Random().nextInt();
//
//        when(documentRepository.returnAllInfosByDocumentId(randomId))
//                .thenReturn(Optional.of(document));
//        when(objectMapper.convertValue(document, DocumentDTO.class)).thenReturn(documentDTO);
//
//        DocumentListDTO documentListDTO = documentService.findById(randomId);
//
//        assertNotNull(documentListDTO);
//        assertNotEquals(documentListDTO, documentDTO);
//    }
//
//    @Test
//    public void shouldCreateDocumentSuccessfully() throws Exception {
//        Integer randomId = new Random().nextInt();
//        Document document = returnDocument();
//        DocumentDTO documentDTO = returnDocumentDTO();
//        DocumentCreateDTO documentCreateDTO = returnDocumentCreateDTO();
//        Set<Document> documents = returnProcess().getDocuments();
//
//        when(processRepository.findById(randomId)).thenReturn(Optional.of(process));
//        when(objectMapper.convertValue(documentCreateDTO, Document.class)).thenReturn(document);
//        when(documentRepository.save(document)).thenReturn(document);
//        when(process.getDocuments()).thenReturn(documents);
//        boolean successfullyAdd = documents.add(document);
//        when(processRepository.save(process)).thenReturn(process);
//        when(objectMapper.convertValue(document, DocumentDTO.class)).thenReturn(documentDTO);
//        documentDTO.setProcessId(process.getProcessId());
//
//        DocumentDTO documentDTOCreated = documentService.create(randomId, documentCreateDTO);
//
//        assertTrue(successfullyAdd);
//        assertEquals(1, process.getDocuments().size());
//        assertNotNull(documentDTOCreated);
//        assertEquals(documentDTOCreated, documentDTO);
//    }
//
//    @Test
//    public void shouldUpdateDocumentSuccessfully() throws Exception {
//        Integer randomId = new Random().nextInt();
//        Document oldDocumentMock = returnDocument();
//        Document newDocumentMock = new Document();
//        DocumentDTO documentDTOMock = new DocumentDTO();
//        DocumentUpdateDTO documentUpdateDTO = returnDocumentUpdateDTO();
//
//        BeanUtils.copyProperties(oldDocumentMock, newDocumentMock);
//
//        when(documentRepository.findById(anyInt())).thenReturn(Optional.of(oldDocumentMock));
//        when(documentRepository.save(any(Document.class))).thenReturn(newDocumentMock);
//        when(objectMapper.convertValue(any(Document.class), eq(DocumentDTO.class))).thenReturn(documentDTOMock);
//
//        DocumentDTO documentDTO = documentService.update(oldDocumentMock.getDocumentId(), documentUpdateDTO);
//
//        assertNotNull(documentDTO);
//        assertNotEquals(oldDocumentMock, documentDTO);
//        assertNotEquals(oldDocumentMock.getExpirationDate(), documentDTO.getExpirationDate());
//    }
//
//    @Test
//    public void shouldSignDocumentSuccessfully() throws Exception {
//        Integer randomId = new Random().nextInt();
//        Document document = returnDocument();
//        DocumentDTO documentDTO = returnDocumentDTO();
//        documentDTO.setSigned(true);
//        Document notSignedDocument = returnDocument();
//        User user = returnUser();
//
//        when(documentRepository.findById(randomId)).thenReturn(Optional.of(document));
//        document.setSigned(true);
//        when(documentRepository.save(document)).thenReturn(document);
//        when(objectMapper.convertValue(document, DocumentDTO.class)).thenReturn(documentDTO);
//
//        documentDTO = documentService.sign(randomId, user.getIdUser());
//
//        assertNotNull(documentDTO);
//        assertNotEquals(document, notSignedDocument);
//        assertNotEquals(documentDTO, notSignedDocument);
//        assertEquals(documentDTO.isSigned(), document.isSigned());
//        assertTrue(documentDTO.isSigned());
//    }
//
//    private Process returnProcess() {
//        Integer randomId = new Random().nextInt();
//        Process process = new Process();
//        Set<Document> documents = new HashSet<>();
//        process.setProcessId(randomId);
//        process.setProcessNumber(UUID.randomUUID().toString().substring(0, 6));
//        process.setTitle("Licitação de Obra Pública");
//        process.setDescription("Licitação de Obra de Capeamento de Via Pública em Porto Alegre");
//        process.setCompany(returnCompany());
//        process.setProcessStatus(null);
//        process.setDocuments(documents);
//
//        return process;
//    }
//
//    private Company returnCompany() {
//        Company companyMock = new Company();
//        Set<Process> processes = new HashSet<>();
//
//        companyMock.setCompanyId(1);
//        companyMock.setCompanyName("Random Company");
//        companyMock.setType(Type.INSTITUTION);
//        companyMock.setStatus(Status.ACTIVE);
//        companyMock.setProcess(processes);
//
//        return companyMock;
//    }
//
//    private static User returnUser() {
//        User userEntity = new User();
//
//        userEntity.setIdUser(1);
//        userEntity.setRegistration("aabb123");
//        userEntity.setName("Joao Silva");
//        userEntity.setUser("joao3352");
//        userEntity.setPassword("senhajoao3352");
//        userEntity.setPosition("Analista de Software");
//        userEntity.setStatus(Status.ACTIVE);
//        userEntity.setEmail("joao@dbccompany.com.br");
//        userEntity.setDepartment(Department.SECRETARIA_FAZENDA);
//
//        return userEntity;
//    }
//}