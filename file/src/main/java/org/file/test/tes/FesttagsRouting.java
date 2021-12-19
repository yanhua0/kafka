package org.file.test.tes;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class FesttagsRouting {

    public static int x=10;
    public static int y=10;
    public static Karte reisekarte[][] = new Karte[x][y];

    public class Karte {
        String rahmen;
        boolean befana;
        boolean haus;//房子
        boolean hindernis;//障碍

        public Karte(String rahmen, boolean befana, boolean haus, boolean hindernis) {
            this.rahmen = rahmen;
            this.befana = befana;
            this.haus = haus;
            this.hindernis = hindernis;
        }
    }


    public static void erzeugerahmen() {
        reisekarte=new Karte[x][y];
        int cols = reisekarte.length;
        int rows = reisekarte[0].length;
        FesttagsRouting festtagsRouting = new FesttagsRouting();
        FesttagsRouting.Karte karte = festtagsRouting.new Karte("+", false, false, false);

        FesttagsRouting.Karte col = festtagsRouting.new Karte("-", false, false, false);
        FesttagsRouting.Karte row = festtagsRouting.new Karte("|", false, false, false);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (i == 0 || i == cols - 1) {
                    reisekarte[i][j] = festtagsRouting.new Karte("-", false, false, false);
                } else if (j == 0 || j == rows - 1) {
                    reisekarte[i][j] = festtagsRouting.new Karte("|", false, false, false);
                } else {
                    reisekarte[i][j] = festtagsRouting.new Karte(" ", false, false, false);
                }
            }

        }
        reisekarte[0][0] = karte;
        reisekarte[0][rows - 1] = karte;
        reisekarte[cols - 1][0] = karte;
        reisekarte[cols - 1][rows - 1] = karte;

    }

    public static void erzeugeObject(int anzahlHeuser, int anzahlHindernisse) {
        if (anzahlHeuser > reisekarte.length) {
            throw new RuntimeException("anzahlHeuser too large");
        }
        if (anzahlHindernisse > reisekarte[0].length - 2) {
            throw new RuntimeException("anzahlHindernisse too large");
        }
        FesttagsRouting festtagsRouting=new FesttagsRouting();
        reisekarte[1][1]=festtagsRouting.new Karte("B", true, false, false);
        Random random=new Random();
        for (int i =0; i <anzahlHeuser ; i++) {
              while (true){
                  int col=random.nextInt(x-2)+1;
                  int row=random.nextInt(x-2)+1;
                  Karte karte=reisekarte[col][row];
                  if(!karte.befana&&!karte.haus&&!karte.hindernis){
                      reisekarte[col][row]=festtagsRouting.new Karte("H", false, true, false);
                      break;
                  }
              }
        }
        for (int j = 0; j <anzahlHindernisse; j++) {
            while (true){
                int col=random.nextInt(x-2)+1;
                int row=random.nextInt(x-2)+1;
                Karte karte=reisekarte[col][row];
                if(!karte.befana&&!karte.haus&&!karte.hindernis){
                    reisekarte[col][row]=festtagsRouting.new Karte("O", false, false, true);
                    break;
                }
            }
        }


    }

    public static void main(String[] args) {


        Scanner scanner=new Scanner(System.in);
        while (true) {

            System.out.println("[1] show\n" +
                    "[2] Programm end");

            try {
               int aktion = scanner.nextInt();
                switch (aktion) {
                    case 1:
                        System.out.println("input anzahlHeuser");
                        int an=scanner.nextInt();
                        System.out.println("input anzahlHindernisse");
                        int anza=scanner.nextInt();
                        System.out.println("input create num");
                        int num=scanner.nextInt();
                        for (int k = 0; k < num;k++) {
                            erzeugerahmen();
                            erzeugeObject(an,anza);
                            for (int i = 0; i < x; i++) {
                                for (int j = 0; j < y; j++) {
                                    System.out.print(reisekarte[i][j].rahmen);
                                }
                                System.out.println();
                            }
                        }
                        break;

                    case 2:
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
