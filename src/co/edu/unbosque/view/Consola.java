package co.edu.unbosque.view;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Consola {

    private Scanner consola;

    public Consola() {
        consola = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n=== SISTEMA BANCO ABC ===");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Registrar cuenta");
        System.out.println("3. Realizar consignación");
        System.out.println("4. Realizar retiro");
        System.out.println("5. Realizar transferencia");
        System.out.println("6. Consultar clientes");
        System.out.println("7. Consultar cuentas");
        System.out.println("8. Consultar movimientos de una cuenta");
        System.out.println("9. Calcular interés mensual");
        System.out.println("10. Salir");
        System.out.print("Seleccione una opción: ");
        return pedirEntero();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String pedirIdentificacion() {
        consola.nextLine();
        System.out.print("Ingrese número de identificación del cliente: ");
        return consola.nextLine();
    }

    public String pedirNombres() {
        System.out.print("Ingrese los nombres del cliente: ");
        return consola.nextLine();
    }

    public String pedirApellidos() {
        System.out.print("Ingrese los apellidos del cliente: ");
        return consola.nextLine();
    }

    public String pedirNumeroCuenta() {
        System.out.print("Ingrese el número de cuenta: ");
        return consola.nextLine();
    }

    public double pedirSaldoInicial() {
        while (true) {
            try {
                System.out.print("Ingrese el saldo inicial de la cuenta: ");
                double saldo = consola.nextDouble();
                consola.nextLine();
                return saldo;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido para el saldo.");
                consola.nextLine();
            }
        }
    }

    public boolean pedirExenta4xMil() {
        while (true) {
            System.out.print("¿La cuenta está exenta del 4x1000? (si/no): ");
            String r = consola.nextLine().trim().toLowerCase();
            if (r.equals("si")) return true;
            if (r.equals("no")) return false;
            System.out.println("Respuesta inválida.");
        }
    }

    public boolean pedirActiva() {
        while (true) {
            System.out.print("¿La cuenta está activa? (si/no): ");
            String r = consola.nextLine().trim().toLowerCase();
            if (r.equals("si")) return true;
            if (r.equals("no")) return false;
            System.out.println("Respuesta inválida.");
        }
    }

    public boolean pedirSobregiroHabilitado() {
        while (true) {
            System.out.print("¿El sobregiro está habilitado para esta cuenta corriente? (si/no): ");
            String r = consola.nextLine().trim().toLowerCase();
            if (r.equals("si")) return true;
            if (r.equals("no")) return false;
            System.out.println("Respuesta inválida.");
        }
    }

    public String pedirTipoCuenta() {
        while (true) {
            System.out.print("Tipo de cuenta (Ahorro/Corriente): ");
            String tipo = consola.nextLine().trim().toLowerCase();
            if (tipo.equals("ahorro") || tipo.equals("corriente"))
                return tipo;
            System.out.println("Debe ingresar 'Ahorro' o 'Corriente'.");
        }
    }

    public double pedirMonto() {
        while (true) {
            try {
                System.out.print("Ingrese el monto: ");
                double monto = consola.nextDouble();
                consola.nextLine();
                return monto;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un monto válido.");
                consola.nextLine();
            }
        }
    }

    public String pedirFechaMovimiento() {
        System.out.print("Ingrese la fecha del movimiento (AAAAMM): ");
        return consola.nextLine();
    }

    public String pedirCuentaDestino() {
        System.out.print("Ingrese el número de cuenta de destino: ");
        return consola.nextLine();
    }

    public int pedirEntero() {
        while (true) {
            try {
                int n = consola.nextInt();
                consola.nextLine();
                return n;
            } catch (InputMismatchException e) {
                System.out.print("Error: ingrese un número entero válido: ");
                consola.nextLine();
            }
        }
    }
}
