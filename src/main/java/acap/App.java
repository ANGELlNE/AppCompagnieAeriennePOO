package acap;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.Locale;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import acap.enumeration.*;
import acap.model.*;

public class App {
    private static ArrayList<Aeroport> aeroports = new ArrayList<Aeroport>();
    private static ArrayList<Avion> avions = new ArrayList<Avion>();
    private static ArrayList<Vol> vols = new ArrayList<Vol>();

    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    private static LocalDateTime currentTime = LocalDateTime.now();
    private static boolean triggerClear = false;

    public static void main(String[] args) {
        Util.init(aeroports, avions);

        // hide cursor
        System.out.print("\033[?25l");
        clearTerminal();
        boolean running = true;
        while (running) {
            if (triggerClear) {
                clearTerminal();
                triggerClear = false;
            } else {
                System.out.print("\033[H");
            }

            System.out.println("╔" + "═".repeat(78) + "╗");
            System.out.print("║Bienvenue dans le tableau de bord !");
            currentTime = currentTime.plusMinutes(1);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy HH:mm", Locale.FRENCH);
            System.out.printf("%43s║\n", currentTime.format(dateFormat));
            System.out.println("╠" + "═".repeat(78) + "╣");
            switch(random.nextInt(50)) {
                case 0 -> nouveauVol();
            }
            // System.out.println("[1] Liste d'aéroports");
            // System.out.println("[2] Liste d'avions");
            // System.out.println("[3] Liste de personnes");
            // System.out.println("[4] Liste de vols");
            // System.out.println("[0] Quitter");
            // System.out.print("Entrer le chiffre pour l'action correspondant: ");

            // // ui: user input
            // int ui = scanner.nextInt();
            // switch(ui) {
            //     case 1 -> printListAeroport();
            //     case 2 -> printListAvion();
            //     case 0 -> running = false;
            // }
            verifierEtatVols();
            afficherVols();
            System.out.println("╚" + "═".repeat(78) + "╝");

            System.out.println("\nAppuyer sur <Ctrl+C> pour quitter.");

            try {
                Thread.sleep(500);
            } catch (InterruptedException exc) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\nLa simulation est terminé. Appuyer sur <Entrer> pour quitter.");
        System.out.print(">>> ");
        scanner.close();
        clearTerminal();
        printArtASCII();
    }

    public static void afficherVols() {
        if (vols.isEmpty()) {
            System.out.printf("║%34sPas de vol%34s║\n", "", "");
        }
        for (int i = 0; i < vols.size(); i++) {
            System.out.printf("║%s║\n", vols.get(i).obtenirGraph(currentTime));
            if (i != vols.size() - 1) {
                System.out.println("╟" + "─".repeat(78) + "╢");
            }
        }
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J\033[3J");
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

    public static void nouveauVol() {
        int sizeAeroports = aeroports.size();
        Aeroport origine = aeroports.get(random.nextInt(sizeAeroports));

        ArrayList<Aeroport> copyAeroports = new ArrayList<Aeroport>(aeroports);
        copyAeroports.remove(origine);
        Aeroport destination = copyAeroports.get(random.nextInt(sizeAeroports-1));

        String numeroVol = Integer.toString(random.nextInt(9999));
        LocalDateTime dateHeureDepart = currentTime.plusHours(1).plusMinutes(random.nextInt(60));
        LocalDateTime dateHeureArrivee = dateHeureDepart.plusMinutes(random.nextInt(700) + 20);

        vols.add(new Vol(numeroVol, origine, destination, dateHeureDepart, dateHeureArrivee, EtatVol.PLANIFIE));
    }

    public static void verifierEtatVols() {
        ArrayList<Vol> ancienVols = new ArrayList<Vol>();
        for (Vol vol : vols) {
            if (vol.getEtat() == EtatVol.PLANIFIE && random.nextInt(1000) == 0) {
                vol.setEtat(EtatVol.ANNULE);
            } else if (vol.getEtat() == EtatVol.PLANIFIE && vol.getDateHeureDepart().isBefore(currentTime)) {
                vol.setEtat(EtatVol.EN_COURS);
            } else if (vol.getEtat() == EtatVol.EN_COURS && vol.getDateHeureArrivee().isBefore(currentTime)) {
                vol.setEtat(EtatVol.TERMINE);
            } else if (vol.getEtat() == EtatVol.EN_COURS && random.nextInt(10_000) == 0) {
                vol.setEtat(EtatVol.CRASHED);
            } else if (vol.getEtat() == EtatVol.ANNULE && vol.getDateHeureDepart().isBefore(currentTime.minusHours(1))) {
                ancienVols.add(vol);
            } else if (vol.getEtat() == EtatVol.TERMINE && vol.getDateHeureArrivee().isBefore(currentTime.minusHours(1))) {
                ancienVols.add(vol);
            }
        }
        if (!ancienVols.isEmpty()) {
            vols.removeAll(ancienVols);
            triggerClear = true;
        }
    }
}
