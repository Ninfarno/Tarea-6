package API;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.*;
import java.security.*;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;
import java.sql.*;

public class Ejemplos2 {
    public static void main(String[] args) {
        List<String> palabras = Arrays.asList("casa", "auto", "perro", "123cosa", "gato123");

        // 1. Usar Predicate y Lambda para filtrar palabras que no contengan números
        Predicate<String> sinNumeros = palabra -> !palabra.matches(".*\\d.*");
        List<String> filtradas = new ArrayList<>();
        for (String p : palabras) {
            if (sinNumeros.test(p)) {
                filtradas.add(p);
            }
        }

        System.out.println("Palabras sin números: " + filtradas);

        // 2. Expresiones regulares para detectar palabras que terminan en vocal
        Pattern patron = Pattern.compile("[aeiou]$");
        for (String palabra : filtradas) {
            Matcher matcher = patron.matcher(palabra);
            if (matcher.find()) {
                System.out.println("La palabra \"" + palabra + "\" termina en vocal.");
            }
        }

        // 3. Generar hash SHA-256 de las palabras (java.security)
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            for (String palabra : filtradas) {
                byte[] hash = digest.digest(palabra.getBytes());
                System.out.println("Hash SHA-256 de " + palabra + ": " + bytesAHex(hash));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 4. Guardar las palabras en un archivo ZIP
        try (FileOutputStream fos = new FileOutputStream("palabras.zip");
             ZipOutputStream zipOut = new ZipOutputStream(fos);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(baos)) {

            for (String palabra : filtradas) {
                writer.println(palabra);
            }
            writer.flush();
            zipOut.putNextEntry(new ZipEntry("palabras.txt"));
            zipOut.write(baos.toByteArray());
            zipOut.closeEntry();
            System.out.println("Archivo palabras.zip creado.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 5. Conexión a base de datos en memoria (JDBC)
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE palabras (texto TEXT)");
            for (String palabra : filtradas) {
                stmt.execute("INSERT INTO palabras (texto) VALUES ('" + palabra + "')");
            }

            ResultSet rs = stmt.executeQuery("SELECT * FROM palabras");
            System.out.println("Palabras guardadas en base de datos:");
            while (rs.next()) {
                System.out.println(" - " + rs.getString("texto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo auxiliar para convertir hash a texto hexadecimal
    public static String bytesAHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
