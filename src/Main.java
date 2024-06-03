/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ValidadorTxt validadorTxt = new ValidadorTxt("entrada.txt");
        if (validadorTxt.leerArchivo()) {
            char[][] matriz = validadorTxt.Matriz();
            String[] diccionario = validadorTxt.Diccionario();
            Tablero tablero = new Tablero(matriz, diccionario);
            tablero.BuscarPalabras();
        } else {
            System.out.println("Error al leer el archivo de entrada.");
        }
    }
}

