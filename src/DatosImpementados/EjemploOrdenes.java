package DatosImpementados;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EjemploOrdenes {

    public static void main(String[] args) {
        // Crear clientes
        Cliente cliente1 = new Cliente("Sebastian", "Calle Lazaro Cardenas #123");
        Cliente cliente2 = new Cliente("Anali", "Avenida Morelos #456");
        Cliente cliente3 = new Cliente("Carlos", "Calle Hidalgo #789");

        // Crear productos
        Producto producto1 = new Producto("Apple", "Telefono inteligente", 15656.99);
        Producto producto2 = new Producto("Samsung", "Tableta", 10264.99);
        Producto producto3 = new Producto("Asus", "Laptop gamer", 25999.99);
        Producto producto4 = new Producto("Soni", "Audifonos", 560.99);
        Producto producto5 = new Producto("Mi Alegria", "Mi primer juego de quimica", 380.98);
        Producto producto6 = new Producto("Avon", "Pastilla de jabon", 29.99);
        Producto producto7 = new Producto("La Costeña", "Chiles chipotle", 25.99);
        Producto producto8 = new Producto("Helmans", "Ketchup", 28.5);

        // Crear órdenes de compra
        OrdenCompra orden1 = new OrdenCompra("Orden 1");
        orden1.setCliente(cliente1);
        orden1.addProducto(producto1);
        orden1.addProducto(producto2);
        orden1.addProducto(producto3);
        orden1.addProducto(producto4);

        OrdenCompra orden2 = new OrdenCompra("Orden 2");
        orden2.setCliente(cliente2);
        orden2.addProducto(producto5);
        orden2.addProducto(producto6);
        orden2.addProducto(producto7);
        orden2.addProducto(producto8);

        OrdenCompra orden3 = new OrdenCompra("Orden 3");
        orden3.setCliente(cliente3);
        orden3.addProducto(producto1);
        orden3.addProducto(producto5);
        orden3.addProducto(producto7);
        orden3.addProducto(producto2);

        // Imprimir detalles de las órdenes
        imprimirOrden(orden1);
        imprimirOrden(orden2);
        imprimirOrden(orden3);
    }

    // Metodo para imprimir los detalles de una orden
    public static void imprimirOrden(OrdenCompra orden) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

        System.out.println("Detalles de la Orden: " + orden.getDescripcion());
        System.out.println("Fecha de la orden: " + orden.getFecha().format(formato));//La fecha con el formato establecido
        System.out.println("Cliente: " + orden.getCliente().getNombre());
        System.out.println("Dirección: " + orden.getCliente().getDireccion());

        double granTotal = 0;
        System.out.println("Productos:");
        for (Producto producto : orden.getProductos()) {
            if (producto != null) {
                System.out.println("  - Nombre: " + producto.getFabricante() + ", Descripción: " + producto.getNombre() + ", Cantidad: " + producto.getPrecio());
                granTotal += producto.getPrecio(); // Se van sumando los precios de los productos
            }
        }

        System.out.println("Gran Total: " + granTotal);
        System.out.println();
        System.out.println("------------------------------");
    }
}
