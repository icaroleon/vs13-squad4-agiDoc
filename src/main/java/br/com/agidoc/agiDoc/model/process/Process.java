package br.com.agidoc.agiDoc.model.process;

import java.util.ArrayList;
import java.util.UUID;

import br.com.agidoc.agiDoc.model.competitor.Competitor;
import br.com.agidoc.agiDoc.model.document.Document;
import br.com.agidoc.agiDoc.model.document.DocumentType;

public class Process implements IProcess {
    UUID uuid = UUID.randomUUID();

    private Integer id;
    private String processNumber = uuid.toString().substring(0, 6);
    private String title, description;
    private ProcessStatus processStatus = ProcessStatus.IN_PROGRESS;
    private Competitor contracted;
    private ArrayList<Competitor> competitors;
    private ArrayList<Document> documents;
    private Integer institutionId = 1;

    public Process(Integer id, String processNumber) {
        this.id = id;
    }

    public Process(Integer institutionId, Integer id, String processNumber, String title, String description, ArrayList<Competitor> competitors, ArrayList<Document> documents) {
        this.institutionId = id;
        this.id = id;
        this.processNumber = processNumber;
        this.title = title;
        this.description = description;
        this.competitors = competitors;
        this.documents = documents;
    }

    public Process() {
    }

    public Process(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }

    public void setContracted(Competitor contracted) {
        this.contracted = contracted;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public boolean chooseContractor(Competitor competitor) {
        this.contracted = competitor;

        return true;
    }

    public boolean subscribe(Competitor competitor) {
        if (competitor == null)
            return false;

        this.competitors.add(competitor);
        return true;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    public void setCompetitors(ArrayList<Competitor> competitors) {
        this.competitors = competitors;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public Integer getId() {
        return id;
    }

    public Competitor getContracted() {
        return contracted;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public String toString() {
        return """
                Id: %s
                Número do Processo: %s
                Titulo: %s
                Status: %s
                Descrição: %s
                Contratado: %s      
                """.formatted(
                    this.id,
                    this.processNumber,
                    this.title,
                    this.processStatus,
                    this.description,
                    this.contracted == null ? "Contratado ainda não selecionado" : this.contracted.getCompanyName()
                );
    }
}
