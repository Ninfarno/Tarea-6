package SolicitarDatos;

public class Errores {

    // Valor fuera de rango
    public static class Valorfuera extends Exception {
        public Valorfuera(String mensaje) {
            super(mensaje);
        }
    }

    // Más valores que los permitidos (por ejemplo, fuera del tamaño de un arreglo)
    public static class Sobrevalores extends Exception {
        public Sobrevalores(String mensaje) {
            super(mensaje);
        }
    }

    // Datos nulos
    public static class Datosnull extends Exception {
        public Datosnull(String mensaje) {
            super(mensaje);
        }
    }
}
