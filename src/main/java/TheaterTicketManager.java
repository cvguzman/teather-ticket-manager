import Models.Play;

import java.util.Scanner;

public class TheaterTicketManager {
    public static void main(String[] args) {
        // :::::: CONSTANTES ::::::
        final Play[] plays = {
                new Play("Aladin"),
                // ... Agregar más obras.
        };

        // :::::: VARIABLES DE INSTANCIA ::::::
        Scanner scanner = new Scanner(System.in);
        MapProvider mapProvider = new MapProvider();

        // :::::: VARIABLES ::::::
        boolean sessionFinished = false;

        // :::::: INICIO DEL PROGRAMA ::::::
        // El programa finalizará cuando "sessionFinished = true"
        while (!sessionFinished) {
            // ...
            // ... Definir flujo
            // ...
            sessionFinished = true;
        }
    }
}