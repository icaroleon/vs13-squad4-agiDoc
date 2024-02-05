package br.com.agidoc.agiDoc.model.contact.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "CONTACTS_ASSOCIATIONS")
public class ContactAssociationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTACTS_ASSOCIATIONS")
    @SequenceGenerator(name = "SEQ_CONTACTS_ASSOCIATIONS", sequenceName = "SEQ_CONTACTS_ASSOCIATIONS", allocationSize = 1)
    @Column(name = "id_contact_association")
    private Integer idContactAssociation;

    @Column(name = "id_contact")
    private Integer idContact;

    @Column(name = "id_company")
    private Integer idCompany;
}