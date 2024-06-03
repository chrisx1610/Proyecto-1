/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ValidadorTxt {
    private String archivo;
    private char[][] matriz;
    private String[] diccionario;

    public ValidadorTxt(String archivo) {
        this.archivo = archivo;
    }

    public boolean leerArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            ArrayList<String> diccionarioList = new ArrayList<>();
            ArrayList<String> matrizList = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                if (linea.equals("dic")) {
                    while (!(linea = br.readLine()).equals("/dic")) {
                        diccionarioList.add(linea.trim());
                    }
                } else if (linea.equals("tab")) {
                    while (!(linea = br.readLine()).equals("/tab")) {
                        matrizList.add(linea.trim());
                    }
                }
            }

            // Procesar diccionario
            diccionario = diccionarioList.toArray(new String[0]);

            // Procesar matriz
            int n = (int) Math.sqrt(matrizList.size());
            matriz = new char[n][n];
            for (int i = 0; i < n; i++) {
                String[] fila = matrizList.get(i).split(",");
                for (int j = 0; j < n; j++) {
                    matriz[i][j] = fila[j].charAt(0);
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public char[][] Matriz() {
        return matriz;
    }

    public String[] Diccionario() {
        return diccionario;
    }
}
