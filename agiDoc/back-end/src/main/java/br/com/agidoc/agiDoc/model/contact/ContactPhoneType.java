package br.com.agidoc.agiDoc.model.contact;

import java.util.Arrays;

public enum ContactPhoneType {
    LANDLINE(1),
    MOBILE(2);

    private final Integer type;

    ContactPhoneType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static ContactPhoneType ofType(Integer type){
        return Arrays.stream(ContactPhoneType.values())
                .filter(tp -> tp.getType().equals(type))
                .findFirst()
                .get();
    }
}
