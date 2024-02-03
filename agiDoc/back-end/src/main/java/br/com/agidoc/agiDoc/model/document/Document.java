package br.com.agidoc.agiDoc.model.document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import br.com.agidoc.agiDoc.model.Associated;
import br.com.agidoc.agiDoc.model.pk.DocumentAssociation;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DOCUMENTS")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "documentId")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENTS")
    @SequenceGenerator(name = "SEQ_DOCUMENTS", sequenceName = "SEQ_DOCUMENTS", allocationSize = 1)
    @Column(name = "id_document")
    private Integer documentId;

    @NotBlank
    @Column(name = "protocol")
    private String protocol = UUID.randomUUID().toString().substring(0, 6);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "is_signed")
    private boolean signed;

    //@Column(name = "file")
    //private String file;

//    private Associated associated;
//
    @ManyToOne
    private Process process;

    //@Column(name="id_signature")
    //private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(documentId, document.documentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId);
    }

    @ManyToOne(optional = false)
    private Process processes;

    public Process getProcesses() {
        return processes;
    }

    public void setProcesses(Process processes) {
        this.processes = processes;
    }
}