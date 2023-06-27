package br.com.userede.qv.parameter.config.runner.parameter;

import br.com.userede.qv.parameter.config.runner.base.validation.ValidatorRunner;
import br.com.userede.qv.parameter.domain.dto.ParameterData;
import br.com.userede.qv.parameter.domain.enums.ExecutionFase;


public interface ParameterValidatorRunner extends ValidatorRunner<ParameterData, ParameterValidatorTaskResult> {

    default ExecutionFase fase() {
        return ExecutionFase.NORMAL;
    }
}
