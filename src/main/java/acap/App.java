package acap;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.time.Duration;
import java.time.LocalDate;
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

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scanner.close();
            clearTerminal();
            printArtASCII();
            System.out.println();
            System.out.print("\033[?25h");
            System.out.flush();
        }));

        // hide cursor
        System.out.print("\033[?25l");
        clearTerminal();
        boolean running = true;
        nouveauVol();
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
            if (random.nextInt(50) == 0) {
                nouveauVol();
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
            afficherInfosDernierVol();
            System.out.println("╚" + "═".repeat(78) + "╝");
            System.out.printf("%80s\n", "");
            System.out.println("Appuyer sur <Ctrl+C> pour quitter.");

            try {
                Thread.sleep(500);
            } catch (InterruptedException exc) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void afficherVols() {
        System.out.println("╠" + "═".repeat(78) + "╣");
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

    public static void afficherInfosDernierVol() {
        System.out.println("╠" + "═".repeat(78) + "╣");
        if (vols.isEmpty()) {
            return;
        }
        Vol vol = vols.get(vols.size() - 1);
        String numeroVol = vol.getNumeroVol();
        String airline = Util.Airlines.get(numeroVol.substring(0, 2));
        String origIATA = vol.getOrigine().getNom();
        String origVille = vol.getOrigine().getVille();
        String destIATA = vol.getDestination().getNom();
        String destVille = vol.getDestination().getVille();
        long delta = Duration.between(vol.getDateHeureDepart(), vol.getDateHeureArrivee()).getSeconds();
        String tempsRestant = String.format("%02dh %02dm", delta / 3600, (delta % 3600) / 60);

        Avion avion = vol.getAvion();
        int personnelAmt = vol.getPilotes().size() + vol.getEquipeCabine().size();
        int passagersAmt = vol.getPassagers().size();

        int l1spc = 69 - airline.length();
        l1spc = Math.max(0, l1spc);
        System.out.printf("║Vol %-5s%s%s║\n", numeroVol, " ".repeat(l1spc), airline);

        String l2string = String.format("%s (%s) -> %s (%s) en %s", origIATA, origVille, destIATA, destVille, tempsRestant);
        int l2spc = (78 - l2string.length()) / 2;
        l2spc = Math.max(0, l2spc);
        if (l2spc % 2 == 0) {
            System.out.printf("║%s%s%s║\n", " ".repeat(l2spc), l2string, " ".repeat(l2spc));
        } else {
            System.out.printf("║%s%s%s ║\n", " ".repeat(l2spc), l2string, " ".repeat(l2spc));
        }

        System.out.printf("╟%s╢\n", "-".repeat(78));

        int l3spc = 52 - avion.toString().length() - Integer.toString(personnelAmt).length() - Integer.toString(passagersAmt).length();
        l3spc = Math.max(0, l3spc);
        String personnel;
        if (personnelAmt < 2) {
            personnel = Integer.toString(personnelAmt) + " personnel";
        } else {
            l3spc -= 1;
            personnel = Integer.toString(personnelAmt) + " personnels";
        }
        String passager;
        if (passagersAmt < 2) {
            passager = Integer.toString(passagersAmt) + " passager";
        } else {
            l3spc -= 1;
            passager = Integer.toString(passagersAmt) + " passagers";
        }
        System.out.printf("║Avion %s%s%s %s║\n", avion, " ".repeat(l3spc), personnel, passager);
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

        Avion avion = avions.get(random.nextInt(avions.size()));

        List<String> codes = new ArrayList<>(Util.Airlines.keySet());
        String airlineCode = codes.get(random.nextInt(codes.size()));

        int numeroLen = random.nextInt(3);
        String numeroVol = airlineCode.toString() + Long.toString(random.nextLong(Math.round(999 / (Math.pow(10, numeroLen)))));

        LocalDateTime dateHeureDepart = currentTime.plusHours(1).plusMinutes(random.nextInt(60));
        LocalDateTime dateHeureArrivee = dateHeureDepart.plusMinutes(random.nextInt(720) + 60);

        Vol vol = new Vol(numeroVol, origine, destination, dateHeureDepart, dateHeureArrivee, EtatVol.PLANIFIE);
        vol.setAvion(avion);

        ArrayList<Pilote> pilotes = new ArrayList<Pilote>();
        pilotes.add(new Pilote("", "", "", 0, LocalDate.now(), "", "", 0));
        pilotes.add(new Pilote("", "", "", 0, LocalDate.now(), "", "", 0));

        ArrayList<PersonnelCabine> personnels = new ArrayList<PersonnelCabine>();
        int personnelsMax = avion.getcapacitePersonnel();
        if (personnelsMax == 4) {
            personnels.add(new PersonnelCabine("", "", "", 0, LocalDate.now(), ""));
            personnels.add(new PersonnelCabine("", "", "", 0, LocalDate.now(), ""));
        } else {
            for (int i = 0; i < random.nextInt(2, personnelsMax-2); ++i) {
                personnels.add(new PersonnelCabine("", "", "", 0, LocalDate.now(), ""));
            }
        }

        ArrayList<Passager> passagers = new ArrayList<Passager>();
        int passagersMax = avion.getcapacitePassager();
        for (int i = 0; i < random.nextInt(2*passagersMax / 3, passagersMax); ++i) {
            passagers.add(new Passager("", "", "", ""));
        }

        vol.setPilotes(pilotes);
        vol.setEquipeCabine(personnels);
        vol.setPassagers(passagers);
        vols.add(vol);
    }

    public static void verifierEtatVols() {
        ArrayList<Vol> ancienVols = new ArrayList<Vol>();
        for (Vol vol : vols) {
            if (vol.getEtat() == EtatVol.PLANIFIE && random.nextInt(1000) == 0) {
                vol.setEtat(EtatVol.ANNULE);
            } else if (vol.getEtat() == EtatVol.PLANIFIE && vol.getDateHeureDepart().isBefore(currentTime)) {
                if (random.nextInt(100) == 0) {
                    vol.setEtat(EtatVol.RETARDE);
                    vol.setDateHeureDepart(vol.getDateHeureDepart().plusMinutes(1));
                    vol.setDateHeureArrivee(vol.getDateHeureArrivee().plusMinutes(1));
                } else {
                    vol.setEtat(EtatVol.EN_COURS);
                }
            } else if (vol.getEtat() == EtatVol.RETARDE && vol.getDateHeureDepart().isBefore(currentTime)) {
                if (random.nextInt(20) == 0) {
                    vol.setEtat(EtatVol.EN_COURS);
                } else {
                    vol.setDateHeureDepart(vol.getDateHeureDepart().plusMinutes(1));
                    vol.setDateHeureArrivee(vol.getDateHeureArrivee().plusMinutes(1));
                }
            } else if (vol.getEtat() == EtatVol.EN_COURS && vol.getDateHeureArrivee().isBefore(currentTime)) {
                vol.setEtat(EtatVol.TERMINE);
            } else if (vol.getEtat() == EtatVol.EN_COURS && random.nextInt(10_000) == 0) {
                vol.setEtat(EtatVol.CRASHED);
            } else if (vol.getEtat() == EtatVol.ANNULE && vol.getDateHeureDepart().isBefore(currentTime.minusHours(1))) {
                ancienVols.add(vol);
            } else if (vol.getEtat() == EtatVol.TERMINE && vol.getDateHeureArrivee().isBefore(currentTime.minusHours(1))) {
                ancienVols.add(vol);
            } else if (vol.getEtat() == EtatVol.CRASHED && vol.getDateHeureArrivee().isBefore(currentTime.minusHours(1))) {
                ancienVols.add(vol);
            }
        }
        if (!ancienVols.isEmpty()) {
            vols.removeAll(ancienVols);
            triggerClear = true;
        }
    }
}
