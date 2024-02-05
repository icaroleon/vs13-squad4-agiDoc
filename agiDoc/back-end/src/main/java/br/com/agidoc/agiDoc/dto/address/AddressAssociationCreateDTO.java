package br.com.agidoc.agiDoc.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressAssociationCreateDTO {
    @NotNull
    @Positive
    private Integer idAddress;

    @NotNull
    @Positive
    private Integer idCompany;
}