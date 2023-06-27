package {{computed_inputs.package_full_name}};
import lombok.Getter;

import java.text.MessageFormat;

@Getter
public enum ValidatorMessages {

    GERAL("0001",Status.ERROR, "not catched exception: {0}")
    ;

    private final String code;
    private final Status status;

    private final String message;

    ValidatorMessages(String code, Status status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public String format(Object ... args){
        return MessageFormat.format(this.message, args);
    }


}
