package co.edu.unbosque.model;

public class CuentaCorriente extends Cuenta {
    private boolean sobregiroHabilitado;

    public CuentaCorriente(String numero, double saldo, boolean exenta4xMil, boolean activa, boolean sobregiroHabilitado) {
        super(numero, saldo, exenta4xMil, activa);
        this.sobregiroHabilitado = sobregiroHabilitado;
    }

    public boolean isSobregiroHabilitado() {
        return sobregiroHabilitado;
    }

    public void setSobregiroHabilitado(boolean sobregiroHabilitado) {
        this.sobregiroHabilitado = sobregiroHabilitado;
    }
}
