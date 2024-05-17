package Negocio;
import Negocio.MediosPago.Credito;
import Negocio.MediosPago.Debito;
import Negocio.MediosPago.Efectivo;

import java.util.*;

public class Venta {

    private int id;
    Pago Mediopago;
    private double subtotal = 0;
    private double total;
    private ArrayList<Detalle_Producto>factura;

    public Venta(){
        factura = new ArrayList<Detalle_Producto>();
    }
    public void setTotal(double total){
        this.total = total;
    }
    public void agregarDetalle(int cantidad, Producto p){
        Detalle_Producto linea= new Detalle_Producto(cantidad,p);
        factura.add(linea);
    }

    public double calcularTotal(){
        for (Detalle_Producto p : factura){
            subtotal += (p.getPrecioXUnid()*p.getCantidad());
        }
        Mediopago.setMontoVenta(subtotal);
        total = Mediopago.calcularTotal();
        return total;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setMediopago(Pago medio) {
        Mediopago = medio;
    }
    public ArrayList<Detalle_Producto> getFactura(){
        return factura;
    }
    public int getId(){
        return id;
    }
    public double getSubtotal(){
        return subtotal;
    }


}
