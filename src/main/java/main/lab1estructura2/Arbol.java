package main.lab1estructura2;

import java.util.ArrayList;
import java.util.Random;

public class Arbol {
    Nodo raiz;
    Nodo actual;
    Nodo hojaLlegada;
    ArrayList<Nodo> hojas;
    ArrayList<Nodo> visitados;

    public Arbol() {
        this.raiz = null;
        this.actual = this.raiz;
        this.hojas = new ArrayList();
        this.visitados = new ArrayList();
    }

    public static int alturaArbol(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return Math.max(alturaArbol(nodo.izquierdo), alturaArbol(nodo.derecho)) + 1;
    }

    private static int getcol(int h) {
        if (h == 1) {
            return 1;
        }
        return getcol(h - 1) + getcol(h - 1) + 1;
    }

    public void agregar(int dato) {
        Nodo nodo = new Nodo(dato);
        if (this.raiz == null) {
            this.raiz = nodo;
        } else {
            agregarRecursivo(this.raiz, nodo);
        }
    }

    private void agregarRecursivo(Nodo nodo, Nodo nuevoNodo) {
        if (nuevoNodo.dato < nodo.dato) {
            if (nodo.izquierdo == null) {
                nodo.izquierdo = nuevoNodo;
            } else {
                agregarRecursivo(nodo.izquierdo, nuevoNodo);
            }
        } else {
            if (nodo.derecho == null) {
                nodo.derecho = nuevoNodo;
            } else {
                agregarRecursivo(nodo.derecho, nuevoNodo);
            }
        }
    }

    public int nivel(Nodo nodo, int dato, int nivel) {
        if (nodo == null) return 0;
        if (nodo.dato == dato) {
            return nivel;
        } else {
            if (dato < nodo.dato) {
                return nivel(nodo.izquierdo, dato,  nivel + 1);
            } else {
                return nivel(nodo.derecho, dato, nivel + 1);
            }
        }
    }
    
    public boolean esHoja(Nodo nodo){
        return nodo.derecho == null && nodo.izquierdo == null;
    }
    
    public void actualizarNodo(Nodo nodo){
        visitados.add(this.actual);        
        this.actual = nodo;
    }
    
    public void escogerHojaLlegada(Nodo nodo){
        if (nodo == null) return;
        if(esHoja(nodo)==true){
            nodo.alturaNodo = nivel(raiz, nodo.dato, 0);
            hojas.add(nodo);
        }
        escogerHojaLlegada(nodo.izquierdo);
        escogerHojaLlegada(nodo.derecho);
        Random rd = new Random();
        int escoger = rd.nextInt();
        for(Nodo hoja : hojas) {
            if(hoja.alturaNodo+1 == Arbol.alturaArbol(this.raiz)) {
                hojaLlegada = hoja;
                if (escoger == rd.nextInt()){
                    break;
                }
            }
        }
        this.actual = this.raiz;
    }
    
    public boolean siguiente(Nodo nodo, int direccion){
        if(direccion == 1 && nodo != null) {
            return nodo.izquierdo != null;
        } else if(direccion != 1 && nodo != null) {
            return nodo.derecho != null;
        }
        return false;
    }
    
    private static void printTree(String[][] M, Nodo root, int col, int row, int height) {
        if (root == null) {
            return;
        }
        M[row][col] = root.nombre;
        printTree(M, root.izquierdo, col - (int) Math.pow(2, height - 2), row + 1, height - 1);
        printTree(M, root.derecho, col + (int) Math.pow(2, height - 2), row + 1, height - 1);
    }
    
    private static void printTree(int[][] M, Nodo root, int col, int row, int height) {
        if (root == null) {
            return;
        }
        M[row][col] = root.dato;
        printTree(M, root.izquierdo, col - (int) Math.pow(2, height - 2), row + 1, height - 1);
        printTree(M, root.derecho, col + (int) Math.pow(2, height - 2), row + 1, height - 1);
    }

    public void TreePrinter() {
        int h = alturaArbol(this.raiz);
        int col = getcol(h);
        int[][] M = new int[h][col];
        printTree(M, this.raiz, col / 2, 0, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < col; j++) {
                if (M[i][j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(M[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public void arbolVisitado() {
        int h = alturaArbol(this.raiz);
        int col = getcol(h);
        String[][] M = new String[h][col];
        printTree(M, this.raiz, col / 2, 0, h);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < col; j++) {
                if ("0".equals(M[i][j])) {
                    System.out.print("    ");
                } else {
                    boolean esta = false;
                    for(Nodo visitado: visitados){
                        if (visitado.nombre.equalsIgnoreCase(M[i][j])) {
                            esta = true;
                        }
                    }
                    if (esta) {
                        System.out.print(M[i][j] + " ");
                    } else if (hojaLlegada.nombre.equals(M[i][j])){
                        System.out.println("("+ M[i][j] + ")" + " ");
                    } else if (this.actual.nombre.equals(M[i][j])){
                        System.out.print(M[i][j] + " ");
                    }else {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println();
        }
    }
}