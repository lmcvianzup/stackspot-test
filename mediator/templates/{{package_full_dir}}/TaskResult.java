package br.com.userede.qv.parameter.config.runner.parameter;

import br.com.userede.qv.parameter.domain.enums.Status;
import br.com.userede.qv.parameter.domain.enums.ValidationColumn;
import br.com.userede.qv.parameter.domain.enums.ValidationMessages;
import lombok.Getter;

import java.util.Optional;

public class ParameterValidatorTaskResult {

    public ParameterValidatorTaskResult(Status status, ValidationColumn ... columns){
        this.status = status;
        this.columns= columns;
        this.messageArgs = new Object[0];
        this.message = null;
    }

    public ParameterValidatorTaskResult(ValidationMessages colunaValidationMessages, Object ... messageArgs) {
        this.status = colunaValidationMessages.getStatus();
        this.message = colunaValidationMessages;
        this.messageArgs = messageArgs;
        this.columns = new ValidationColumn[0];
    }

    private final ValidationMessages message;

    private final Object[] messageArgs;

    private final ValidationColumn[] columns;

    @Getter
    private final Status status;

    public Optional<String> getMessage(){
        if(this.message == null) {
            return Optional.empty();
        }
        return Optional.of(message.format(messageArgs));
    }

    public ValidationColumn[] getColumns(){
        if(message != null){
            return message.getColumns();
        }
        return columns;
    }

    public Optional<String> getCode() {
        return getMessage().map(strMsg -> message.getCode());
    }
}
