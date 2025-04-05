package API;
/* El * hace que se importen todas las clases de ese paquete, es bueno saberlo para la
proxima que no sepa cual es el que ocupo -_-' */

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.net.*;
import javax.swing.*;

public class Ejemplos {

    public class DemoAPI {

        public static void main(String[] args) {
            // 1. Lista de mensajes (java.util)
            List<String> mensajes = new ArrayList<>();

            // 2. Leer entrada del usuario (java.util.Scanner)
            Scanner scanner = new Scanner(System.in);
            System.out.print("Escribe un mensaje: ");
            String mensaje = scanner.nextLine();

            // 3. Agregar fecha y hora (java.time)
            String mensajeConFecha = "[" + LocalDateTime.now() + "] " + mensaje;
            mensajes.add(mensajeConFecha);

            // 4. Guardar en archivo (java.nio.file + java.io)
            try {
                Path archivo = Paths.get("mensajes.txt");
                Files.write(archivo, mensajes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                System.out.println("Mensaje guardado en mensajes.txt");
            } catch (IOException e) {
                System.out.println("Error al escribir el archivo: " + e.getMessage());
            }

            // 5. Tarea en hilo separado (java.lang.Thread)
            Thread tarea = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println("Hilo en segundo plano finalizado.");
                } catch (InterruptedException e) {
                    System.out.println("Error en el hilo.");
                }
            });
            tarea.start();

            // 6. Abrir una URL (java.net)
            try {
                URL url = new URL("https://example.com");
                URLConnection conexion = url.openConnection();
                System.out.println("Conectado a: " + url.getHost());
            } catch (IOException e) {
                System.out.println("Error de conexión: " + e.getMessage());
            }

            // 7. Mostrar ventana (javax.swing)
            JFrame ventana = new JFrame("Ventana de prueba");
            JLabel etiqueta = new JLabel("¡Hola desde Swing!", SwingConstants.CENTER);
            ventana.add(etiqueta);
            ventana.setSize(300, 100);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setVisible(true);
        }
    }
}
