package Vehiclerental;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import Authentication.*;


import java.util.Scanner;

public class Main {

    private static Main instance = null;

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

    public static void main(String[] args){
        startMainFlow(args);
    }

    public static void startMainFlow(String[] args) {

        String RESET = "\u001B[0m";
        String RED_TEXT = "\u001B[31m";
        String BLUE_TEXT = "\u001B[34m";
        String GREEN_TEXT = "\u001B[32m";
        String YELLOW_TEXT = "\u001B[33m";
        String BLACK_BG = "\u001B[40m";
        String WHITE_BG = "\u001B[47m";

        Scanner s = new Scanner(System.in);
        System.out.println(" ");
        System.out.println(YELLOW_TEXT+"             "+"Welcome to my Java Vehicle Rental System "+RESET);
        boolean status = true;
        while (status) {
            System.out.println(RED_TEXT+"1. Admin"+RESET);
            System.out.println(RED_TEXT+"2. Renter\n"+RESET);
            System.out.print(BLUE_TEXT+"Enter your choice (1/2) : "+RESET);
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    Admin.getInstance().startFlow();
                    status = false;
                    break;
                case 2:
                    Renter.getInstance().startFlow();
                    status = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

}

}
