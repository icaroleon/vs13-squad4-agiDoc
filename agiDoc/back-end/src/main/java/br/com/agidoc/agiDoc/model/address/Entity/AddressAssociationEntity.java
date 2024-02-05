package br.com.agidoc.agiDoc.model.address.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ADDRESSES_ASSOCIATIONS")
public class AddressAssociationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESSES_ASSOCIATIONS")
    @SequenceGenerator(name = "SEQ_ADDRESSES_ASSOCIATIONS", sequenceName = "SEQ_ADDRESSES_ASSOCIATIONS", allocationSize = 1)
    @Column(name = "id_address_association")
    private Integer idContactAssociation;

    @Column(name = "id_address")
    private Integer idAddress;

    @Column(name = "id_company")
    private Integer idCompany;
}
