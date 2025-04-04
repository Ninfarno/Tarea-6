package SolicitarDatos;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
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
                if (NClientes < 1) {
                    throw new Errores.Valorfuera("El numero de clientes debe ser al menos de 1"); //Genera un error para saltar al catch
                }
                break;
            } catch (Errores.Valorfuera e) {
                System.out.println("Error!!: " + e.getMessage());
            }
        }
        System.out.println();
        OrdenCompra[] ordenes = new OrdenCompra[NClientes];

        for (int i = 0; i < NClientes; i++) {
            int NProductos = 0;
            String[] datosCliente;
            OrdenCompra orden = null;
            Cliente cliente = null;

            while(cliente == null) {
                System.out.print("Ingrese los datos del cliente " + (i + 1) + " (Nombre; Direccion):");
                try {
                    datosCliente = scanner.nextLine().split(";");
                    if (datosCliente.length != 2){
                        throw new Errores.Datosnull("Hay datos sin completar");
                    } else if (datosCliente[0].trim().isEmpty() || datosCliente[1].trim().isEmpty()){
                        throw new Errores.Valorfuera("Debe de completar 2 datos: Nombre; Direccion");
                    }
                        cliente = new Cliente(datosCliente[0].trim(), datosCliente[1].trim());
                        orden = new OrdenCompra("Orden " + (i + 1));
                        orden.setCliente(cliente);
                        break;
                    
                }catch(Errores.Valorfuera | Errores.Datosnull e){
                    System.out.println("Error!!: "+e.getMessage());
                }
            }

            while (true) {
                System.out.print("Ingrese el número de Productos (Max. 4): ");
                try {
                    NProductos = scanner.nextInt();
                    scanner.nextLine();
                    if (NProductos < 1|| NProductos>4) {
                        throw new Errores.Valorfuera("Los valores estan por fuera");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Error!!: El valor debe ser un numero");
                    scanner.nextLine();
                } catch (Errores.Valorfuera e){
                    System.out.println("Error!!: " + e.getMessage());
                }
            }

            for (int j = 0; j < NProductos; j++) {
                while (true) {
                    System.out.print("Ingrese los datos del producto " + (j + 1) + " (Fabricante; Nombre; Precio):");
                    String[] datosProducto = scanner.nextLine().split(";");

                    double precio = 0;
                    try {
                        //Analiza si los datos del arreglo son validos de lo contrario saltara un error //
                        if (datosProducto.length != 3) {
                            throw new Errores.Datosnull("Deben de ser 3 datos: Fabricante; Nombre; Precio");
                        }
                        precio = Double.parseDouble(datosProducto[2].trim());

                        if (precio < 0) {
                            throw new Errores.Valorfuera("El precio debe ser positivo");
                        }

                        if(datosProducto[0].trim().isEmpty() || datosProducto[1].trim().isEmpty()){
                            throw new Errores.Datosnull("Hay datos incompletos: Fabricante; Nombre; Precio");
                        }

                        Producto producto = new Producto(datosProducto[0].trim(), datosProducto[1].trim(), precio);
                        orden.addProducto(producto);

                        break;
                    } catch (Errores.Valorfuera | Errores.Datosnull e) {
                        System.out.println("Error!!: " + e.getMessage());
                        j--;
                        continue;
                    } catch (NumberFormatException e) {
                        System.out.println("Error!!: El precio tiene que ser un numero");
                        j--;
                    }
                }
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
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Detalles de la Orden: " + orden.getDescripcion()
                + "\nFecha de la Orden: " + orden.getFecha().format(formato));
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
                System.out.println("          - Fabricante: " + producto.getFabricante() + ", Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
                granTotal += producto.getPrecio();
            }
        }
        System.out.println("Gran Total: " + granTotal);
        System.out.println("------------------------------");
    }

}