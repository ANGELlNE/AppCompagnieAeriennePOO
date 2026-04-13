package acap;

import java.util.ArrayList;
import java.util.Map;

import acap.model.*;

public class Util {
    private static final double EARTH_RADIUS_KM = 6371.0;

    public static void init(ArrayList<Aeroport> aeroports, ArrayList<Avion> avions) {
        aeroports.add(new Aeroport("ATL", "Atlanta", 33.6407, -84.4277));
        aeroports.add(new Aeroport("HND", "Tokyo", 35.5533, 139.7811));
        aeroports.add(new Aeroport("PVG", "Shanghai", 31.1434, 121.8053));
        aeroports.add(new Aeroport("IST", "Istanbul", 41.2611, 28.7419));
        aeroports.add(new Aeroport("DEL", "Delhi", 28.5686, 77.1124));
        aeroports.add(new Aeroport("ICN", "Incheon", 37.4602, 126.4407));
        aeroports.add(new Aeroport("LAX", "Los Angeles", 33.9416, -118.4085));
        aeroports.add(new Aeroport("CDG", "Paris", 49.0097, 2.5479));

        avions.add(new Avion("F-GKXA", "Airbus A320", 6, 180, 828));
        avions.add(new Avion("N37273", "Boeing 737-800", 6, 189, 842));
        avions.add(new Avion("JA873A", "Boeing 787 Dreamliner", 8, 242, 913));
        avions.add(new Avion("A6-EEQ", "Airbus A380", 21, 517, 945));
        avions.add(new Avion("B-16721", "Boeing 777-300ER", 12, 396, 905));
        avions.add(new Avion("9M-MTK", "Airbus A330-300", 10, 277, 871));
        avions.add(new Avion("PR-AXH", "Embraer E190", 4, 114, 829));
        avions.add(new Avion("C-FJZL", "Bombardier CRJ900", 4, 90, 829));
        avions.add(new Avion("F-HTYH", "Airbus A350-900", 10, 325, 905));
        avions.add(new Avion("D-ABYT", "Boeing 747-8", 13, 467, 918));
        avions.add(new Avion("ET302", "Boeing 737 MAX 8", 6, 162, 842));
    }

    public static double distanceBetween(Aeroport a1, Aeroport a2) {
        double lat1 = Math.toRadians(a1.getLatitude());
        double lon1 = Math.toRadians(a1.getLongitude());
        double lat2 = Math.toRadians(a2.getLatitude());
        double lon2 = Math.toRadians(a2.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    public static long tempsVolBloc(Aeroport depart, Aeroport arrivee, Avion avion) {
        double distanceKm = distanceBetween(depart, arrivee);
        double vitesse = avion.getVitesseCroisiere();
        if (vitesse <= 0) {
            throw new IllegalArgumentException("Aircraft cruise speed must be positive");
        }
        long deltaT = Math.round((distanceKm / vitesse) * 60 + 30);
        return deltaT;
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
