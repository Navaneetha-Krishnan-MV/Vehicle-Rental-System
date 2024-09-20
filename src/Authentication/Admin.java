package Authentication;


import Vehicledata.Addingdata;
import Vehicledata.Admindisplay;
import Vehicledata.VehicleInfo;
import Vehiclerental.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.Console;
import java.util.Scanner;

public class Admin {

    static String RESET = "\u001B[0m";
    static String RED_TEXT = "\u001B[31m";
    String BLUE_TEXT = "\u001B[34m";
    static String GREEN_TEXT = "\u001B[32m";
    String YELLOW_TEXT = "\u001B[33m";
    String BLACK_BG = "\u001B[40m";
    String WHITE_BG = "\u001B[47m";
    static HashMap<String,String> hash = new HashMap<>();

    static Console console = System.console();
    static Scanner s = new Scanner(System.in);
    private static Admin adminInstance = null;
    private static Addingdata addingdataInstance = null;


    public static Admin getInstance() {
        if (adminInstance == null) {
            adminInstance = new Admin();
        }
//        instantiate();
        return adminInstance;
    }

    /*private static void instantiate() {
        if (addingdataInstance == null) {
            addingdataInstance = new Addingdata();
        }
    }*/

    public static void startFlow(){
        hash.put("navaneethank45@gmail.com","nvn@mad");
        System.out.println(" ");
        System.out.println(GREEN_TEXT+"Login as admin"+RESET);
        System.out.println(" ");
        System.out.println(RED_TEXT+"Enter your admin_email"+RESET);
        String admin_email = s.next();
        System.out.println(RED_TEXT+"Enter your password"+RESET);
        char[] passwordch = console != null ? console.readPassword() : s.next().toCharArray();
        String password = new String(passwordch);
        if (hash.containsKey(admin_email) && hash.get(admin_email).equals(password)) {
            System.out.println(" ");
            System.out.println("Successfully Logged in as " + admin_email); // Create a single instance of Addingdata
            Addingdata.getInstance().addDefaultData();
            ArrayList<HashMap<String, VehicleInfo>> data = Addingdata.getInstance().getData(); // Create a single instance of Addingdata
            Admindisplay.getInstance().displayData(data);
        } else {
            System.out.println(" ");
            System.out.println("Invalid admin_email or password. Please try again.");
            System.out.println(" ➜ 1.Try again             ➜ 2.Go to Home");
            int choice = s.nextInt();
            switch (choice){
                case 1:
                   startFlow();
                    break;
                case 2:
                    Main.getInstance().startMainFlow(null);
                    break;
                default:
                    System.out.println("Invalid choice, returning to Home.");
                    Main.getInstance().startMainFlow(null); // Default to Home
                    break;
            }
        }
    }
}
