package br.com.agidoc.agiDoc.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressAssociationDTO {
    private Integer idAddressAssociation;
    private Integer idAddress;
    private Integer idCompany;
}
