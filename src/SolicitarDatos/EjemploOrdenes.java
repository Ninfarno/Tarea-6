package SolicitarDatos;

import java.util.Scanner;

public class EjemploOrdenes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int NClientes = GetDatos.num(2, "Ingrese el numero de clientes: ");

        System.out.println();
        OrdenCompra[] ordenes = new OrdenCompra[NClientes];

        for (int i = 0; i < NClientes; i++) {

            Cliente cliente = GetDatos.Cliente(i);

            OrdenCompra orden = new OrdenCompra("Orden " + (i + 1));
            orden.setCliente(cliente);

            int NProductos = GetDatos.num(4, "Ingrese el numero de Productos (Max. 4): ");

            for (int j = 0; j < NProductos; j++) {
                Producto producto = GetDatos.producto(j);
                orden.addProducto(producto);
            }

            ordenes[i] = orden;
            System.out.println();
        }

        System.out.println("\nResumen de órdenes:");
        for (OrdenCompra orden : ordenes) {
            GetDatos.OrdenFinal(orden);
            System.out.println();
        }
        scanner.close();
    }

    //Es demasiado para documentar y ya no doy para mas -.-'
}