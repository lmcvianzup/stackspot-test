package {{computed_inputs.package_full_name}};
import lombok.Getter;

public enum ExecutionFase {
    PRE_VALIDATION(Status.COMPLETED),
    NORMAL(Status.COMPLETED, Status.ERROR),

    // validações feitas por api, qualquer erro impede a continuação
    API(Status.COMPLETED, Status.ERROR)
    ;

    @Getter
    private final Status[] stopOn;

    ExecutionFase(Status ... stopOn) {
        this.stopOn= stopOn;
    }
}
