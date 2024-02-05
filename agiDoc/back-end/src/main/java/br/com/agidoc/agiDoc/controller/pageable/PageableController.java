package br.com.agidoc.agiDoc.controller.pageable;

import br.com.agidoc.agiDoc.model.document.Document;

import br.com.agidoc.agiDoc.service.PageableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pageable")
@RequiredArgsConstructor
public class PageableController {
    private final PageableService pageableService;

    @GetMapping("/documents")
    public ResponseEntity<Page<Document>> listPageableDocuments(@RequestParam(defaultValue = "0") Integer requestPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(requestPage, pageSize);
        Page<Document> all = pageableService.findAllDocuments(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/list-ordered-pageable/documents")
    public ResponseEntity<Page<Document>> listPageExpirationDateDocuments(@PageableDefault(size = 10, page = 10, sort = {"expirationDate"}) Pageable pageable) {
        Page<Document> all = pageableService.findAllDocuments(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/users")
    public ResponseEntity<Page<Document>> listPageableUsers(@RequestParam(defaultValue = "0") Integer requestPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(requestPage, pageSize);
        Page<Document> all = pageableService.findAllUsers(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/list-ordered-pageable/users")
    public ResponseEntity<Page<Document>> listPageableOrderedUsers(@PageableDefault(size = 10, page = 10, sort = {"name"}) Pageable pageable) {
        Page<Document> all = pageableService.findAllUsers(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/list-pageable-actives/users")
    public ResponseEntity<Page<Document>> listPageableActiveUsers(@RequestParam(defaultValue = "0") Integer requestPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(requestPage, pageSize);
        Page<Document> all = pageableService.findAllActivesUsers(pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/list-pageable-inactives/users")
    public ResponseEntity<Page<Document>> listPageableInctiveUsers(@RequestParam(defaultValue = "0") Integer requestPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(requestPage, pageSize);
        Page<Document> all = pageableService.findAllInactiveUsers(pageable);
        return ResponseEntity.ok(all);
    }

}
