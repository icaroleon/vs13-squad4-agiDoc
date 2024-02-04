package br.com.agidoc.agiDoc.model.document;

import br.com.agidoc.agiDoc.model.process.Process;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

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
    @Column(name = "ID_DOCUMENT", nullable = false)
    private Integer documentId;

    @Column(name = "protocol")
    private String protocol = UUID.randomUUID().toString().substring(0, 6);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "is_signed", nullable = false)
    private boolean signed;

    @Column(name = "ATTACHMENT")
    private String attachment;

//    @ManyToOne
////    @JoinTable(name = "DOCUMENTS_ASSOCIATIONS", joinColumns = {@JoinColumn(name = "ID_DOCUMENT",
////            referencedColumnName = "ID_DOCUMENT")},
////            inverseJoinColumns = {@JoinColumn(name = "ID_DOCUMENT",
////                    referencedColumnName = "ID_PROCESS")})
//    private Process process;

//    @Column(name = "file")
//    private Associated associated;


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
}