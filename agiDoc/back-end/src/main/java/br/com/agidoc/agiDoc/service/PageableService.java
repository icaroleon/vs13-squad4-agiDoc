package br.com.agidoc.agiDoc.service;

import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.document.Document;

import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.user.User;
import br.com.agidoc.agiDoc.repository.DocumentRepository;
import br.com.agidoc.agiDoc.repository.ProcessRepository;
import br.com.agidoc.agiDoc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PageableService {
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final ProcessRepository processRepository;

    public Page findAllDocuments(Pageable pageable) {
        Page<Document> documentPage = documentRepository.findAll(pageable);
        return documentPage;
    }

    public Page findAllUsers(Pageable pageable) {
        Page<User> UsersPage = userRepository.findAll(pageable);
        return UsersPage;
    }

    public Page findAllActivesUsers(Pageable pageable) {
        Page<User> UsersPage = userRepository.findUsersByStatus(Status.ACTIVE, pageable);
        return UsersPage;
    }

    public Page findAllInactiveUsers(Pageable pageable) {
        Page<User> UsersPage = userRepository.findUsersByStatus(Status.INACTIVE, pageable);
        return UsersPage;
    }

    public Page findAllProcesses(Pageable pageable) {
        Page<Process> process = processRepository.findAll(pageable);
        return process;
    }
}
