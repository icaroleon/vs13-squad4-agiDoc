package br.com.agidoc.agiDoc.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactAssociationDTO {
    private Integer idContactAssociation;
    private Integer idContact;
    private Integer idCompany;
}