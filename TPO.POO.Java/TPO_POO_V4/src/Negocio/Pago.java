package Negocio;

public class Pago {
    protected double montoVenta;

    public double calcularTotal(){
        return getMontoVenta();
    }

    public double getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }

}