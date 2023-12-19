package main.lab1estructura2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lab1estructura2 {
    private static void debug(Arbol arbol){ // permite ver todos los valores del juego
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("hoja llegada: " + arbol.hojaLlegada.dato + ", su nivel es "+ arbol.hojaLlegada.alturaNodo);
        System.out.print("nodos visitados: ");
        arbol.visitados.forEach((visitado)-> {
            System.out.print(visitado.dato + ",");
        });
        System.out.println("\nEl arbol es");
        arbol.TreePrinter();
        System.out.println("");
        System.out.println("nodo actual: "+ arbol.actual.dato + ", su nivel es "+ arbol.actual.alturaNodo);
        System.out.println("respuesta: " + arbol.actual.acertijo.get(2));
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
    
    private static int gen(ArrayList<Integer> numeros, Random ra) {
        // crea un valor ramdon y busca si ya esta en la lista, si es verdadero vuelve a ejecutar la misma funcion gen 
        int numero = ra.nextInt(100);
        if (!numeros.contains(numero)) {
            return numero;
        } else {
            return gen(numeros, new Random());
        }
    }

    private static ArrayList<Integer> generarArbol(int cantidad) {
        // creo una lista vacio para luego llenarla con un valor ramdon no repetido que proviene de la funcion gen
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            numeros.add(gen(numeros, new Random()));
        }
        return numeros;
    }

    public static int validacion(String respuesta) { // valida cada entrada del jugador
        if (respuesta.equalsIgnoreCase("izquierda")) {
            return 1;
        } else if (respuesta.equalsIgnoreCase("derecha")) {
            return 2;
        } else if (respuesta.equalsIgnoreCase("salir")) {
            return 3;
        } else if (respuesta.equalsIgnoreCase("debug") ||respuesta.equalsIgnoreCase("0")) {
            return 4;
        }
        return 0;
    }

    public static Arbol crearArbol(int cantidad) { // crea la totalidad de un arbol junto con la seleccion de la hoja de llegada
        Arbol arbolNivel = new Arbol();
        for (Integer valore : generarArbol(cantidad)) {
            arbolNivel.agregar(valore);
        }
        arbolNivel.escogerHojaLlegada(arbolNivel.raiz);
        return arbolNivel;
    }

    public static boolean containsKeywords(String[] input, String keywords) { // validar si la respuesta del usuario contiene la respuesta
        String[] new_keywords = new String[input.length];
        int i = 0;
        for(String keyword : input){
            if(keyword.length() >= 3){
                new_keywords[i] = keyword;
                
            } else{
                new_keywords[i] = "/";
            }
            i++;
        }
        for (String keyword : new_keywords) {
            if (keywords.contains(keyword)) {
                return true; 
            }
        }
        return false;
    }
    
    public static void partida(Arbol arbol, Scanner input, String respuesta, boolean act) {
        System.out.println("\nte parece raro donde estas ¿No?, te encuentras en el centro del bosque oscuro de edinburgo, si quieres pues intentar salir, espero que llegues a la salida");
        while (validacion(respuesta) != 3) {
            while (validacion(respuesta) == 0) {
                if (act) debug(arbol);
                System.out.print("\n"+arbol.actual.nombre + ": ");
                System.out.println(arbol.actual.acertijo.get(1)); // muestra el acertijo
                System.out.print("adivina el acertijo:");
                respuesta = input.nextLine();
                if (validacion(respuesta) == 4) {
                    act = true;
                    respuesta = " ";
                }
                if(containsKeywords(respuesta.split(" "),arbol.actual.acertijo.get(2))) { // comparacion si gano el acertijo
                //if (arbol.actual.acertijo.get(2).equalsIgnoreCase(respuesta)) { 
                    while (validacion(respuesta) != 1 && validacion(respuesta) != 2 && validacion(respuesta) != 3) {
                        System.out.print("adivinaste!, ahora donde quieres ir el camino de la izquierda o el de la derecha:");
                        respuesta = input.nextLine();
                        if (validacion(respuesta) == 1) { // movimiento a la izquierda                                
                            if (arbol.siguiente(arbol.actual, 1)) {
                                arbol.actualizarNodo(arbol.actual.izquierdo);
                            } else {
                                System.out.println("has consumido tu turno de avanzar pero no es posible avanzar por esa ruta intenta por otra diferente \n");
                            }
                        }
                        if (validacion(respuesta) == 2) { // movimiento a la derecha
                            if (arbol.siguiente(arbol.actual, 2)) {
                                arbol.actualizarNodo(arbol.actual.derecho);
                            } else {
                                System.out.println("has consumido tu turno de avanzar pero no es posible avanzar por esa ruta intenta por otra diferente \n");
                            }
                        }
                        if (validacion(respuesta) == 3) break;
                    }
                    if (validacion(respuesta) != 3)respuesta = "";
                    if (arbol.esHoja(arbol.actual)) { // validacion si gano
                        if (arbol.actual == arbol.hojaLlegada) { // victoria
                            System.out.println("\nhas encontrado la salida del bosque oscuro de edinburgo, Felicidades has ganado");
                            System.out.println("este es todo tu recorrido por el bosque");
                            arbol.arbolVisitado();
                            respuesta = "salir";
                        } else { //  llegada a una hoja diferente a la hoja de llegada
                            System.out.println("\nhas llegado a un callejon sin salida donde no esta anhelada tu meta, aqui te muestro tu recorrido del bosque, ¡recuerdalo!");
                            arbol.arbolVisitado();
                            System.out.println("regresaras al centro de este bosque, otra vez");
                            arbol.actualizarNodo(arbol.raiz);
                            respuesta = "";
                        }
                    }
                    break;
                } else { // cambio de acertijo
                    if (validacion(respuesta) != 3 && validacion(respuesta) != 4) {
                        System.out.println("respuesta al acertijo de esta zona del bosque es incorrecta o hablaste en una lengua de la que no tengo conocimiento");
                    }
                    arbol.actual.cambioAc();
                }
            } 
            if (validacion(respuesta) == 1 || validacion(respuesta) == 2) respuesta = " ";
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cantidad[] = {10, 12, 15}; // cantidad de niveles
        
        int estados = 1;
        while (estados == 1) {
            Arbol arbolNivel_1 = crearArbol(cantidad[0]);
            Arbol arbolNivel_2 = crearArbol(cantidad[1]);
            Arbol arbolNivel_3 = crearArbol(cantidad[2]);
            String respuesta = "";
            System.out.println("¡Bienvenido a la Aventura en el Bosque Misterioso!");
            System.out.println("Reglas del juego: \n "
                    + "1. Puedes avanzar en este bosque si ganas acertijos o diferentes pruebas \n "
                    + "2. avanzas en el camino si escribes 'Izquierda' o 'Derecha' al ganar un acertijo \n "
                    + "3. puesdes salir en cualquier momento escribiendo 'Salir' \n");
            String nivel = "";
            while (nivel.equalsIgnoreCase("1") == false && nivel.equalsIgnoreCase("2") == false && nivel.equalsIgnoreCase("3") == false && nivel.equalsIgnoreCase("0") == false) {
                System.out.println("Que nivel de dificultad quieres para el juego, deseas escoger: 1, 2, 3");
                nivel = input.nextLine();
                if (validacion(nivel) == 3) {
                    break;
                }
            }
            boolean act = false;
            if (nivel.equalsIgnoreCase("0") == true) {
                act = true;
                nivel = "1";
            }
            if (validacion(nivel) != 3) {
                switch (Integer.parseInt(nivel)) {
                    case 1:
                        partida(arbolNivel_1, input, respuesta, act);
                        break;
                    case 2:
                        partida(arbolNivel_2, input, respuesta, act);
                        break;
                    case 3:
                        partida(arbolNivel_3, input, respuesta, act);
                        break;
                }
            }
            System.out.println("deseas otra partida 1. si 2. no");
            String salida = input.nextLine();
            if (salida.equalsIgnoreCase("si") == true || true == salida.equalsIgnoreCase("1")) {
                estados = 1;
            } else {
                estados = 2;
            }
        }
    }
}