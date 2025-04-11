package DTOs.Builder;

public interface ICuentaBuilder<T> {
    ICuentaBuilder<T> withSaldo(double saldo);
    ICuentaBuilder<T> withOperaciones(int operaciones);
    T build();
}
