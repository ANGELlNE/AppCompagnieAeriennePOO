package acap;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import acap.model.Aeroport;
import acap.model.Avion;

public class App {
    private static ArrayList<Aeroport> aeroports = new ArrayList<Aeroport>();
    private static ArrayList<Avion> avions = new ArrayList<Avion>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Aeroport Eglin = new Aeroport("Eglin Air Force Base", "Floride", "La plus grande base aérienne américaine.");
        Aeroport CDG = new Aeroport("CDG", "Île-de-France", "La 2e aéroport le plus important d'Europe.");
        aeroports.add(Eglin);
        aeroports.add(CDG);

        Avion F35 = new Avion("22-5816", "Lockheed Martin F-35 Lightning II", 1);
        Avion SR71 = new Avion("61-7951", "Lockheed SR-71 Blackbird", 2);
        avions.add(F35);
        avions.add(SR71);

        boolean running = true;
        while (running) {
            clearTerminal();
            System.out.println("Bienvenue dans le tableau de bord !");
            System.out.println("[1] Liste d'aéroports");
            System.out.println("[2] Liste d'avions");
            System.out.println("[3] Liste de personnes");
            System.out.println("[0] Quitter");
            System.out.print("Entrer le chiffre pour l'action correspondant: ");

            // ui: user input
            int ui = scanner.nextInt();
            switch(ui) {
                case 1 -> printListAeroport();
                case 2 -> printListAvion();
                case 0 -> running = false;
            }
        }
        scanner.close();
        clearTerminal();
        printArtASCII();
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J\033[3J");
        System.out.flush();
    }

    public static void printArtASCII() {
        Path path = Paths.get("art.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void printListAeroport() {
        boolean running = true;
        while (running) {
            clearTerminal();
            for (int i = 0; i < aeroports.size(); i++) {
                System.out.printf("[%d] %s\n", i+1, aeroports.get(i));
            }
            System.out.println("[0] Revenir");
            System.out.print(">>> ");

            int ui = scanner.nextInt();
            switch(ui) {
                case 0 -> running = false;
            }
        }
    }

    public static void printListAvion() {
        boolean running = true;
        while (running) {
            clearTerminal();
            for (int i = 0; i < avions.size(); i++) {
                System.out.printf("[%d] %s\n", i+1, avions.get(i));
            }
            System.out.println("[0] Revenir");
            System.out.print(">>> ");

            int ui = scanner.nextInt();
            switch(ui) {
                case 0 -> running = false;
            }
        }
    }
}
