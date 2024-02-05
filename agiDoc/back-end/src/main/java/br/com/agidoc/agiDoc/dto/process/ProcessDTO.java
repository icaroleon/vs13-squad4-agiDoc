package br.com.agidoc.agiDoc.dto.process;

import br.com.agidoc.agiDoc.dto.company.CompanyDTO;
import br.com.agidoc.agiDoc.dto.document.DocumentDTO;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDTO{

    private Integer processId;
    private String processNumber = UUID.randomUUID().toString().substring(0, 6);
    private String title, description;
    private ProcessStatus processStatus = ProcessStatus.IN_PROGRESS;
    private List<DocumentDTO> documentDTOS;
    private Company company;
}
