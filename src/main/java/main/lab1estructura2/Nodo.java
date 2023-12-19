package main.lab1estructura2;

import java.util.ArrayList;
import java.util.Random;

public class Nodo {
    Random rd = new Random();
    Nodo izquierdo;
    Nodo derecho;
    String nombre;
    int dato;
    int alturaNodo;
    ArrayList<String> acertijo;
    static int id = 0;
    
    
    public Nodo(int dato){
        this.izquierdo = null;
        this. derecho = null;
        this.dato = dato;
        this.alturaNodo = 1;
        Acertijo.leer_archivo();
        this.acertijo = Acertijo.acertijos.get(rd.nextInt(Acertijo.acertijos.size()));
        this.nombre = Acertijo.acertijos.get(id).get(0);
        id += 1;
    }
    
    public void cambioAc(){
        ArrayList<String> nuevo = Acertijo.acertijos.get(rd.nextInt(Acertijo.acertijos.size()));
//        while (nuevo.get(1).equalsIgnoreCase(this.acertijo.get(1))){
//            nuevo = Acertijo.acertijos.get(rd.nextInt(Acertijo.acertijos.size()));
//        }
        this.acertijo = nuevo;
    }
}