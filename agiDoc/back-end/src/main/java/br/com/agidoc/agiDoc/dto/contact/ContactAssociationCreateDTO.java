package br.com.agidoc.agiDoc.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactAssociationCreateDTO {
    @NotNull
    @Positive
    private Integer idContact;

    @NotNull
    @Positive
    private Integer idCompany;
}