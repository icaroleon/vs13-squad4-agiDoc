package br.com.agidoc.agiDoc.model.process;

import java.util.ArrayList;
import java.util.UUID;

import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.document.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Process implements IProcess {
    private Integer processId;
    private String processNumber = UUID.randomUUID().toString().substring(0, 6);
    private String title;
    private String description;
    private ProcessStatus processStatus = ProcessStatus.IN_PROGRESS;
    private Company contracted;
    private ArrayList<Company> companies;
    private ArrayList<Document> documents;
    private Integer institutionId = 1;

    public boolean chooseContractor(Company company) {
        this.contracted = company;

        return true;
    }

    public boolean subscribe(Company company) {
        if (company == null)
            return false;

        this.companies.add(company);
        return true;
    }

}