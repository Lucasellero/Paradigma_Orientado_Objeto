package Negocio;

public class Producto {
    private int id;
    private String descripcion;
    private float preciounit;
    private int stockact;
    private int stockmin;
    private boolean disponible = true;

    public void modificarProducto(String descripcion, float preciounit, int stockact, int stockmin) {
        this.descripcion = descripcion;
        this.preciounit = preciounit;
        this.stockact = stockact;
        this.stockmin = stockmin;
    }

    public void darBaja() {
        this.disponible = false;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPreciounit(float preciounit) {
        this.preciounit = preciounit;
    }

    public void setStockact(int stockact) {
        this.stockact = stockact;
    }

    public void setStockmin(int stockmin) {
        this.stockmin = stockmin;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }

    public float getPreciounit() {
        return preciounit;
    }

    public int getStockmin() {
        return stockmin;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getStockact() {
        return stockact;
    }

    public String ToString() {
        String x = "";
        if (stockmin >= stockact) {
            x = "Encargar";
        }
        return id + " " + descripcion + " " + preciounit + " " + stockact + " " + stockmin+" "+ x;

    }
}
