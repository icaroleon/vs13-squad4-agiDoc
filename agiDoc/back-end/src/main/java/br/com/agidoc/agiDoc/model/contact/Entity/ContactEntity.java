package br.com.agidoc.agiDoc.model.contact.Entity;

import br.com.agidoc.agiDoc.model.contact.ContactPhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CONTACTS")
@Table(name = "CONTACTS")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTACTS")
    @SequenceGenerator(name = "SEQ_CONTACTS", sequenceName = "seq_contacts", allocationSize = 1)
    @Column(name = "ID_CONTACT")
    private Integer idContact;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "TYPE")
    private ContactPhoneType phoneType;
}