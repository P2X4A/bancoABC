package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected String numero;
    protected double saldo;
    protected boolean exenta4xMil;
    protected boolean activa;
    protected List<Transaccion> movimientos;

    public Cuenta(String numero, double saldo, boolean exenta4xMil, boolean activa) {
        this.numero = numero;
        this.saldo = saldo;
        this.exenta4xMil = exenta4xMil;
        this.activa = activa;
        this.movimientos = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isExenta4xMil() {
        return exenta4xMil;
    }

    public void setExenta4xMil(boolean exenta4xMil) {
        this.exenta4xMil = exenta4xMil;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public List<Transaccion> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Transaccion> movimientos) {
        this.movimientos = movimientos;
    }
}
