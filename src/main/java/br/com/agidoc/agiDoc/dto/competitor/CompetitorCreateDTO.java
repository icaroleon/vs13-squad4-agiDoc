package br.com.agidoc.agiDoc.dto.competitor;

import br.com.agidoc.agiDoc.dto.juridical.JuridicalCreateDTO;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.process.Process;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitorCreateDTO extends JuridicalCreateDTO {
    private int id;
    private int isContracted;
    private ArrayList<Integer> idProcesses;
    private ArrayList<Integer> idDocuments;
    private int processId;
}
