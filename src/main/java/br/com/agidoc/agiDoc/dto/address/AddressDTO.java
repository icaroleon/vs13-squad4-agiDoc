package br.com.agidoc.agiDoc.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Integer id;
    private String street;
    private String district;
    private Integer num;
    private String complement;
    private String zipCode;
    private String city;
    private String state;
}