package {{group_id}}.{{package_name}};

public interface MediatorRunner<T, E> {
  E run(T data);
}
