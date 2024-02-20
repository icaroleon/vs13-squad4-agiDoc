package br.com.agidoc.agiDoc.controller.document;


import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentListDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentUpdateDTO;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.service.DocumentService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "document", description = "CRUD of document")
@RestController
@Validated
//TODO IMPLEMENTAR DOCUMENT CONTROLLER
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> list() throws Exception {
        return new ResponseEntity<>(this.documentService.list(), HttpStatus.OK);
    }

    @GetMapping("/{idDocument}")
    public ResponseEntity<DocumentListDTO> findById(@NotNull @PathVariable("idDocument") Integer idDocument) throws Exception {
        return new ResponseEntity<>(this.documentService.findById(idDocument), HttpStatus.OK);
    }

    @PostMapping("/{idProcess}/process")
    public ResponseEntity<DocumentDTO> create(@NotNull @PathVariable("idProcess") Integer idProcess,
                                              @Valid @RequestBody DocumentCreateDTO documentCreateDTO) throws Exception {
        return new ResponseEntity<>(this.documentService.create(idProcess, documentCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{idDocument}")
    public ResponseEntity<DocumentDTO> update(@NotNull @PathVariable("idDocument") Integer idDocument,
                                              @Valid @RequestBody DocumentUpdateDTO documentUpdateInfosDTO) throws Exception {
        return new ResponseEntity<>(this.documentService.update(idDocument, documentUpdateInfosDTO), HttpStatus.OK);
    }

//TODO DECIDIR SE SERÁ POSSÍVEL DELETAR UM DOCUMENT
//    @DeleteMapping("/{idDocument}")
//    public ResponseEntity<String> delete(@PathVariable("idDocument") Integer idDocument) throws Exception {
//        try {
//            this.documentService.delete(idDocument);
//            return ResponseEntity.ok("Document deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.NOT_MODIFIED)
//                    .body ("Failed to delete the document:" + e.getMessage());
//        }
//    }

    @PutMapping("{idDocument}/sign/{userId}")
    public ResponseEntity<String> sign(@Valid @PathVariable ("idDocument") Integer idDocument,
                                       @Valid @PathVariable ("userId") Integer userId) throws Exception {
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