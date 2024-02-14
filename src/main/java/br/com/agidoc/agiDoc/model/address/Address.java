package br.com.agidoc.agiDoc.model.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Integer id;
    private String street;
    private String district;
    private Integer number;
    private String complement;
    private String zipCode;
    private String city;
    private String state;
}