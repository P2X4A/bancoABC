package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import co.edu.unbosque.exceptions.*;

public class GestorBanco {
	private List<Cliente> clientes;
	private List<Cuenta> cuentas;
	private Properties config;

	public GestorBanco(Properties config) {
		this.clientes = new ArrayList<>();
		this.cuentas = new ArrayList<>();
		this.config = config;
	}

	// Métodos CRUD
	public void registrarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public void registrarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}

	public Cliente buscarClientePorId(String id) {
		for (Cliente c : clientes)
			if (c.getIdentificacion().equals(id))
				return c;
		return null;
	}

	public Cuenta buscarCuentaPorNumero(String numero) {
		for (Cuenta cu : cuentas)
			if (cu.getNumero().equals(numero))
				return cu;
		return null;
	}

	// CONSIGNACION: suma saldo y agrega movimiento
	public void realizarConsignacion(String numCuenta, double monto, String fecha) throws CuentaInactivaExcepcion {
		Cuenta cuenta = buscarCuentaPorNumero(numCuenta);
		if (cuenta == null || !cuenta.isActiva())
			throw new CuentaInactivaExcepcion("Cuenta inactiva o no encontrada");
		cuenta.setSaldo(cuenta.getSaldo() + monto);
		MovimientoConsignacion mov = new MovimientoConsignacion(fecha, monto, "Consignación");
		cuenta.getMovimientos().add(mov); // Aquí se agrega el movimiento a la lista de la cuenta
	}

	// RETIRO
	public void realizarRetiro(String numCuenta, double monto, String fecha) throws CuentaInactivaExcepcion,
			SaldoInsuficienteExcepcion, SobregiroNoPermitidoExcepcion, ParametroNoConfiguradoException {
		Cuenta cuenta = buscarCuentaPorNumero(numCuenta);
		if (cuenta == null || !cuenta.isActiva())
			throw new CuentaInactivaExcepcion("Cuenta inactiva o no encontrada");

		double comision = calcularComisionRetiro(monto, cuenta);

		if (cuenta instanceof CuentaCorriente) {
			CuentaCorriente cc = (CuentaCorriente) cuenta;
			if (!cc.isSobregiroHabilitado() && (cc.getSaldo() < monto + comision)) {
				throw new SaldoInsuficienteExcepcion("No hay saldo suficiente y sobregiro no habilitado");
			}
			if (cc.isSobregiroHabilitado()) {
				double porcentajeSobregiro = leerParametro("PORCENTAJE_SOBREGIRO");
				double costoSobregiro = leerParametro("COSTO_SOBREGIRO");
				double limiteSobregiro = cc.getSaldo() * porcentajeSobregiro;
				double totalDisponible = cc.getSaldo() + limiteSobregiro;
				if (totalDisponible < monto + costoSobregiro)
					throw new SaldoInsuficienteExcepcion("No hay saldo suficiente siquiera con sobregiro");
				cc.setSaldo(cc.getSaldo() - monto - comision - costoSobregiro);
			} else {
				cc.setSaldo(cc.getSaldo() - monto - comision);
			}
		} else { // Cuenta de ahorro
			if (cuenta.getSaldo() < monto + comision)
				throw new SaldoInsuficienteExcepcion("No hay saldo suficiente");
			cuenta.setSaldo(cuenta.getSaldo() - monto - comision);
		}
	}

	// TRANSFERENCIA
	public void transferir(String numCuentaOrigen, String numCuentaDestino, double monto, String fecha)
			throws CuentaInactivaExcepcion, SaldoInsuficienteExcepcion, SobregiroNoPermitidoExcepcion,
			ParametroNoConfiguradoException {
		realizarRetiro(numCuentaOrigen, monto, fecha);
		realizarConsignacion(numCuentaDestino, monto, fecha);
	}

	// COMISIONES
	private double calcularComisionRetiro(double monto, Cuenta cuenta) throws ParametroNoConfiguradoException {
		double umbral = leerParametro("UMBRAL_EXONERACION");
		double comision = 0.0;
		if (cuenta.getSaldo() < umbral) {
			comision = monto * leerParametro("COMISION_RETIRO");
		}
		return comision;
	}

	// INTERÉS DE CADA CUENTA
	public double calcularInteresMensual(Cuenta cuenta) throws ParametroNoConfiguradoException {
		double tasa = leerParametro("TASA_INTERES");
		return cuenta.getSaldo() * tasa;
	}

	// UTILIDAD PARA LEER PARÁMETROS
	private double leerParametro(String clave) throws ParametroNoConfiguradoException {
		String valor = config.getProperty(clave);
		if (valor == null)
			throw new ParametroNoConfiguradoException("Parámetro no configurado: " + clave);
		return Double.parseDouble(valor);
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}
}
