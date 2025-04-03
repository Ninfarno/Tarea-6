package SolicitarDatos;

import java.util.Scanner;

public class EjemploOrdenes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int NClientes;

        while (true) {
            System.out.print("Ingrese el número de clientes: ");
            try {
                NClientes = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                if(NClientes<1){
                    throw new NumberFormatException(); //Genera un error para saltar al catch
                }
                break;
            } catch(NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }

        OrdenCompra[] ordenes = new OrdenCompra[NClientes];

        for (int i = 0; i < NClientes; i++) {
            int NProductos = 0;
            System.out.print("Ingrese los datos del cliente " + (i + 1) + " (nombre;direccion):");
            String[] datosCliente = scanner.nextLine().split(";");
            Cliente cliente = new Cliente(datosCliente[0], datosCliente[1]);

            OrdenCompra orden = new OrdenCompra("Orden " + (i + 1));
            orden.setCliente(cliente);

            while (true) {
                System.out.print("Ingrese el número de clientes: ");
                try {
                    NProductos = scanner.nextInt();
                    scanner.nextLine();
                    if(NProductos<1){
                        throw new NumberFormatException();
                    }
                    break;
                } catch(NumberFormatException e) {
                    System.out.println("Ingrese un número válido.");
                }
            }

            for (int j = 0; j < NProductos; j++) {
                System.out.print("Ingrese los datos del producto " + (j + 1) + " (fabricante;nombre;precio):");
                String[] datosProducto = scanner.nextLine().split(";");
                double precio;
                try {
                    precio = Double.parseDouble(datosProducto[2]);
                    if (precio <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Precio inválido. Debe ser un número positivo.");
                    j--;
                    continue;
                }
                Producto producto = new Producto(datosProducto[0], datosProducto[1], precio);
                orden.addProducto(producto);
            }

            ordenes[i] = orden;
            System.out.println();
        }

        System.out.println("\nResumen de órdenes:");
        for (OrdenCompra orden : ordenes) {
            imprimirOrden(orden);
            System.out.println();
        }

        scanner.close();
    }

    public static void imprimirOrden(OrdenCompra orden) {
        System.out.println("Detalles de la Orden: " + orden.getDescripcion());
        if (orden.getCliente() != null) {
            System.out.println("Cliente: " + orden.getCliente().getNombre());
            System.out.println("Dirección: " + orden.getCliente().getDireccion());
        } else {
            System.out.println("Cliente: No asignado");
            System.out.println("Dirección: No asignada");
        }

        double granTotal = 0;
        System.out.println("Productos:");
        for (Producto producto : orden.getProductos()) {
            if (producto != null) {
                System.out.println("  - Fabricante: " + producto.getFabricante() + ", Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
                granTotal += producto.getPrecio();
            }
        }
        System.out.println("Gran Total: " + granTotal);
        System.out.println("------------------------------");
    }
}