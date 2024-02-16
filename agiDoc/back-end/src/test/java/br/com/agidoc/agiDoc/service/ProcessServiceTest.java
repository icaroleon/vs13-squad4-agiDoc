package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.process.ProcessCreateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProcessService - Test")
class ProcessServiceTest {

    ProcessCreateDTO processCreateDTO;
    ProcessDTO processDTO;
    Process process;
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

    @Test
    public void shouldListSuccessful() throws DatabaseException {
        List<Process> mockList = new ArrayList<>(Collections.nCopies(3, returnProcess()));

        when(processRepository.findAll()).thenReturn(mockList);

        List<ProcessDTO> DTOList = processService.list();

        assertNotNull((DTOList));
        assertEquals(mockList.size(), DTOList.size());
    }


    private static ProcessCreateDTO returnProcessCreateDTO() {
        ProcessCreateDTO processCreateDTO = new ProcessCreateDTO(
                "Licitação de Obra Pública",
                "Licitação de Obra de Capeamento de Via Pública em Porto Alegre");
        return processCreateDTO;
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

    private ProcessDTO returnProcessDTO(){
        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setProcessId(1);
        processDTO.setProcessNumber(UUID.randomUUID().toString().substring(0, 6));
        processDTO.setTitle("Licitação de Obra Pública");
        processDTO.setDescription("Licitação de Obra de Capeamento de Via Pública em Porto Alegre");
        processDTO.setProcessStatus(ProcessStatus.IN_PROGRESS);
        processDTO.setCompany(companyRepository.getById(1));

        return processDTO;
    }

}