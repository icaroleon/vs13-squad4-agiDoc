package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessCreateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.company.Type;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import br.com.agidoc.agiDoc.model.user.Department;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.repository.CompanyRepository;
import br.com.agidoc.agiDoc.repository.DocumentRepository;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import br.com.agidoc.agiDoc.repository.ProcessStatusReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProcessService - Test")
//@MockitoSettings(strictness = Strictness.LENIENT)
class ProcessServiceTest {
    @Mock
    Company companyMock = returnCompany();

    @Mock
    UserService userService;

    @Mock
    ProcessStatusReportService processStatusReportService;

    @Mock
    private ProcessRepository processRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private DocumentRepository documentRepository;
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private ProcessService processService;

    private static ProcessCreateDTO returnProcessCreateDTO() {
        return new ProcessCreateDTO(
                "Licitação de Obra Pública",
                "Licitação de Obra de Capeamento de Via Pública em Porto Alegre");
    }

    static ProcessUpdateDTO returnProcessUpdateDTO() {
        return new ProcessUpdateDTO(
                "Licitação de Via Pública",
                "Licitação de Obra de Capeamento de Via Pública em Belo Horizonte");
    }

    @Test
    public void shouldListSuccessful() throws DatabaseException {
        List<Process> mockList = new ArrayList<>(Collections.nCopies(3, returnProcess()));

        when(processRepository.findAll()).thenReturn(mockList);

        List<ProcessDTO> DTOList = processService.list();

        assertNotNull((DTOList));
        assertEquals(mockList.size(), DTOList.size());
    }

    @Test
    public void shouldReturnProcessDTOByHisId() throws RegraDeNegocioException {
        Optional<Process> processMock = Optional.of(returnProcess());

        Document doc1 = new Document();
        Document doc2 = new Document();
        List<Document> documentsList = Arrays.asList(doc1, doc2);

        DocumentDTO docDto1 = new DocumentDTO();
        DocumentDTO docDto2 = new DocumentDTO();

        ProcessDTO processDTOMock = returnProcessDTO();
        Integer randomId = new Random().nextInt();

        when(processRepository.findById(randomId)).thenReturn(processMock);
        when(documentRepository.findAllDocumentsByProcessId(randomId))
                .thenReturn(documentsList);
        when(objectMapper.convertValue(doc1, DocumentDTO.class)).thenReturn(docDto1);
        when(objectMapper.convertValue(doc2, DocumentDTO.class)).thenReturn(docDto2);
        when(objectMapper.convertValue(processMock.get(), ProcessDTO.class)).thenReturn(processDTOMock);

        ProcessDTO processDTOReturned = processService.findById(randomId);
        assertNotNull(processDTOReturned);
        assertEquals(processDTOReturned, processDTOMock);
    }

    @Test
    public void shouldCreateProcessSuccessfully() throws Exception {
        Integer randomId = new Random().nextInt();
        Set<Process> processes = companyMock.getProcess();
        ProcessCreateDTO processCreateDTOMock = returnProcessCreateDTO();
        Process processMock = returnProcess();
        ProcessDTO processDTOMock = returnProcessDTO();

        when(companyRepository.findById(randomId)).thenReturn(Optional.of(companyMock));
        when(objectMapper.convertValue(processCreateDTOMock, Process.class))
                .thenReturn(processMock);
        when(processRepository.save(any(Process.class))).thenReturn(processMock);
        when(companyMock.getProcess()).thenReturn(processes);
        boolean successfullyAdd = processes.add(processMock);
        when(companyRepository.save(companyMock)).thenReturn(companyMock);
        when(objectMapper.convertValue(processMock, ProcessDTO.class)).thenReturn(processDTOMock);

        ProcessDTO processDTOCreated = processService.create(randomId, processCreateDTOMock);

        assertTrue(successfullyAdd);
        assertEquals(1, companyMock.getProcess().size());
        assertNotNull(processDTOCreated);
        assertEquals(processDTOCreated, processDTOMock);
    }

