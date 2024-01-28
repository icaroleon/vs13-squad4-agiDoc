package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProcessService {

    private final ProcessRepository processRepository;

    public Process findById(Integer idProcess) throws Exception {
        return processRepository.getProcessById(idProcess);
    }

    public List<Process> list() throws DatabaseException {
        return processRepository.list();
    }
}