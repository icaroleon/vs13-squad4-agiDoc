package br.com.agidoc.agiDoc.controller.competitor;

import br.com.agidoc.agiDoc.dto.competitor.CompetitorCreateDTO;
import br.com.agidoc.agiDoc.dto.competitor.CompetitorDTO;
import br.com.agidoc.agiDoc.service.CompetitorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/competitor")
@Validated
@Slf4j
public class CompetitorController implements ICompetitorController {
    private final CompetitorService competitorService;

    @PostMapping
    public ResponseEntity<CompetitorDTO> create (@Valid @RequestBody CompetitorCreateDTO competitorCreateDTO) throws Exception {
        log.info("Competidor criado.");

        return new ResponseEntity<>(competitorService.create(competitorCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompetitorDTO>> list() throws Exception {
        log.info("Competidor listado.");
        return new ResponseEntity<>(competitorService.list(), HttpStatus.OK);
    }

    @GetMapping("/{idCompetitor}")
    public ResponseEntity<CompetitorDTO> getById(@NotNull @PathVariable("idCompetitor") Integer id) throws Exception {
        log.info("Competidor listado por id.");
        return new ResponseEntity<>(competitorService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{idCompetitor}")
    public ResponseEntity<CompetitorDTO> update(@NotNull @PathVariable("idCompetitor") Integer id, @Valid @RequestBody CompetitorCreateDTO competitorCreateDTO) throws Exception {
        log.info("Competidor atualizado.");
        return new ResponseEntity<>(competitorService.update(id, competitorCreateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{idCompetitor}")
    public ResponseEntity<Void> delete(@NotNull @PathVariable("idCompetitor") Integer id) throws Exception {
        log.info("Competidor deletado.");
        competitorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
