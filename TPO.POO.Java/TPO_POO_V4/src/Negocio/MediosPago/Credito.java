package Negocio.MediosPago;
import Negocio.Pago;
public class Credito extends Pago {
    private int cuotas;
    private double recargo;
    private double total;

    public Credito( int cuotas ){
        this.cuotas = cuotas;
    }
    public double calcularRecargo(int cuotas) {
        if (cuotas == 2){
            recargo = getMontoVenta() * 0.06 ;
        }else if(cuotas == 3){
            recargo = getMontoVenta() * 0.12 ;
        }else {
            recargo = getMontoVenta() * 0.2 ;
        }
        return recargo;
    }

    public double calcularTotal() {
        total = getMontoVenta() + calcularRecargo(cuotas);
        return total;
    }



}