package Vehicledata;

import Vehiclerental.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Admindisplay {

    static String RESET = "\u001B[0m";
    static String RED_TEXT = "\u001B[31m";
    static String GREEN_TEXT = "\u001B[32m";

    static String BLUE_TEXT = "\u001B[34m";
    static Scanner s = new Scanner(System.in);

    static ArrayList<HashMap<String, VehicleInfo>> x;

    private static Admindisplay instance = null;

    public static Admindisplay getInstance() {
        if (instance == null) {
            instance = new Admindisplay();
        }
        return instance;
    }

    public static void displayData(ArrayList<HashMap<String, VehicleInfo>> data) {
        x = data; // Use the passed Addingdata instance

        // Print the ArrayList
        int k = 1;
        System.out.println(GREEN_TEXT + "----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("S.no" + "    Name  " + "         Number_Plate " + "  Availability" + "    Type" + "           isDue      " + "  Security-Deposit" +"          BookingStatus");
        for (HashMap<String, VehicleInfo> map : x) {
            for (String name : map.keySet()) {
                System.out.print(" " + k + "     " + name + map.get(name));
                k++;
            }
            System.out.println(" ");
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
        System.out.println(" ");
        System.out.println("1.Search by name");
        System.out.println("2.Search by Number plate");
        System.out.println("3.Make changes in the Table");
        System.out.println("4.Go to Main menu");
        System.out.println(" ");
        int choice = s.nextInt();
        switch (choice) {
            case 1:
                searchByName();
                break;
            case 2:
                searchByNumberPlate();
                break;
            case 3:
                modifyTable();
                break;
            case 4:
                Main.getInstance().startMainFlow(null);
                break;
        }
    }

    private static void searchByName() {
        System.out.println("Enter name of the vehicle");
        String name = s.next();
        System.out.println(" ");
        boolean found = false;
        System.out.println("Details of the selected Vehicle \n");
        System.out.println(BLUE_TEXT + "-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("S.no" + "    Name  " + "         Number_Plate " + "  Availability" + "    Type" + "           isDue      " + "  Security-Deposit"+"                   BookingStatus");
        int l = 1;
        for (HashMap<String, VehicleInfo> map : x) {
            for (String name1 : map.keySet()) {
                if (name1.trim().equals(name)) {
                    System.out.print(" " + l + ".      " + name + map.get(name1));
                    System.out.println(" ");
                    l++;
                    found = true;
                }
            }
        }
        if(found == false){
            System.out.println(" ");
            System.out.println("                                        No vehicle found with the given name.");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
            searchByName();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
        System.out.println(" ");
        displayData(x);
    }

    private static void searchByNumberPlate() {
        System.out.println("Enter numberplate of the vehicle");
        String numberplate = s.next();
        System.out.println(" ");
        boolean found = false;
        System.out.println(RED_TEXT + "------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("S.no" + "    Name  " + "         Number_Plate " + "  Availability" + "    Type" + "           isDue      " + "  Security-Deposit"+"                  BookingStatus");
        for (HashMap<String, VehicleInfo> map : x) {
            for (String name1 : map.keySet()) {
                if (map.get(name1).getNumberPlate().equals(numberplate)) {
                    System.out.print(" " + "1." + "     " + name1 + map.get(name1));
                    System.out.println(" ");
                    found = true;
                }
            }
        }
        if(found == false){
            System.out.println(" ");
            System.out.println("                                        No vehicle found with the given number plate.");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
            searchByNumberPlate();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
        System.out.println(" ");
        displayData(x);
    }

    private static void modifyTable() {
        System.out.println("1.Add Vehicle");
        System.out.println("2.Remove Vehicle");
        System.out.println("3.Change details in existing Vehicle");
        System.out.println("4.Go back");
        System.out.println(" ");
        System.out.print("Enter Your Choice : ");
        int choice1 = s.nextInt();
        switch (choice1) {
            case 1:
                addVehicle();
                break;
            case 2:
                // Implement remove vehicle functionality
                deletevehicle();
                break;
            case 3:
                // Implement modify vehicle functionality
                modifyVehicle();
                break;
            case 4:
                displayData(x);
                break;
            default:
                System.out.println("Invalid Choice");
                modifyTable();
        }
    }

    private static void addVehicle() {
        System.out.println("Enter NAME NUMBERPLATE TYPE AVAILABILITY DUESTATUS SECURITYDEPOSIT as the same format....");

        System.out.print("Enter name: ");
        String name1 = s.next();

        System.out.print("Enter number plate: ");
        String numplate = s.next();

        System.out.print("Enter type: ");
        String type = s.next();

        System.out.print("Enter availability: ");
        int availability = s.nextInt();

        System.out.print("Enter due status (true/false): ");
        boolean duestat = s.nextBoolean();

        System.out.print("Enter money: ");
        int money = s.nextInt();

        HashMap<String, VehicleInfo> map6 = new HashMap<>();
        map6.put(name1, new VehicleInfo(numplate, type, availability, duestat, "$" + money,"Not Booked"));

        // Add the HashMap to the ArrayList
//        x.add(map6);
        Addingdata.getInstance().addDataToList(map6);
        x = Addingdata.getInstance().getData();
        System.out.println(" ");
        System.out.println("Successfully Added");

        // Re-display the table
        displayData(x);
    }

    private static void deletevehicle(){

        System.out.println("Enter the numberplate of vehicle to Delet : ");
        String numberplate = s.next();
        boolean isdeleted = false;
         for(int i=0;i<x.size();i++) {

            HashMap<String,VehicleInfo> map = x.get(i);
            for (String name1 : map.keySet()) {
                if (map.get(name1).getNumberPlate().equals(numberplate)) {
                    map.remove(name1);
                    isdeleted = true;
                    if (map.isEmpty()) {
                        Addingdata.getInstance().removeDataFromList(i);
                        x = Addingdata.getInstance().getData();
                    }
                    System.out.println("Vehicle with number plate " + numberplate + " has been deleted.");
                    break;
                }
                if(isdeleted){
                    break;
                }
            }
        }
        if(!isdeleted){
            System.out.println("No vehicle found with the given number plate.");
        }


        displayData(x);
    }

    private static void modifyVehicle(){
        System.out.println("Give the Number plate of the vehicle you like to modify :");
        String numplate = s.next();
        boolean found = false;
        for (int i = 0; i < x.size(); i++) {
            HashMap<String, VehicleInfo> map = x.get(i);
            for (String name2 : map.keySet()) {
                if (map.get(name2).getNumberPlate().equals(numplate)) {
                    found = true;
                   break;
                }
            }

        }
        if(found){
        System.out.println(" ");
            System.out.println("What would you like to change ? ");
            System.out.println("1.Duestatus 2.Security_Deposit 3.To go back");
            int wish = s.nextInt();
            if (wish == 1) {
                for (int i = 0; i < x.size(); i++) {
                    HashMap<String, VehicleInfo> map = x.get(i);
                    for (String name2 : map.keySet()) {
                        if (map.get(name2).getNumberPlate().equals(numplate)) {
                            System.out.println("Enter the duestatus value to be updated...");
                            boolean k = s.nextBoolean();
                            map.get(name2).setDueStatus(k);
                            System.out.print(BLUE_TEXT+" " + "1." + ".      " + name2 + map.get(name2)+RESET);
                            System.out.println(" ");
                            Addingdata.getInstance().modifyData(i, map);
                            x = Addingdata.getInstance().getData();
                            displayData(x);
                        }
                    }
                }
            }else if(wish == 2){
                for (int i = 0; i < x.size(); i++) {
                    HashMap<String, VehicleInfo> map = x.get(i);
                    for (String name2 : map.keySet()) {
                        if (map.get(name2).getNumberPlate().equals(numplate)) {
                            System.out.println("Enter the Security Deposit value to be updated...ex($2000)");
                            String secdepo = s.next();
                            map.get(name2).setSecurityDeposit(secdepo);
                            System.out.print(BLUE_TEXT+" " + "1." + ".      " + name2 + map.get(name2)+RESET);
                            System.out.println();
                            Addingdata.getInstance().modifyData(i, map);
                            x = Addingdata.getInstance().getData();
                            displayData(x);
                        }
                    }
                }
            }else if(wish == 3)
                modifyTable();
            else {
                System.out.println("Invalid Choice......");
                modifyVehicle();
            }

    }else {
                System.out.println(RED_TEXT+"No vehicle found with the given number plate."+RESET);
                modifyVehicle();

        }
    }
}

