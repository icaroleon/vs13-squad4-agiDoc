package br.com.agidoc.agiDoc.model.contact.Entity;

import br.com.agidoc.agiDoc.model.contact.pk.ContactAssociationPk;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
@Getter
@Setter
@Entity(name = "CONTACTS_ASSOCIATIONS")
@Table(name = "CONTACTS_ASSOCIATIONS")
public class ContactAssociationEntity {
    @EmbeddedId
    private ContactAssociationPk contactAssociationPk;
}