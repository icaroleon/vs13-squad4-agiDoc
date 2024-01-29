package br.com.agidoc.agiDoc.dto.competitor;

import br.com.agidoc.agiDoc.dto.juridical.JuridicalDTO;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitorDTO extends JuridicalDTO {
    private int id;
    private int isContracted;
    private ArrayList<Process> processes;
    private ArrayList<Document> documents;
    private int processId;
}
