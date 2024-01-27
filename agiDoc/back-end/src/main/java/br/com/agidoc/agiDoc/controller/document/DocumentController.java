package br.com.agidoc.agiDoc.controller.document;


import br.com.agidoc.agiDoc.dto.document.DocumentCreateDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.exception.DatabaseException;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/document")
@AllArgsConstructor
@RestController
@Validated
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping
    public List<DocumentDTO> list() throws Exception {
        return this.documentService.list();
    }

    @GetMapping("/{idDocument}")
    public DocumentDTO findById(@PathVariable("idDocument") Integer idDocument) throws Exception {
        return this.documentService.findById(idDocument);
    }

    @PostMapping({"/{idProcess}/process"})
    public DocumentDTO create(@NotNull @PathVariable ("idProcess") Integer idProcess,
                              @Valid @RequestBody DocumentCreateDTO documentCreateDTO) throws Exception {
        return this.documentService.create(idProcess, documentCreateDTO);
    }

    @PutMapping("/{idDocument}")
    public Document update(@PathVariable ("idDocument") Integer idDocument, @RequestBody Document document) throws DatabaseException {
        return this.documentService.update(idDocument, document);
    }

    @DeleteMapping("/{idDocument}")
    public void delete(@PathVariable ("idDocument") Integer idDocument) throws DatabaseException {
        this.documentService.delete(idDocument);
    }
}
