package br.com.agidoc.agiDoc.dto.process;

import br.com.agidoc.agiDoc.model.document.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessesDocumentsDTO extends ProcessDTO {

    public List<Document> documentsList;
}
