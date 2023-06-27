package {{global_computed_inputs.mediator_package_name}};

import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public abstract class BaseMediator<
            T extends Serializable,
            E,
            R extends MediatorResult<T, E, S>,
            S extends MediatorRunner<T, E>
        > {


    private final Supplier<T> dataSupplier;
    private final Supplier<R> validatorResultSupplier;

    protected BaseMediator(Supplier<T> dataSupplier, Supplier<R> validatorResultSupplier) {
        this.dataSupplier = dataSupplier;

        this.validatorResultSupplier = validatorResultSupplier;
    }

    public abstract R execute(T data);

    protected abstract E onExceptionResult(Exception e);

    protected R execute(Iterable<S> runners, T data) {


        ModelMapper mapper = new ModelMapper();
        var tasks = StreamSupport.stream(runners.spliterator(), false)
                .parallel()
                .map(runnerTask ->{

                    var task = MediatorTask.<T, E, S>builder()
                             .start(LocalDateTime.now())
                             .runner(runnerTask)
                             .build()
                            ;

                    try{
                        var clone = dataSupplier.get();
                        mapper.map(data, clone);
                        var result = runnerTask.run(clone);

                        task.setResult(result);

                    }catch (Exception e){
                        task.setException(e);
                        task.setResult(onExceptionResult(e));
                    }
                    finally {
                        task.setEnd(LocalDateTime.now());
                    }
                    return task;
                }).
                collect(Collectors.toList());

         var val = validatorResultSupplier.get();
         val.setTasks(tasks);
         val.setParameterData(data);

         return val;
    }
}
