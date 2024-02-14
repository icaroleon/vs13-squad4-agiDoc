package br.com.agidoc.agiDoc.model.user.entity;

import br.com.agidoc.agiDoc.model.user.pk.UserAssociationPK;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_ASSOCIATIONS")
public class UserAssociationEntity {
    @EmbeddedId
    @NotNull
    private UserAssociationPK userAssociationPK;
}