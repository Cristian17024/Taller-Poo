import java.io.*;
import java.util.*;

public class ContadorDePalabras {

    static int palabrasTotales;
    static int lineasTotales;
    static Map<String, Integer> frecuenciaPalabras = new HashMap<>();

    public static void main(String[] args) {

        palabrasTotales = 0;
        lineasTotales = 0;

        File archivo = new File("C:\\Users\\ISAI LEDESMA MELENA\\Desktop\\Poo\\Taller\\Trabajo 5\\Aforos.txt");

        contarPalabrasLineas(archivo);
        
        guardarFrecuenciaPalabras();
    }

    public static void contarPalabrasLineas(File archivo) {
        try {
            if (archivo.exists()) {
                BufferedReader archivoLeer = new BufferedReader(new FileReader(archivo));

                String lineaLeida;
                while ((lineaLeida = archivoLeer.readLine()) != null) {
                    System.out.println(lineaLeida);

                    // Contar las palabras en la línea
                    StringTokenizer st = new StringTokenizer(lineaLeida);

                    lineasTotales++;
                    while (st.hasMoreTokens()) {
                        String palabra = st.nextToken().toLowerCase();  
                        palabrasTotales++;

                        // Contar la frecuencia de la palabra
                        frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                    }
                }

                System.out.println("");
                System.out.println("Número de palabras totales: " + palabrasTotales);
                System.out.println("Número de líneas totales: " + lineasTotales);

                archivoLeer.close();
            } else {
                System.out.println("No encontré el archivo");
            }
        } catch (Exception e) {
            System.out.println("No puedo leer el archivo");
            e.printStackTrace();
        }
    }

    public static void guardarFrecuenciaPalabras() {
        File salida = new File("C:\\Users\\ISAI LEDESMA MELENA\\Desktop\\Poo\\Taller\\frecuencia_palabras.txt");
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(salida))) {
            bw.write("palabra,frecuencia\n");

            for (Map.Entry<String, Integer> entry : frecuenciaPalabras.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue() + "\n");
            }

        } catch (IOException e) {
            System.out.println("No se guardó el archivo");
            e.printStackTrace();
        }
    }
}
