package Service;

import DTOs.IGestionSaldo;
import java.util.ArrayList;
import java.util.List;

public class LogicaCuenta {
    private static volatile LogicaCuenta instancia;
    private final List<IGestionSaldo> cuentas;

    private LogicaCuenta() {
        cuentas = new ArrayList<>();
    }

    public static synchronized LogicaCuenta getInstancia() {
        if (instancia == null) {
            instancia = new LogicaCuenta();
        }
        return instancia;
    }

    public synchronized void agregarCuenta(IGestionSaldo cuenta) {
        cuentas.add(cuenta);
    }

    public boolean agregarSaldo(int nroCuenta, double monto) {
        if (validar(nroCuenta)) {
            return cuentas.get(nroCuenta).agregarSaldo(monto);
        }
        return false;
    }

    public boolean quitarSaldo(int nroCuenta, double monto) {
        if (validar(nroCuenta)) {
            return cuentas.get(nroCuenta).quitarSaldo(monto);
        }
        return false;
    }

    public double consultarSaldo(int nroCuenta) {
        if (validar(nroCuenta)) {
            return cuentas.get(nroCuenta).getSaldo();
        }
        return -1;
    }

    public int getOperaciones(int nroCuenta) {
        if (validar(nroCuenta)) {
            return cuentas.get(nroCuenta).getOperaciones();
        }
        return -1;
    }

    public List<IGestionSaldo> getCuentas() {
        return cuentas;
    }

    private boolean validar(int i) {
        if (i >= 0 && i < cuentas.size()) {
            return true;
        } else {
            return false;
        }
    }
}