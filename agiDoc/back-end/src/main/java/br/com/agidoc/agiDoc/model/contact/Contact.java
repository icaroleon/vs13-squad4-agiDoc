package br.com.agidoc.agiDoc.model.contact;

import br.com.agidoc.agiDoc.model.Associated;

public class Contact {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private ContactPhoneType phoneType;
    private Associated associated;
    private Integer associatedId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ContactPhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(ContactPhoneType phoneType) {
        this.phoneType = phoneType;
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
        return "Contact{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", email='" + email + "'" +
                ", phone=" + phone +
                ", phoneType='" + phoneType +
                "}";
    }
}
