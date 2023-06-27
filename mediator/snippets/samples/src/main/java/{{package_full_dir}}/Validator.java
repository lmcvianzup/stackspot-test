package {{computed_inputs.package_full_name}};


import {{global_computed_inputs.mediator_base_package_name}}.BaseMediator;
import {{global_computed_inputs.mediator_base_package_name}}.MediatorTask;
import {{computed_inputs.dto_full_name}};

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

{{dto_name_capitalized}}, TaskResult, Runner
@Component
public class Validator extends BaseMediator<{{dto_name_capitalized}}, TaskResult, Result ,Runner> {

    private final Iterable<Runner> validators;

    public Validator(List<Runner> validators){
        super(ParameterData::new, ParameterValidatiorResult::new);
        this.validators = validators;
    }

    public ParameterValidatiorResult execute(ParameterData parameterData){
        ParameterValidatiorResult endResult = null;
        for(var fase: ExecutionFase.values()) {
            var preValidations = StreamSupport.stream(validators.spliterator(),false).filter(item -> item.fase().equals(fase))
                    .collect(Collectors.toList());

            var result = super.execute(preValidations,parameterData);

            endResult = mergeResults(endResult,result);

            if(result.getTasks().stream()
                    .map(ValidatorTask::getResult)
                    .map(ParameterValidatorTaskResult::getStatus)
                    .anyMatch(taskStatus ->
                            Arrays.asList(fase.getStopOn()).contains(taskStatus)
                    ))
            {
                // ignore possible future fases of validation
                return endResult;
            }

        }
        return endResult;
    }

    private ParameterValidatiorResult mergeResults(ParameterValidatiorResult endResult, ParameterValidatiorResult newResult) {
        if(endResult == null){
            return newResult;
        }
        endResult.getTasks().addAll(newResult.getTasks());

        return endResult;
    }
    @Override
    protected ParameterValidatorTaskResult onExceptionResult(Exception e) {
        return new ParameterValidatorTaskResult(ValidatorMessages.GERAL, "error not catched during field validation");
    }
}
