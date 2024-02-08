package br.com.agidoc.agiDoc.model.contact.entity;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "CONTACTS")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTACTS")
    @SequenceGenerator(name = "SEQ_CONTACTS", sequenceName = "SEQ_CONTACTS", allocationSize = 1)
    @Column(name = "id_contact")
    private Integer idContact;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone_type")
    private Integer phoneType;
}