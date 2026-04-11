package acap;

import java.util.ArrayList;

import acap.model.*;

public class Util {
    public static void init(ArrayList<Aeroport> aeroports, ArrayList<Avion> avions) {
        // Aeroport Eglin = new Aeroport("Eglin Air Force Base", "Floride", "La plus grande base aérienne américaine.");
        Aeroport ATL = new Aeroport("ATL", "Atlanta");
        Aeroport HDN = new Aeroport("HDN", "Tokyo");
        Aeroport PVG = new Aeroport("PVG", "Shanghai");
        Aeroport IST = new Aeroport("IST", "Istanbul");
        Aeroport DEL = new Aeroport("DEL", "Delhi");
        Aeroport ICN = new Aeroport("ICN", "Incheon");
        Aeroport LAX = new Aeroport("LAX", "Los Angeles");
        Aeroport CDG = new Aeroport("CDG", "Paris");
        // aeroports.add(Eglin);
        aeroports.add(ATL);
        aeroports.add(HDN);
        aeroports.add(PVG);
        aeroports.add(IST);
        aeroports.add(DEL);
        aeroports.add(ICN);
        aeroports.add(LAX);
        aeroports.add(CDG);

        Avion F35 = new Avion("22-5816", "Lockheed Martin F-35 Lightning II", 1);
        Avion SR71 = new Avion("61-7951", "Lockheed SR-71 Blackbird", 2);
        Avion B737 = new Avion("ET302", "Boeing 737 MAX 8", 162);
        avions.add(F35);
        avions.add(SR71);
        avions.add(B737);
    }

    // public static void printListAeroport(ArrayList<Aeroport> aeroports) {
    //     boolean running = true;
    //     while (running) {
    //         clearTerminal();
    //         for (int i = 0; i < aeroports.size(); i++) {
    //             System.out.printf("[%d] %s\n", i+1, aeroports.get(i));
    //         }
    //         System.out.println("[0] Revenir");
    //         System.out.print(">>> ");

    //         int ui = scanner.nextInt();
    //         switch(ui) {
    //             case 0 -> running = false;
    //         }
    //     }
    // }

    // public static void printListAvion(ArrayList<Avion> avions) {
    //     boolean running = true;
    //     while (running) {
    //         clearTerminal();
    //         for (int i = 0; i < avions.size(); i++) {
    //             System.out.printf("[%d] %s\n", i+1, avions.get(i));
    //         }
    //         System.out.println("[0] Revenir");
    //         System.out.print(">>> ");

    //         int ui = scanner.nextInt();
    //         switch(ui) {
    //             case 0 -> running = false;
    //         }
    //     }
    // }
}
