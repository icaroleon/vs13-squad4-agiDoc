package br.com.agidoc.agiDoc.model.user.pk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserAssociationPK implements Serializable {
    @Column(name = "ID_USER")
    private Integer idUser;
    @Column(name = "ID_COMPANY")
    private Integer idCompany;
}