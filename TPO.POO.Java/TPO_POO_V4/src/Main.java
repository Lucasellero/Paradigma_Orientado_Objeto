import java.util.Scanner;

import Negocio.*;
import Negocio.MediosPago.Credito;
import Negocio.MediosPago.Debito;
import Negocio.MediosPago.Efectivo;

public class Main {
    public static void main(String[] args) {

        Empresa empresa = new Empresa();
        Scanner input = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de facturacion diaria de la empresa "+empresa.getNombre());
        System.out.println();
        System.out.println("Ingrese un numero de acuerdo a la funcionalidad del sistema que desea realizar: ");
        System.out.println("1- Cargar productos\n" +
                "2- Listar productos\n" +
                "3- Listar todos los productos de alta\n" +
                "4- Listar todos los productos dados de baja\n" +
                "5- Modificar un producto\n" +
                "6- Dar de baja un producto\n" +
                "7- Registrar una nueva venta\n" +
                "8- Mostrar listado de todas las ventas\n" +
                "9- Mostrar detalles de la venta\n" +
                "0- Finalizar la carga");

        System.out.print("Opcion: ");
        int opciones = input.nextInt();
        while (opciones != 0) {
            if (opciones == 1) {
                empresa.agregarProducto(1, "Cebolla", 50, 2, 10);
                empresa.agregarProducto(2, "Tomate", 40, 2, 10);
                empresa.agregarProducto(3, "Papa", 30, 2, 15);
                empresa.agregarProducto(4, "Zanahoria", 35, 2, 12);
                empresa.agregarProducto(5, "Lechuga", 20, 15, 8);
                empresa.agregarProducto(6, "Manzana", 60, 48, 20);
                empresa.agregarProducto(7, "Banana", 45, 60, 25);
                empresa.agregarProducto(8, "Naranja", 55, 42, 18);
                empresa.agregarProducto(9, "Uva", 70, 28, 14);
                empresa.agregarProducto(10, "Morron", 70, 22, 10);
                empresa.agregarProducto(11, "Pepino", 25, 30, 12);
                empresa.agregarProducto(12, "Sandía", 80, 18, 8);
                empresa.agregarProducto(13, "Kiwi", 65, 35, 15);
                empresa.agregarProducto(14, "Calabaza", 40, 20, 10);
                empresa.agregarProducto(15, "Melón", 75, 24, 12);
                empresa.agregarProducto(16, "Frutilla", 70, 30, 12);
                empresa.agregarProducto(17, "Cereza", 90, 15, 8);
            } else if (opciones == 2) {
                System.out.println("Lista de todos los productos :");
                empresa.listarProductos();
            } else if (opciones == 3) {
                System.out.println("Lista de productos de alta: ");
                empresa.listarProductosAlta();
            } else if (opciones == 4) {
                System.out.println("Lista de productos dados de baja: ");
                empresa.listarProductosBaja();
            } else if (opciones == 5) {
                empresa.modificarProducto(2, "Cebolla", 99, 250, 25); //Sin usar scanner por comodidad
            } else if (opciones == 6) {
                System.out.print("Ingrese el id del producto que desea dar de baja: ");
                int idBaja = input.nextInt();
                empresa.bajarProducto(idBaja);
            } else if (opciones == 7) {
                System.out.print("Ingrese el id de la venta: ");
                int idVenta = input.nextInt();
                while (idVenta != -1) {
                    while (empresa.existeVenta(idVenta)) {
                        System.out.print("Ya existe la venta ingresada. Ingrese un nuevo id de venta: ");
                        idVenta = input.nextInt();
                    }
                    empresa.crearVenta(idVenta);
                    System.out.print("Ingrese el id del producto: ");
                    int idProd = input.nextInt();
                    int cantidad;
                    while (idProd != -1) {
                        while (empresa.findProductXId(idProd) == null){
                            System.out.print("Producto inexistente. Ingrese un id correcto: ");
                            idProd = input.nextInt();
                        }
                        System.out.print("Ingrese la cantidad: ");
                        cantidad = input.nextInt();
                        while (!empresa.HayStock(idProd, cantidad)) {
                            if (cantidad == -1) {
                                break;
                            }
                            int stock = empresa.getStock(idProd);
                            System.out.print("Hay " + stock + " disponible, ingrese una opcion valida o -1 para cancelar: ");
                            cantidad = input.nextInt();
                        }
                        if (cantidad != 0 && cantidad != -1){
                            empresa.registrarVenta(idVenta, idProd, cantidad);
                            empresa.disminuirStock(idProd, cantidad);
                        }
                        System.out.print("Ingrese el id del producto o -1 para finalizar la carga: ");
                        idProd = input.nextInt();
                    }

                    System.out.print("1 Efectivo - 2 Tarjeta de credito - 3 Tarjeta de debito: ");
                    int opcion = input.nextInt();
                    System.out.println();
                    Pago medio;

                    while (opcion > 3 || opcion < 1) {
                        System.out.println("Opcion incorrecta");
                        System.out.print("1 Efectivo - 2 Tarjeta de credito - 3 Tarjeta de debito: ");
                        opcion = input.nextInt();
                    }
                    if (opcion == 1) {
                        medio = new Efectivo();
                    } else if (opcion == 2) {
                        System.out.println("Elija entre:\n"+
                                "2 cuotas con un interes del 6% \n"+
                                "3 cuotas con un interes del 12% \n"+
                                "6 cuotas con un interes del 20%");
                        System.out.print("Cuotas: ");
                        int cuotas = input.nextInt();
                        while (cuotas != 2 && cuotas != 3 && cuotas != 6){
                            System.out.print("Ingrese una opcion correcta: ");
                            cuotas = input.nextInt();
                        }
                        medio = new Credito(cuotas);
                    } else {
                        medio = new Debito();
                    }
                    empresa.seleccionarMedioPago(idVenta, medio);
                    System.out.println();
                    System.out.println("¡Venta creada exitosamente!");
                    System.out.println();
                    System.out.println("Factura: ");
                    String texto = empresa.mostrarFactura(idVenta);
                    System.out.println("Factura: ");
                    System.out.println(texto);
                    double total = empresa.calcularTotal(idVenta);
                    System.out.println("Total: "+total);
                    System.out.println();
                    System.out.print("Ingrese otro id de venta o -1 para terminar la carga: ");
                    idVenta = input.nextInt();

                }
            } else if(opciones == 8){
                System.out.println("Listado de ventas: ");
                empresa.listarVentas();
            }else if (opciones == 9){
                System.out.print("Ingrese el id de la venta de la cual desea conocer su factura: ");
                int idFactura = input.nextInt();
                while (!empresa.existeVenta(idFactura)) {
                    System.out.print("El id ingresado es inexistente. Ingrese un nuevo id de venta: ");
                    idFactura = input.nextInt();
                }
                empresa.mostrarFactura(idFactura);
            }else{
                System.out.println("Opcion invalida");
            }
            System.out.println();
            System.out.print("Ingrese un numero de acuerdo a la funcionalidad del programa que desea realizar: ");
            opciones = input.nextInt();
        }
        System.out.println("¡Programa finalizado!");
    }
}