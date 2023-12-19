package main.lab1estructura2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Acertijo {

    static ArrayList<ArrayList<String>> acertijos = new ArrayList<>();

    public Acertijo() {
        leer_archivo();
    }

    public static void leer_archivo() {
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("acertijos_2" + ".txt"));
                String line = null; //definición de line
                while ((line = br.readLine()) != null) {
                    String temp[] = line.split("::");
                    //System.out.println(temp[0] + " " + temp[1] + " " + temp[2] + " " + temp[3] + " " + temp[4] + " " + temp[5]);
                    ArrayList<String> temp2 = new ArrayList<>();
                    String reemplazar = "Â";
                    for (char c : reemplazar.toCharArray()) {
                        temp[1] = temp[1].replace(String.valueOf(c), "");
                    }
                    temp2.add(temp[0]);
                    temp2.add(temp[1]);
                    temp2.add(temp[2]);
                    acertijos.add(temp2);
                }
                br.close();
                hay = true;

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hay = true;
            }
        }
    }
}
