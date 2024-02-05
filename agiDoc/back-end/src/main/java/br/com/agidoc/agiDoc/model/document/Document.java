package br.com.agidoc.agiDoc.model.document;

import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.company.Company;
import br.com.agidoc.agiDoc.model.process.Process;
import br.com.agidoc.agiDoc.model.process.ProcessStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.BlobType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENTS")
    @SequenceGenerator(name = "SEQ_DOCUMENTS", sequenceName = "SEQ_DOCUMENTS", allocationSize = 1)
    @Column(name = "ID_DOCUMENT", nullable = false)
    private Integer documentId;

    @Column(name = "PROTOCOL")
    private String protocol = UUID.randomUUID().toString().substring(0, 6);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @Column(name = "is_signed", nullable = false)
    private boolean signed;

    @Column(name = "ATTACHMENT")
    private BlobType attachment = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "DOCUMENTS_ASSOCIATIONS", joinColumns = {@JoinColumn(name = "ID_DOCUMENT",
            referencedColumnName = "ID_DOCUMENT")},
            inverseJoinColumns = {@JoinColumn(name = "ID_PROCESS",
                    referencedColumnName = "ID_PROCESS")})
    private Process process;

    @Column(name = "STATUS")
    private Status status = Status.ACTIVE;
}