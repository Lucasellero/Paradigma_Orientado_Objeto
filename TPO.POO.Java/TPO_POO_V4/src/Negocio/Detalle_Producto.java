package Negocio;

public class Detalle_Producto {

    private int id;
    private String descripcion;
    private int cantidad;
    private float precioXUnid;
    private float precioTotal;
    Producto p;

    public int getId(){
        return id;
    }
    public int getCantidad(){
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecioXUnid() {
        return precioXUnid;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public String toString(){
        return "Id: "+this.id+" Descripción: "+this.descripcion+" Cantidad: "+this.cantidad+" Precio unitario:  "+this.precioXUnid+" Subtotal:  "+this.precioTotal;
    }

    public Detalle_Producto(int cantidad, Producto p){
        this.id = p.getId();
        this.descripcion=p.getDescripcion();
        this.cantidad= cantidad;
        this.precioXUnid = p.getPreciounit();
        this.precioTotal = cantidad * precioXUnid;
    }
}