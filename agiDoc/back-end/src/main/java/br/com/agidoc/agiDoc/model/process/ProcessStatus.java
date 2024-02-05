package br.com.agidoc.agiDoc.model.process;
import java.util.Arrays;

public enum ProcessStatus {
    IN_PROGRESS(0),
    COMPLETED(1),
    SUSPENDED(2),
    ARCHIVED(3);
//    INACTIVE(5);

    private final Integer type;

    ProcessStatus(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static ProcessStatus ofType(Integer type){
        return Arrays.stream(ProcessStatus.values())
                .filter(tp -> tp.getType().equals(type))
                .findFirst()
                .get();
    }
}


