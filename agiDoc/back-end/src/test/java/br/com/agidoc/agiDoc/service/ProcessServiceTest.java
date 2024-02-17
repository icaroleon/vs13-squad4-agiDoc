package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.process.ProcessCreateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.exception.RegraDeNegocioException;
import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.company.Type;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import br.com.agidoc.agiDoc.repository.CompanyRepository;
import br.com.agidoc.agiDoc.repository.CompanyWithProcessesAssociationRepository;
import br.com.agidoc.agiDoc.repository.DocumentRepository;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProcessService - Test")
class ProcessServiceTest {

    ProcessCreateDTO processCreateDTO;
    ProcessDTO processDTO;
    Process process;

    @Mock
    Company companyMock = returnCompany();

    @Mock
    private ProcessRepository processRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private DocumentRepository documentRepository;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyWithProcessesAssociationRepository companyWithProcessesAssociationRepository;
    @InjectMocks
    private ProcessService processService;

    private static ProcessCreateDTO returnProcessCreateDTO() {
        ProcessCreateDTO processCreateDTO = new ProcessCreateDTO(
                "Licitação de Obra Pública",
                "Licitação de Obra de Capeamento de Via Pública em Porto Alegre");
        return processCreateDTO;
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
        List<Document> documentsList = new ArrayList<>();
        ProcessDTO processDTOMock = returnProcessDTO();
        Integer randomId = new Random().nextInt();

        when(processRepository.findById(randomId)).thenReturn(processMock);
        when(documentRepository.findAllDocumentsByProcessId(randomId))
                .thenReturn(documentsList);
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
        processes.add(processMock);
        when(companyRepository.save(companyMock)).thenReturn(companyMock);
        when(objectMapper.convertValue(processMock, ProcessDTO.class)).thenReturn(processDTOMock);

        ProcessDTO processDTOCreated = processService.create(randomId, processCreateDTOMock);

        assertEquals(1, companyMock.getProcess().size());
        assertNotNull(processDTOCreated);
        assertEquals(processDTOCreated, processDTOMock);
    }

    private Process returnProcess() {
        Process process = new Process();
        process.setProcessId(1);
        process.setProcessNumber(UUID.randomUUID().toString().substring(0, 6));
        process.setTitle("Licitação de Obra Pública");
        process.setDescription("Licitação de Obra de Capeamento de Via Pública em Porto Alegre");
        process.setProcessStatus(ProcessStatus.IN_PROGRESS);
        process.setCompany(companyRepository.getById(1));

        return process;
    }

    private ProcessDTO returnProcessDTO() {
        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setProcessId(1);
        processDTO.setProcessNumber(UUID.randomUUID().toString().substring(0, 6));
        processDTO.setTitle("Licitação de Obra Pública");
        processDTO.setDescription("Licitação de Obra de Capeamento de Via Pública em Porto Alegre");
        processDTO.setProcessStatus(ProcessStatus.IN_PROGRESS);
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

}