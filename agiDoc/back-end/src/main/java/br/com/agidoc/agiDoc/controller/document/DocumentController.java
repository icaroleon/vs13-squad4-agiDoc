package br.com.agidoc.agiDoc.controller.document;


import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/document")
@AllArgsConstructor
@RestController
@Validated
public class DocumentController implements IDocumentController {
    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> list() throws Exception {
        return new ResponseEntity<>(this.documentService.list(), HttpStatus.OK);
    }

    @GetMapping("/{idDocument}")
    public ResponseEntity<DocumentDTO> findById(@NotNull @PathVariable("idDocument") Integer idDocument) throws Exception {
        return new ResponseEntity<>(this.documentService.findDocByIdAndConvertedToDto(idDocument), HttpStatus.OK);
    }

    @PostMapping("/{idProcess}/process")
    public ResponseEntity<DocumentDTO> create(@NotNull @PathVariable("idProcess") Integer idProcess, @Valid @RequestBody DocumentCreateDTO documentCreateDTO) throws Exception {
        return new ResponseEntity<>(this.documentService.create(idProcess, documentCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{idDocument}")
    public ResponseEntity<DocumentDTO> update(@NotNull @PathVariable("idDocument") Integer idDocument, @Valid @RequestBody DocumentCreateDTO documentCreateDTO) throws Exception {
        return new ResponseEntity<>(this.documentService.update(idDocument, documentCreateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idDocument}")
    public ResponseEntity<Void> delete(@PathVariable("idDocument") Integer idDocument) throws Exception {
        this.documentService.delete(idDocument);
        return ResponseEntity.ok().build();
    }
}