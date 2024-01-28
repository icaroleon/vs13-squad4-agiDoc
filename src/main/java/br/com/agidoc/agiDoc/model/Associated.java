package br.com.agidoc.agiDoc.model;

import java.util.Arrays;

public enum Associated {
    COMPETITOR(1),
    INSTITUTION(2),
    PROCESS(3);

    private final Integer type;

    Associated(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static Associated ofType(Integer type) {
        return Arrays.stream(Associated.values())
                .filter(tp -> tp.getType().equals(type))
                .findFirst()
                .get();
    }
}
