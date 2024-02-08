package br.com.agidoc.agiDoc.dto.user;

import br.com.agidoc.agiDoc.model.user.pk.UserAssociationPK;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAssociationCreateDTO {
    @NotNull
    private UserAssociationPK userAssociationPK;
}