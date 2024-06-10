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
            Listaenlazada diccionarioList = new Listaenlazada();
            Listaenlazada matrizList = new Listaenlazada();

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
            diccionario = new String[diccionarioList.size()];
            Listaenlazada.Node node = diccionarioList.head;
            for (int i = 0; i < diccionarioList.size(); i++) {
                diccionario[i] = (String) node.element;
                node = node.next;
            }

            // Procesar matriz
            int n = (int) Math.sqrt(matrizList.size());
            matriz = new char[n][n];
            node = matrizList.head;
            for (int i = 0; i < n; i++) {
                String[] fila = ((String) node.element).split(",");
                for (int j = 0; j < n; j++) {
                    matriz[i][j] = fila[j].charAt(0);
                }
                node = node.next;
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

class Listaenlazada {
    Node head;
    int size;

    public Listaenlazada() {
        head = null;
        size = 0;
    }

    public void add(Object element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public class Node {
        Object element;
        Node next;

        public Node(Object element) {
            this.element = element;
            this.next = null;
        }
    }
}