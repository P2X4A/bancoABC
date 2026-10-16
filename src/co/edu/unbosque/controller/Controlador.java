package co.edu.unbosque.controller;

import co.edu.unbosque.model.*;
import co.edu.unbosque.view.Consola;
import co.edu.unbosque.persistence.FileHandler;
import co.edu.unbosque.exceptions.*;

import java.util.Properties;
import java.io.IOException;

public class Controlador {
	private Consola consola;
	private GestorBanco gestorBanco;

	public Controlador() {
		consola = new Consola();
		gestorBanco = inicializarGestorBanco();
	}

	private GestorBanco inicializarGestorBanco() {
		FileHandler fileHandler = new FileHandler();
		Properties parametrosBanco = new Properties();
		try {
			parametrosBanco = fileHandler.cargarArchivoProperties("config.properties");
		} catch (IOException ex) {
			consola.mostrarMensaje("Error al cargar la configuración del banco: " + ex.getMessage());
		}
		return new GestorBanco(parametrosBanco);
	}

	public void ejecutar() {
		int opcionSeleccionada;
		boolean sistemaEnEjecucion = true;

		consola.mostrarMensaje("¡Bienvenido al Sistema del Banco ABC!");
		while (sistemaEnEjecucion) {
			opcionSeleccionada = consola.mostrarMenu();

			switch (opcionSeleccionada) {
			case 1:
				registrarCliente();
				break;
			case 2:
				registrarCuenta();
				break;
			case 3:
				realizarConsignacion();
				break;
			case 4:
				realizarRetiro();
				break;
			case 5:
				realizarTransferencia();
				break;
			case 6:
				mostrarClientes();
				break;
			case 7:
				mostrarCuentas();
				break;
			case 8:
				consultarMovimientosCuenta();
				break;
			case 9:
				calcularInteresCuenta();
				break;
			case 10:
				consola.mostrarMensaje("¡Gracias por utilizar el sistema!");
				sistemaEnEjecucion = false;
				break;
			default:
				consola.mostrarMensaje("Opción no válida. Por favor intente de nuevo.");
				break;
			}
		}
	}

	private void registrarCliente() {
		String identificacion = consola.pedirIdentificacion();
		String nombres = consola.pedirNombres();
		String apellidos = consola.pedirApellidos();
		Cliente clienteNuevo = new Cliente(identificacion, nombres, apellidos);
		gestorBanco.registrarCliente(clienteNuevo);
		consola.mostrarMensaje("Cliente registrado exitosamente.");
	}

	private void registrarCuenta() {
		String tipoCuenta = consola.pedirTipoCuenta();
		String numeroCuenta = consola.pedirNumeroCuenta();
		double saldoInicial = consola.pedirSaldoInicial();
		boolean exenta4xMil = consola.pedirExenta4xMil();
		boolean cuentaActiva = consola.pedirActiva();

		if (tipoCuenta.equals("ahorro")) {
			gestorBanco.registrarCuenta(new CuentaAhorro(numeroCuenta, saldoInicial, exenta4xMil, cuentaActiva));
		} else {
			boolean sobregiroHabilitado = consola.pedirSobregiroHabilitado();
			gestorBanco.registrarCuenta(
					new CuentaCorriente(numeroCuenta, saldoInicial, exenta4xMil, cuentaActiva, sobregiroHabilitado));
		}
		consola.mostrarMensaje("Cuenta registrada exitosamente.");
	}

	private void realizarConsignacion() {
		String numeroCuenta = consola.pedirNumeroCuenta();
		double montoConsignacion = consola.pedirMonto();
		String fechaMovimiento = consola.pedirFechaMovimiento();
		try {
			gestorBanco.realizarConsignacion(numeroCuenta, montoConsignacion, fechaMovimiento);
			consola.mostrarMensaje("Consignación realizada exitosamente.");
		} catch (CuentaInactivaExcepcion ex) {
			consola.mostrarMensaje("Error: " + ex.getMessage());
		}
	}

