package org.example;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Principal {
    public static void main(String[] args) {

        /*
        List<Producto> listaProductos = new ArrayList<>();

        Producto producto1 = new Producto("Tortilla de patata", new BigDecimal("4.5"), 10);
        Producto producto2 = new Producto("Jamón serrano", new BigDecimal("3.80"), 10);
        Producto producto3 = new Producto("Huevos de gallina campera", new BigDecimal("3.50"), 10);
        Producto producto4 = new Producto("Arroz redondo", new BigDecimal("1.20"), 10);
        Producto producto5 = new Producto("Salmón congelado", new BigDecimal("6.80"), 10);

*/
        int opcion;

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Opción 1: ver los productos.\n" +
                            "Opción 2: agregar un producto.\n" +
                            "Opcion 3: crear un archivo.\n" +
                            "Opción 4: salir."));

            switch (opcion) {

                case 1 -> {

                    try (BufferedReader br = new BufferedReader(new FileReader(JOptionPane.showInputDialog("¿Qué archivo quieres leer?")))) {

                        StringBuilder sb = new StringBuilder();
                        String linea;

                        while ((linea = br.readLine()) != null) {

                            sb.append(linea)
                                    .append(System.lineSeparator());
                        }

                        JOptionPane.showMessageDialog(null, sb.toString());

                    } catch (IOException io) {
                        System.out.println("No se pudo leer el archivo");
                    }
                }

                case 2 -> {

                    String nombreArchivo = (JOptionPane.showInputDialog("Elige el archivo .txt"));

                    File archivoInsertar = new File(nombreArchivo);

                    String nombre = JOptionPane.showInputDialog("Inserta el nombre del producto.");
                    String precio = JOptionPane.showInputDialog("Inserta el precio del producto.");
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Inserta el stock del producto"));

                    Producto productoInsertar = new Producto(nombre, new BigDecimal(precio), cantidad);

                    agregarProductos(archivoInsertar.getName(), productoInsertar);

                }

                case 3 -> crearFichero(JOptionPane.showInputDialog(
                        "Introduce el nombre del archivo .txt que quieres crear."));

                case 4 -> System.out.println("Saliendo del programa.");
            }

        } while (opcion != 4);

    }

    public static void crearFichero(String nombre) {

        File file = new File(nombre);

        if (!file.exists()) {

            try {

                if (file.createNewFile()) System.out.println("Se creó el nuevo archivo " + nombre);
                 else  System.out.println("No se pudo crear el archivo.");
            } catch (IOException io) {
                System.out.println("Hubo un problema al crear el archivo");
            }
        }
    }

    public static void agregarProductos(String fichero, Producto producto) {

        File file = new File(fichero);

        if (!file.exists() || !file.canRead() || !file.isFile()) {

            System.out.println("No se pude realizar la operación. " +
                    "Puede que el fichero no exista, que no se encuentre en la ruta proporcionada " +
                    "o que no sea del tipo adecuado");

        } else {

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                StringBuilder textCompleto = new StringBuilder("Productos\n");

                String linea;
                while ((linea = br.readLine()) != null) {

                    textCompleto.append(linea);
                }

                if (!textCompleto.toString().contains(producto.getNombre())) {

                    Files.write(Paths.get(fichero),
                            (producto.verDetalle() + System.lineSeparator()).getBytes(),
                            StandardOpenOption.APPEND,
                            StandardOpenOption.CREATE);

                }
            } catch (IOException io) {
                JOptionPane.showMessageDialog(null, "Saliendo del programa.");
            }
        }
    }
}