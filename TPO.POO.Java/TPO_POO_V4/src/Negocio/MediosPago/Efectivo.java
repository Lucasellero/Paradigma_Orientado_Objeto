package Negocio.MediosPago;
import Negocio.Pago;

public class Efectivo extends Pago {
    private int descuento = 10;
    private double total;
    private double descuentoTotal;

    public double calcularDescuento(double subtotal) {
        descuentoTotal = (subtotal * descuento) / 100;
        return descuentoTotal;
    }

    public double calcularTotal() {
        total = getMontoVenta() - calcularDescuento(getMontoVenta());
        return total;
    }

}
