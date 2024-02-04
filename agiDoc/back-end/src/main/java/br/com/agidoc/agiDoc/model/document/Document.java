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
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
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
    @Column(name = "ID_DOCUMENT")
    private Integer documentId;

    @Column(name = "protocol")
    private String protocol = UUID.randomUUID().toString().substring(0, 6);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "is_signed")
    private boolean signed;

//    @Column(name = "ATTACHMENT")
//    private String attachment;

//    @Column(name = "file")
//    private Associated associated;


    //@Column(name="id_signature")
    //private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinTable(name = "documents_associations", joinColumns = {@JoinColumn(name = "id_document", referencedColumnName = "id_document")},
            inverseJoinColumns = {@JoinColumn(name = "id_process", referencedColumnName = "id_process")})
    private Process processes;

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
}