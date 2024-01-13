package model.address;

import java.util.Arrays;

public enum AddressAssociated {
    COMPETITOR(1),
    INSTITUTION(2);

    private final Integer type;

    AddressAssociated(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static AddressAssociated ofType(Integer tipo){
        return Arrays.stream(AddressAssociated.values())
                .filter(tp -> tp.getType().equals(tipo))
                .findFirst()
                .get();
    }
}
