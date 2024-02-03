package br.com.agidoc.agiDoc.model.document;

import java.time.LocalDate;
import java.util.UUID;

import br.com.agidoc.agiDoc.model.Associated;
import br.com.agidoc.agiDoc.model.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "documents")
public class Document {
    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENTS")
    @SequenceGenerator(name = "SEQ_DOCUMENTS", sequenceName = "SEQ_DOCUMENTS", allocationSize = 1)
    @Column(name = "id_document")
    private Integer processId;

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
//    @Column(name = "process_number")
//    private Integer processId;

    //@Column(name="id_signature")
    //private User user;
}