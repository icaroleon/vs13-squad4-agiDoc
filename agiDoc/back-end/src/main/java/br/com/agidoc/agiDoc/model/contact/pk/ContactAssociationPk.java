package br.com.agidoc.agiDoc.model.contact.pk;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ContactAssociationPk implements Serializable {
    @NotNull
    @Positive
    @Schema(description = "New contact id.", example = "1")
    @Column(name = "ID_CONTACT")
    private Integer idContact;

    @NotNull
    @Positive
    @Schema(description = "Id of the new company.", example = "1")
    @Column(name = "ID_COMPANY")
    private Integer idCompany;
}
