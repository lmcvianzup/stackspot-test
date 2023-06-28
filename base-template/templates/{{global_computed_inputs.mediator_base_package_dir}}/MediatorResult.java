package {{global_computed_inputs.mediator_package_name}};


import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
public abstract class MediatorResult<T, E, S extends MediatorRunner<T, E>> {
    T parameterData;
    List<MediatorTask<T,E, S>> tasks = new LinkedList<>();
}
