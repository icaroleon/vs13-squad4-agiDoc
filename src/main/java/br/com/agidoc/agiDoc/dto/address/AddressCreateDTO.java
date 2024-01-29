package br.com.agidoc.agiDoc.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressCreateDTO {
    @NotBlank
    @Schema(description = "Rua", example = "Peixoto")
    private String street;
    @NotBlank
    @Schema(description = "distrito", example = "Distrito de São Paulo")
    private String district;
    @NotNull
    @Positive
    @Schema(description = "Número do local", example = "12")
    private Integer number;
    @NotBlank
    @Schema(description = "Complemento do local", example = "Proximo a praça")
    private String complement;
    @NotBlank
    @Schema(description = "CEP", example = "81725172")
    private String zipCode;
    @NotBlank
    @Schema(description = "Cidade", example = "São Paulo")
    private String city;
    @NotBlank
    @Schema(description = "Estado", example = "São Paulo")
    private String state;
}