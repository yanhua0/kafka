package org.file.test.tes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Rezept[] rezepte = new Rezept[3];


    public static String rezeptAnzeigen(int index) {

        if (index > rezepte.length || index <= 0 || rezepte[index - 1] == null) {
            return "No Rezept found.";
        }
        return rezepte[index - 1].toString();

    }

    public static Rezept rezeptSuchen(String titel) {

        if (titel == null || titel.length() == 0) {
            return null;
        }

        for (Rezept rezept : rezepte) {
            if (rezept != null && rezept.getName().contains(titel)) {
                return rezept;
            }
        }

        return null;
    }

    public static String alleRezepteAuflisten() {

        StringBuilder stringBuilder = new StringBuilder("Rezepte on the list:\n");

        for (int i = 0; i < rezepte.length; i++) {
            if (rezepte[i] != null) {
                stringBuilder.append(String.format("%d. %s\n", i + 1, rezepte[i].getName()));
            }
        }

        return stringBuilder.toString();
    }

    public static boolean rezeptUmrechnen(int index, double faktor) {

        if (index > rezepte.length || index <= 0 || rezepte[index - 1] == null) {
            return false;
        }

        rezepte[index - 1].mengenMultiplizieren(faktor);
        return true;

    }

    public static void beispielRezepteLaden() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, List<Map<String, Object>>> map = objectMapper.readValue(new File("G:\\JavaWeb\\studyJava\\kafka\\file\\src\\main\\java\\org\\file\\test\\tes\\rezepte.json"), Map.class);
         //   JsonProcessingException jsonProcessingException=
            map.forEach((key, val) -> {
                for (int i = 0; i < rezepte.length; i++) {
                    for (Map<String, Object> stringObjectMap : val) {
                        String name = (String) stringObjectMap.get("name");
                        Double portions = Double.valueOf(String.valueOf(stringObjectMap.get("portions")));
                        String instructions = (String) stringObjectMap.get("instructions");
                        String category = (String) stringObjectMap.get("Category");
                        List<Map<String, String>> inMap = (List<Map<String, String>>) stringObjectMap.get("ingredients");

                        Zutat[] ingredients = new Zutat[inMap.size()];
                        for (int j = 0; j < ingredients.length; j++) {
                            ingredients[j] = new Zutat(inMap.get(j).get("name"), Double.valueOf(inMap.get(j).get("quantity")), inMap.get(j).get("unit"));
                        }
                        rezepte[i] = new Rezept(name, ingredients, instructions, Category.get(category), portions);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Diese Methode dient zum Ausprobieren - wichtig bei der Abgabe ist die Funktionalität der Methoden und Klassen
    public static void main(String[] args) {

        beispielRezepteLaden();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your Rezeptsammlung, what do you want to do?");

        scanner.useDelimiter("\n");

        int aktion;

        while (true) {

            System.out.println("[1] Rezeptname shown\n" +
                    "[2] one Rezept shown\n" +
                    "[3] one Rezept convert\n" +
                    "[4] Programm end");

            try {
                aktion = scanner.nextInt();

                int index;
                double faktor;

                switch (aktion) {
                    case 1:
                        System.out.println(alleRezepteAuflisten());
                        break;

                    case 2:
                        System.out.println("Bitte geben Sie die Nummer des Rezepts ein, das Sie anzeigen möchten:");
                        index = scanner.nextInt();
                        System.out.println(rezeptAnzeigen(index));
                        break;

                    case 3:
                        System.out.println("Bitte geben Sie die Nummer des Rezepts ein, dessen Mengen Sie neu berechnen möchten:");
                        index = scanner.nextInt();
                        System.out.println("Geben Sie nun einen Umrechnungsfaktor ein:");
                        faktor = scanner.nextDouble();
                        if (rezeptUmrechnen(index, faktor)) {
                            System.out.println(String.format("Die Mengen für das Rezept %s wurden mit dem Faktor %.1f multipliziert.", index, faktor));
                            System.out.println(rezeptAnzeigen(index));
                        } else {
                            System.out.println(String.format("Die Mengen für das Rezept %s konnten nicht angepasst werden", index));
                        }
                        break;

                    case 4:
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid input.");

                }
            } catch (InputMismatchException e) {
                // for now: use the input
                scanner.next();
                System.err.println("Invalid input.");
            }

        }
    }
}
