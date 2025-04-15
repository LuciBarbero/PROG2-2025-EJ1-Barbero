package InterfazGrafica;

import DTOs.CajaDeAhorro;
import DTOs.CuentaCorriente;
import DTOs.IGestionSaldo;
import Service.LogicaCuenta;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int cantidadCuentas = 10;
    private static final int iteraciones = 10000;

    public static void main(String[] args) throws InterruptedException {
        LogicaCuenta logica = LogicaCuenta.getInstancia();
        Random random = new Random();

        // 1. Crear 10 cuentas aleatorias
        for (int i = 0; i < cantidadCuentas; i++) {
            double saldoInicial = 1000 + random.nextDouble() * 40000; // Entre 1000 y 41000
            IGestionSaldo cuenta;

            if (i % 2 == 0) {
                cuenta = new CajaDeAhorro(saldoInicial, 0);
            } else {
                double descubierto = 500 + random.nextDouble() * 5000; // Entre 500 y 5500
                cuenta = new CuentaCorriente(saldoInicial, 0, descubierto);
            }

            logica.agregarCuenta(cuenta);
        }

        // 2. Crear pool de hilos para operaciones concurrentes
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < iteraciones; i++) {
            executor.execute(() -> {
                int cuentaId = random.nextInt(cantidadCuentas);
                double monto = random.nextDouble() * 1000; // Hasta 1000

                // 50/50 chance de agregar o quitar
                if (random.nextBoolean()) {
                    logica.agregarSaldo(cuentaId, monto);
                } else {
                    logica.quitarSaldo(cuentaId, monto);
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(50);
        }

        // 3. Imprimir los resultados
        System.out.println("=== RESULTADOS ===");
        for (int i = 0; i < cantidadCuentas; i++) {
            double saldo = logica.consultarSaldo(i);
            int operaciones = logica.getOperaciones(i);
            System.out.printf("Cuenta #%d -> Saldo: %.2f | Operaciones: %d%n", i, saldo, operaciones);
        }
    }
}
