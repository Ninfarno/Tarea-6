package SolicitarDatos;

import java.time.LocalDateTime;

public class OrdenCompra {
    private static int contador = 1;
    private int tipo;
    private String descripcion;
    private LocalDateTime fecha;
    private Cliente cliente;
    private Producto[] productos;

    public OrdenCompra(String descripcion) {
        this.tipo = contador++;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
        this.productos = new Producto[4];
    }

    public int getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getFecha(){
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void addProducto(Producto producto) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] == null) {
                productos[i] = producto;
                return;
            }
        }
        System.out.println("No se pueden agregar más productos. El límite es de 4.");
    }
}