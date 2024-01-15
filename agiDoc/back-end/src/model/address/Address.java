package model.address;

import model.Associated;

public class Address {
    private Integer id;
    private String street;
    private String district;
    private Integer number;
    private String complement;
    private String zipCode;
    private String city;
    private String state;
    private Associated associated;
    private Integer associatedId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Associated getAssociated() {
        return associated;
    }

    public void setAssociated(Associated associated) {
        this.associated = associated;
    }

    public Integer getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(Integer associatedId) {
        this.associatedId = associatedId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + "'" +
                ", district='" + district + "'" +
                ", number=" + number +
                ", complement='" + complement + "'" +
                ", city='" + city + "'" +
                ", state='" + state + "'" +
                ", zipCode='" + zipCode + "'" +
                ", association=" + associated +
                ", associationId=" + associatedId +
                "}";
    }
}
