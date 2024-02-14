package br.com.agidoc.agiDoc.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateDTO {
    @NotBlank
    @Schema(description = "Sitio", example = "Peixoto")
    private String street;
    @NotBlank
    @Schema(description = "distrito", example = "Distrito do Rio de Janeiro")
    private String district;
    @NotNull
    @Positive
    @Schema(description = "Número do local", example = "25")
    private Integer num;
    @NotBlank
    @Schema(description = "Complemento do local", example = "Proximo a delegacia")
    private String complement;
    @NotBlank
    @Schema(description = "CEP", example = "11729972")
    private String zipCode;
    @NotBlank
    @Schema(description = "Cidade", example = "Rio de Janeiro")
    private String city;
    @NotBlank
    @Schema(description = "Estado", example = "Rio de Janeiro")
    private String state;
}
