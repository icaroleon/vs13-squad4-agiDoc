package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessCreateDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessDTO;
import br.com.agidoc.agiDoc.dto.process.ProcessUpdateDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Proc;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProcessService {

    private final ProcessRepository processRepository;
    private ObjectMapper objectMapper;
    private  final EmailService emailService;

    public ProcessDTO findProcessByIdAndReturnDTO(Integer idProcess) throws Exception {
        Process process = processRepository.getProcessById(idProcess);

        ProcessDTO processDTO = objectMapper.convertValue(process, ProcessDTO.class);

        return processDTO;
    }

    public List<ProcessDTO> list() throws DatabaseException {
        ArrayList<Process> processesList = processRepository.list();
        ArrayList<ProcessDTO> processesDtoList = new ArrayList<>();

        for(Process process : processesList) {
            ProcessDTO processDTO = objectMapper.convertValue(process, ProcessDTO.class);
            processesDtoList.add(processDTO);
        }

        return processesDtoList;
    }

    public ProcessDTO create(@Valid ProcessCreateDTO processCreateDto) throws Exception {
        Process process = objectMapper.convertValue(processCreateDto, Process.class);
        process = processRepository.create(process);

        ProcessDTO processDTO = objectMapper.convertValue(process, ProcessDTO.class);
        emailService.sendEmail(processDTO, "createProcess");
        return  processDTO;
    }

    public ProcessDTO update(Integer idProcess, ProcessUpdateDTO processCreateDTO) throws Exception {
        Process process = objectMapper.convertValue(processCreateDTO, Process.class);
        process = processRepository.update(idProcess, process);
        process.setProcessId(idProcess);

        ProcessDTO processDTO = objectMapper.convertValue(process, ProcessDTO.class);
        emailService.sendEmail(processDTO, "updateProcess");
        return  processDTO;
    }

    public void delete(Integer idProcess) throws Exception {
        processRepository.delete(idProcess);
    }

    public Process findProcessById(Integer idProcess) throws Exception {
        Process process = processRepository.getProcessById(idProcess);

        return process;
    }
}