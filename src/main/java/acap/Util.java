package acap;

import java.util.ArrayList;
import java.util.Map;

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

        // Avion F35 = new Avion("22-5816", "Lockheed Martin F-35 Lightning II", 1, 0);
        // Avion SR71 = new Avion("61-7951", "Lockheed SR-71 Blackbird", 2, 0);
        // // Popular commercial aircraft
        Avion A320 = new Avion("F-GKXA", "Airbus A320", 6, 180);
        Avion B737_800 = new Avion("N37273", "Boeing 737-800", 6, 189);
        Avion B787 = new Avion("JA873A", "Boeing 787 Dreamliner", 8, 242);
        Avion A380 = new Avion("A6-EEQ", "Airbus A380", 21, 517);
        Avion B777 = new Avion("B-16721", "Boeing 777-300ER", 12, 396);
        Avion A330 = new Avion("9M-MTK", "Airbus A330-300", 10, 277);
        Avion E190 = new Avion("PR-AXH", "Embraer E190", 4, 114);
        Avion CRJ900 = new Avion("C-FJZL", "Bombardier CRJ900", 4, 90);
        Avion A350 = new Avion("F-HTYH", "Airbus A350-900", 10, 325);
        Avion B747 = new Avion("D-ABYT", "Boeing 747-8", 13, 467);
        Avion B737 = new Avion("ET302", "Boeing 737 MAX 8", 6, 162);
        // avions.add(F35);
        // avions.add(SR71);
        avions.add(A320);
        avions.add(B737_800);
        avions.add(B787);
        avions.add(A380);
        avions.add(B777);
        avions.add(A330);
        avions.add(E190);
        avions.add(CRJ900);
        avions.add(A350);
        avions.add(B747);
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

    // Map IATA code → Airline name
    public static final Map<String, String> Airlines = Map.ofEntries(
        Map.entry("AA", "American Airlines"),
        Map.entry("DL", "Delta Air Lines"),
        Map.entry("UA", "United Airlines"),
        Map.entry("WN", "Southwest Airlines"),
        Map.entry("B6", "JetBlue Airways"),
        Map.entry("AS", "Alaska Airlines"),
        Map.entry("AC", "Air Canada"),
        Map.entry("WS", "WestJet"),

        Map.entry("AF", "Air France"),
        Map.entry("BA", "British Airways"),
        Map.entry("LH", "Lufthansa"),
        Map.entry("KL", "KLM Royal Dutch Airlines"),
        Map.entry("IB", "Iberia"),
        Map.entry("AZ", "ITA Airways"),
        Map.entry("SK", "SAS Scandinavian Airlines"),
        Map.entry("LX", "Swiss International Air Lines"),
        Map.entry("OS", "Austrian Airlines"),
        Map.entry("SN", "Brussels Airlines"),
        Map.entry("TP", "TAP Air Portugal"),
        Map.entry("AY", "Finnair"),
        Map.entry("LO", "LOT Polish Airlines"),
        Map.entry("EI", "Aer Lingus"),
        Map.entry("TK", "Turkish Airlines"),
        Map.entry("A3", "Aegean Airlines"),
        Map.entry("FR", "Ryanair"),
        Map.entry("U2", "easyJet"),
        Map.entry("VY", "Vueling"),
        Map.entry("W6", "Wizz Air"),

        Map.entry("EK", "Emirates"),
        Map.entry("QR", "Qatar Airways"),
        Map.entry("EY", "Etihad Airways"),
        Map.entry("SQ", "Singapore Airlines"),
        Map.entry("CX", "Cathay Pacific"),
        Map.entry("JL", "Japan Airlines"),
        Map.entry("NH", "All Nippon Airways"),
        Map.entry("KE", "Korean Air"),
        Map.entry("OZ", "Asiana Airlines"),
        Map.entry("CZ", "China Southern Airlines"),
        Map.entry("MU", "China Eastern Airlines"),
        Map.entry("CA", "Air China"),
        Map.entry("HU", "Hainan Airlines"),
        Map.entry("AI", "Air India"),
        Map.entry("MH", "Malaysia Airlines"),
        Map.entry("TG", "Thai Airways"),
        Map.entry("GA", "Garuda Indonesia"),
        Map.entry("VN", "Vietnam Airlines"),
        Map.entry("PR", "Philippine Airlines"),

        Map.entry("QF", "Qantas"),
        Map.entry("VA", "Virgin Australia"),
        Map.entry("NZ", "Air New Zealand"),

        Map.entry("LA", "LATAM Airlines"),
        Map.entry("AV", "Avianca"),
        Map.entry("CM", "Copa Airlines"),
        Map.entry("AM", "Aeroméxico"),
        Map.entry("G3", "Gol Linhas Aéreas"),
        Map.entry("AR", "Aerolíneas Argentinas"),

        Map.entry("ET", "Ethiopian Airlines"),
        Map.entry("MS", "Egyptair"),
        Map.entry("KQ", "Kenya Airways"),
        Map.entry("SA", "South African Airways"),
        Map.entry("AT", "Royal Air Maroc")
    );
}
