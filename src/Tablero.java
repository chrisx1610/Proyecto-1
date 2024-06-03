/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private final char[][] matriz;
    private final String[] diccionario;
    private final List<Integer>[] grafo;
    private final int[][] direcciones = {
        {0, 1}, {1, 0}, {1, 1}, {-1, 1},
        {0, -1}, {-1, 0}, {-1, -1}, {1, -1}
    };

    public Tablero(char[][] matriz, String[] diccionario) {
        this.matriz = matriz;
        this.diccionario = diccionario;
        this.grafo = new List[matriz.length * matriz[0].length];
        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new ArrayList<>();
        }
        construirGrafo(matriz);
    }

    private void construirGrafo(char[][] matriz) {
        int n = matriz.length;
        int m = matriz[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int nodoActual = i * m + j;
                for (int[] dir : direcciones) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < m) {
                        int nodoVecino = x * m + y;
                        grafo[nodoActual].add(nodoVecino);
                    }
                }
            }
        }
    }

    public void BuscarPalabras() {
        for (String palabra : diccionario) {
            palabra = palabra.toUpperCase();
            boolean encontrada = false;
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    if (dfs(i, j, palabra, 0, new boolean[matriz.length * matriz[0].length])) {
                        encontrada = true;
                        break;
                    }
                }
                if (encontrada) break;
            }

            if (encontrada) {
                System.out.println("La palabra '" + palabra + "' se encuentra en el tablero.");
            } else {
                System.out.println("La palabra '" + palabra + "' no se encuentra en el tablero.");
            }
        }
    }

    private boolean dfs(int i, int j, String palabra, int indice, boolean[] visitado) {
        boolean encontrada = false;
        if (indice == palabra.length()) {
            encontrada = true;
        } else {
            int n = matriz.length;
            int m = matriz[0].length;
            if (i < 0 || i >= n || j < 0 || j >= m || visitado[i * m + j] || matriz[i][j] != palabra.charAt(indice)) {
                encontrada = false;
            } else {
                visitado[i * m + j] = true;
                for (int[] dir : direcciones) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < m) {
                        if (dfs(x, y, palabra, indice + 1, visitado)) {
                            encontrada = true;
                            break;
                        }
                    }
                }
                visitado[i * m + j] = false;
            }
        }
        return encontrada;
    }
}