	private void realizarRetiro() {
		String numeroCuenta = consola.pedirNumeroCuenta();
		double montoRetiro = consola.pedirMonto();
		String fechaMovimiento = consola.pedirFechaMovimiento();
		try {
			gestorBanco.realizarRetiro(numeroCuenta, montoRetiro, fechaMovimiento);
			consola.mostrarMensaje("Retiro realizado exitosamente.");
		} catch (CuentaInactivaExcepcion ex) {
			consola.mostrarMensaje("Error: " + ex.getMessage());
		} catch (SaldoInsuficienteExcepcion ex) {
			consola.mostrarMensaje("Error: " + ex.getMessage());
		} catch (SobregiroNoPermitidoExcepcion ex) {
			consola.mostrarMensaje("Error: " + ex.getMessage());
		} catch (ParametroNoConfiguradoException ex) {
			consola.mostrarMensaje("Error: " + ex.getMessage());
		}
	}

	private void realizarTransferencia() {
		String numeroCuentaOrigen = consola.pedirNumeroCuenta();
		String numeroCuentaDestino = consola.pedirCuentaDestino();
		double montoTransferencia = consola.pedirMonto();
		String fechaMovimiento = consola.pedirFechaMovimiento();
		try {
			gestorBanco.transferir(numeroCuentaOrigen, numeroCuentaDestino, montoTransferencia, fechaMovimiento);
			consola.mostrarMensaje("Transferencia realizada exitosamente.");
		} catch (CuentaInactivaExcepcion | SaldoInsuficienteExcepcion | SobregiroNoPermitidoExcepcion
				| ParametroNoConfiguradoException ex) {
			consola.mostrarMensaje("Error: " + ex.getMessage());
		}
	}

	private void mostrarClientes() {
		consola.mostrarMensaje("=== Lista de clientes registrados ===");
		for (Cliente cliente : gestorBanco.getClientes()) {
			consola.mostrarMensaje("Identificación: " + cliente.getIdentificacion() + ", Nombres: "
					+ cliente.getNombres() + ", Apellidos: " + cliente.getApellidos());
		}
	}

	private void mostrarCuentas() {
		consola.mostrarMensaje("=== Lista de cuentas registradas ===");
		for (Cuenta cuenta : gestorBanco.getCuentas()) {
			consola.mostrarMensaje("Número: " + cuenta.getNumero() + ", Saldo: " + cuenta.getSaldo() + ", Activa: "
					+ cuenta.isActiva() + ", Exenta 4xMil: " + cuenta.isExenta4xMil());
		}
	}

	private void consultarMovimientosCuenta() {
		String numeroCuenta = consola.pedirNumeroCuenta();
		Cuenta cuenta = gestorBanco.buscarCuentaPorNumero(numeroCuenta);
		if (cuenta == null) {
			consola.mostrarMensaje("Cuenta no encontrada.");
			return;
		}
		consola.mostrarMensaje("=== Movimientos de la cuenta ===");
		for (Transaccion transaccion : cuenta.getMovimientos()) {
			consola.mostrarMensaje("Fecha: " + transaccion.getFecha() + ", Monto: " + transaccion.getMonto()
					+ ", Descripción: " + transaccion.getDescripcion());
		}
	}

	private void calcularInteresCuenta() {
		String numeroCuenta = consola.pedirNumeroCuenta();
		Cuenta cuenta = gestorBanco.buscarCuentaPorNumero(numeroCuenta);
		if (cuenta == null) {
			consola.mostrarMensaje("Cuenta no encontrada.");
			return;
		}
		try {
			double interesCalculado = gestorBanco.calcularInteresMensual(cuenta);
			consola.mostrarMensaje("El interés calculado para la cuenta es: " + interesCalculado);
		} catch (ParametroNoConfiguradoException ex) {
			consola.mostrarMensaje("Error: " + ex.getMessage());
		}
	}
}
