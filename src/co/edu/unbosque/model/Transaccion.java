package co.edu.unbosque.model;

public abstract class Transaccion {
    protected String fecha; // AAAAMM
    protected double monto;
    protected String descripcion;

    public Transaccion(String fecha, double monto, String descripcion) {
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
