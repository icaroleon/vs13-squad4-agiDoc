package br.com.agidoc.agiDoc.controller.document;


import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentUpdateInfosDTO;
import br.com.agidoc.agiDoc.service.DocumentService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<DocumentDTO> update(@NotNull @PathVariable("idDocument") Integer idDocument, @Valid @RequestBody DocumentUpdateInfosDTO documentUpdateInfosDTO) throws Exception {
        return new ResponseEntity<>(this.documentService.update(idDocument, documentUpdateInfosDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idDocument}")
    public ResponseEntity<String> delete(@PathVariable("idDocument") Integer idDocument) throws Exception {
        try {
            this.documentService.delete(idDocument);
            return ResponseEntity.ok("Document deleted successfully");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body ("Failed to delete the document:" + e.getMessage());
        }
    }

    @PutMapping("{idDocument}/sign/{userId}")
    public ResponseEntity<String> sign(@Valid @PathVariable ("idDocument") Integer idDocument, @Valid @PathVariable ("userId") Integer userId) throws Exception {
        try {
            documentService.sign(idDocument, userId);
            return ResponseEntity.ok("Document signed successfully");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body ("Failed to sign the document:" + e.getMessage());
        }
    }

}