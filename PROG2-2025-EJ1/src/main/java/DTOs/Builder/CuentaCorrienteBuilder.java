package DTOs.Builder;

import DTOs.CuentaCorriente;

public class CuentaCorrienteBuilder implements ICuentaBuilder<CuentaCorriente> {
    private double saldo;
    private int operaciones;
    private double giroDescubierto;

    public CuentaCorrienteBuilder withSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    }

    public CuentaCorrienteBuilder withOperaciones(int operaciones) {
        this.operaciones = operaciones;
        return this;
    }

    public CuentaCorrienteBuilder withGiroDescubierto(double giro) {
        this.giroDescubierto = giro;
        return this;
    }

    @Override
    public CuentaCorriente build() {
        return new CuentaCorriente(saldo, operaciones, giroDescubierto);
    }
}
