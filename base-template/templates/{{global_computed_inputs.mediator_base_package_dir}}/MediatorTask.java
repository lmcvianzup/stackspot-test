package {{group_id}}.{{package_name}};

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
public class MediatorTask<T,E, S extends MediatorRunner<T, E>> {
    @Getter
    private S runner;

    @Getter
    private LocalDateTime start;

    @Getter
    @Setter
    private LocalDateTime end;

    @Getter
    @Setter
    private Exception exception;

    @Getter
    @Setter
    private E result;

}
