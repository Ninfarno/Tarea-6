package SolicitarDatos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompra {
    private static int contador = 1;
    private int tipo;
    private String descripcion;
    private LocalDateTime fecha;
    private Cliente cliente;
    private List<Producto> productos;

    public OrdenCompra(String descripcion) {
        this.tipo = contador++;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
        this.productos = new ArrayList<>();
    }

    public int getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void addProducto(Producto producto) {
        productos.add(producto);
    }
}