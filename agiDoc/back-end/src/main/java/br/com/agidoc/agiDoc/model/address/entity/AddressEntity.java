package br.com.agidoc.agiDoc.model.address.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "ADDRESS")
@Table(name = "ADDRESSES")
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESSES")
    @SequenceGenerator(name = "SEQ_ADDRESSES", sequenceName = "seq_addresses", allocationSize = 1)
    @Column(name = "ID_ADDRESS")
    private Integer id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "STREET_NUMBER")
    private Integer num;

    @Column(name = "COMPLEMENT")
    private String complement;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;
}