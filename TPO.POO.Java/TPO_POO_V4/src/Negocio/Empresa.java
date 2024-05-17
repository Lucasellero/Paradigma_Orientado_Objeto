package Negocio;
import Negocio.MediosPago.*;

import java.util.*;

public class Empresa {
    private String nombre = "Market All";
    private ArrayList<Producto>ListaProductos;
    private ArrayList<Venta>ListaVentas;

    public String getNombre(){
        return nombre;
    }

    public Empresa(){
        ListaProductos = new ArrayList<Producto>();
        ListaVentas = new ArrayList<Venta>();
    }

    private Venta findSaleXId(int id){
        Venta v =null;
        for(int i =0;i<ListaVentas.size();i++){
            v=ListaVentas.get(i);
            if (v.getId()==id){
                break;
            }else{
                v = null;
            }
        }
        return v;
    }
    public boolean existeVenta(int idVenta) {
        boolean existe = false;
        for (Venta venta : ListaVentas) {
            if (venta.getId() == idVenta) {
                existe = true;
            }
        }
        return existe;
    }
    public Producto findProductXId(int id){
        Producto p =null;
        for(int i =0;i<ListaProductos.size();i++){
            p=ListaProductos.get(i);
            if (p.getId()==id){
                break;
            }else{
                p = null;
            }
        }
        return p;
    }

    public void agregarProducto(int id, String descripcion, float preciounit, int stockact, int stockmin){
        Producto p = new Producto();
        p.setId(id);
        p.setDescripcion(descripcion);
        p.setPreciounit(preciounit);
        p.setStockact(stockact);
        p.setStockmin(stockmin);
        ListaProductos.add(p);
    }
    public void modificarProducto(int id, String nombre, int preciounit, int stock, int stockmin){
        Producto p = findProductXId(id);
        if (p == null){
        }else{
            p.modificarProducto(nombre,preciounit,stock,stockmin); //Sin usar scanner por comodidad
        }
    }
    public void bajarProducto(int id){
        Producto p = findProductXId(id);
        if (p == null){
            System.out.println("No existe el producto");
        }else{
            p.darBaja();
        }
    }
    public void listarProductosAlta(){
        for(int i =0;i<ListaProductos.size();i++){
            Producto p=ListaProductos.get(i);
            if (p.isDisponible()){
                System.out.println(p.ToString());
            }
        }
    }
    public void listarProductosBaja(){
        for(int i =0;i<ListaProductos.size();i++){
            Producto p=ListaProductos.get(i);
            if (!p.isDisponible()){
                System.out.println(p.ToString());
            }
        }
    }

    public void listarProductos() {
        for (int i = 0; i < ListaProductos.size(); i++) {
            Producto p = ListaProductos.get(i);
            System.out.println(p.ToString());
        }
    }

    public void registrarVenta(int idV,int idP, int cantidad) {
        Producto p = findProductXId(idP);
        Venta venta=findSaleXId(idV);
        if (p.isDisponible()) {
            venta.agregarDetalle(cantidad, p);
        }else{
            System.out.println("Producto dado de baja");
        }
    }

    public void listarVentas(){
        for(int i =0;i<ListaVentas.size();i++){
            Venta v=ListaVentas.get(i);
            mostrarFactura(v.getId());
            System.out.println();
        }
    }

    public void crearVenta(int id){
        Venta v = new Venta();
        v.setId(id);
        ListaVentas.add(v);
    }

    public double calcularTotal(int idVenta){  //Por si queremos agregarlo por id
        Venta v = findSaleXId(idVenta);
        double total = v.calcularTotal();
        return total;
    }
    public void seleccionarMedioPago(int idVenta, Pago mediopago){
        Venta v = findSaleXId(idVenta);
        v.setMediopago(mediopago);
    }




    public String mostrarFactura(int id){
        Venta v;
        v = findSaleXId(id);
        ArrayList<Detalle_Producto> factura=v.getFactura();
        String Factura = " ";
        for( Detalle_Producto detalle : factura){
            String renglon = "Id: "+detalle.getId()+"  Nombre: "+detalle.getDescripcion()+"  Cantidad: "+ detalle.getCantidad()+"  Precio unitario: "+detalle.getPrecioXUnid()+"  Precio total: "+detalle.getPrecioTotal();
            Factura +="\n"+ renglon;
        }

        return Factura;
    }

    public int getStock(int id){
        Producto p = findProductXId(id);
        return p.getStockact();
    }
    public boolean HayStock(int id, int cantidad){
        Producto p = findProductXId(id);
        boolean disponible = false;
        if (cantidad <= p.getStockact())
            disponible = true;
        return disponible;
    }

    public void disminuirStock (int id, int cantidad){
        Producto p = findProductXId(id);
        int stock = p.getStockact() - cantidad;
        p.setStockact(stock);
    }
}