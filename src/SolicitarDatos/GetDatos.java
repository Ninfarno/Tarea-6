package SolicitarDatos;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GetDatos {
    static Scanner scanner = new Scanner(System.in);

    public static int num(int Max, String Mensaje) {
        int num = 0;

        while (true) {
            System.out.print(Mensaje);
            try {
                num = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                if (num < Max) {
                    throw new Errores.Valorfuera("El numero debe ser entre 1 y "+Max); //Genera un error para saltar al catch
                }
                return num;
            } catch (Errores.Valorfuera e) {
                System.out.println("Error!!: " + e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Error!!: El valor debe ser un numero");
                scanner.nextLine();
            }
        }
    }

    public static Cliente Cliente(int i) {
        String[] datosCliente;
        while (true) {
            System.out.print("Ingrese los datos del cliente " + (i + 1) + " (Nombre; Direccion):");
            try {
                datosCliente = scanner.nextLine().split(";");
                if (datosCliente.length != 2) {
                    throw new Errores.Datosnull("Hay datos sin completar");
                } else if (datosCliente[0].trim().isEmpty() || datosCliente[1].trim().isEmpty()) {
                    throw new Errores.Valorfuera("Debe de completar 2 datos: Nombre; Direccion");
                }
                return new Cliente(datosCliente[0].trim(), datosCliente[1].trim());

            } catch (Errores.Valorfuera | Errores.Datosnull e) {
                System.out.println("Error!!: " + e.getMessage());
            }
        }
    }

    public static Producto producto(int j) {

        while (true) {
            System.out.print("Ingrese los datos del producto " + (j + 1) + " (Fabricante; Nombre; Precio):");
            String[] Datosin = scanner.nextLine().split(";");

            try {
                //Analiza si los datos del arreglo son validos de lo contrario saltara un error //
                if (Datosin.length != 3) {
                    throw new Errores.Datosnull("Deben de ser 3 datos: Fabricante; Nombre; Precio");
                }
                String fabricante = Datosin[0].trim();
                String producto = Datosin[1].trim();
                double precio = Double.parseDouble(Datosin[2].trim());

                if (precio < 0) {
                    throw new Errores.Valorfuera("El precio debe ser positivo \nRecuerde usar ';' para separar los datos");
                }

                if (Datosin[0].trim().isEmpty() || Datosin[1].trim().isEmpty()) {
                    throw new Errores.Datosnull("Hay datos incompletos: Fabricante; Nombre; Precio");
                }

                //
                return new Producto(fabricante, producto, precio);
                //

            } catch (Errores.Valorfuera | Errores.Datosnull e) {
                System.out.println("Error!!: " + e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Error!!: El precio tiene que ser un numero");
            }
        }
    }

    public static void OrdenFinal(OrdenCompra orden) {
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
                System.out.println("          - " + producto);
                granTotal += producto.getPrecio();
            }
        }
        System.out.printf("Gran Total: %.2f \n" + granTotal);
        System.out.println("------------------------------");
    }
}