    @Test
    public void shouldUpdateProcessSuccessfully() throws Exception {
        Integer randomId = new Random().nextInt();
        Process oldProcessMock = returnProcess();
        Process newProcessMock = new Process();
        ProcessDTO processDTOMock = returnProcessDTO();
        ProcessUpdateDTO processUpdateDTO = returnProcessUpdateDTO();

        BeanUtils.copyProperties(oldProcessMock, newProcessMock);

        newProcessMock.setTitle("Teste");
        newProcessMock.setDescription("Teste");


        when(processRepository.findById(anyInt())).thenReturn(Optional.of(oldProcessMock));
        when(processRepository.save(any(Process.class))).thenReturn(newProcessMock);
        when(objectMapper.convertValue(any(Process.class), eq(ProcessDTO.class))).thenReturn(processDTOMock);

        ProcessDTO processDTO = processService.update(oldProcessMock.getProcessId(), processUpdateDTO);

        assertNotNull(processDTO);
        assertNotEquals(oldProcessMock, processDTO);
        assertNotEquals(oldProcessMock.getTitle(), processDTO.getTitle());
        assertNotEquals(oldProcessMock.getDescription(), processDTO.getDescription());
    }

    @ParameterizedTest
    @EnumSource(ProcessStatus.class)
    public void shouldSetStatusSuccessfully(ProcessStatus processStatus) throws Exception {
        Integer randomId = new Random().nextInt();
        ProcessDTO processDTOMock = returnProcessDTO();
        Process oldProcessMock = returnProcess();
        Process newProcessMock = returnProcess();
        User user = returnUser();

        when(processRepository.findById(randomId)).thenReturn(Optional.of(newProcessMock));
        when(userService.getLoggedUser()).thenReturn(Optional.of(user));
        newProcessMock.setProcessStatus(processStatus);
        when(processRepository.save(eq(newProcessMock))).thenReturn(newProcessMock);
        when(objectMapper.convertValue(newProcessMock, ProcessDTO.class)).thenReturn(processDTOMock);

        processService.setStatus(randomId, processStatus.ordinal());

        assertNotEquals(oldProcessMock.getProcessStatus(), newProcessMock.getProcessStatus());
        assertEquals(processStatus, newProcessMock.getProcessStatus());
    }

    private Process returnProcess() {
        Integer randomId = new Random().nextInt();
        Process process = new Process();
        process.setProcessId(randomId);
        process.setProcessNumber(UUID.randomUUID().toString().substring(0, 6));
        process.setTitle("Licitação de Obra Pública");
        process.setDescription("Licitação de Obra de Capeamento de Via Pública em Porto Alegre");
        process.setCompany(companyRepository.getById(1));
        process.setProcessStatus(null);

        return process;
    }

    private ProcessDTO returnProcessDTO() {
        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setProcessId(1);
        processDTO.setProcessNumber(UUID.randomUUID().toString().substring(0, 6));
        processDTO.setTitle("Licitação de Obra Pública");
        processDTO.setDescription("Licitação de Obra de Capeamento de Via Pública em Porto Alegre");
        processDTO.setProcessStatus(null);
        processDTO.setCompany(companyRepository.getById(1));

        return processDTO;
    }

    private Company returnCompany() {
        Company companyMock = new Company();
        Set<Process> processes = new HashSet<>();

        companyMock.setCompanyId(1);
        companyMock.setCompanyName("Random Company");
        companyMock.setType(Type.INSTITUTION);
        companyMock.setStatus(Status.ACTIVE);
        companyMock.setProcess(processes);

        return companyMock;
    }

    private static User returnUser() {
        User userEntity = new User();

        userEntity.setIdUser(1);
        userEntity.setRegistration("aabb123");
        userEntity.setName("Joao Silva");
        userEntity.setUser("joao3352");
        userEntity.setPassword("senhajoao3352");
        userEntity.setPosition("Analista de Software");
        userEntity.setStatus(Status.ACTIVE);
        userEntity.setEmail("joao@dbccompany.com.br");
        userEntity.setDepartment(Department.SECRETARIA_FAZENDA);

        return userEntity;
    }
}