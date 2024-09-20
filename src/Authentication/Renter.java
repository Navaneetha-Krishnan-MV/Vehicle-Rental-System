package Authentication;

import Vehicledata.Addingdata;
import Vehicledata.Admindisplay;
import Vehicledata.Renterdisplay;
import Vehicledata.VehicleInfo;
import Vehiclerental.Main;

import java.awt.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.*;

public class Renter {

    static String RESET = "\u001B[0m";
    static String RED_TEXT = "\u001B[31m";
    static String MAGNETA_TEXT = "\u001B[35m";
    static String BLUE_TEXT = "\u001B[34m";
    String GREEN_TEXT = "\u001B[32m";
    static String YELLOW_TEXT = "\u001B[33m";
    String BLACK_BG = "\u001B[40m";
    String WHITE_BG = "\u001B[47m";
    static HashMap<String,String> hash = new HashMap<>();
    static Scanner s = new Scanner(System.in);
    private static Renter instance = null;

    public static Renter getInstance() {
        if (instance == null) {
            instance = new Renter();
        }
        return instance;
    }

    public static void startFlow() {
        // Prepopulate with some users for testing
        hash.put("vijay@gmail.com", "thalapathy");
        hash.put("navaneethan@gmail.com", "password");
        System.out.println(" ");
        System.out.println(BLUE_TEXT+"1.Account Creation "+RESET);
        System.out.println(BLUE_TEXT+"2.Login to existing account"+RESET);
        boolean status = true;
        while(status){
            System.out.print(YELLOW_TEXT+"Enter your choice to proceed (1/2) : "+RESET);
            int choice = s.nextInt();
            switch (choice){
                case 1:
                    Signin();
                    status = false;
                    break;
                case 2:
                    login();
                    status = false;
                    break;
            }
        }
    }
    public static void Signin(){
        System.out.println(" ");
        System.out.println(RED_TEXT+"You requested to create an account"+RESET);
        System.out.println(" ");
        System.out.println(MAGNETA_TEXT+"Enter your user_email : "+RESET);
        String user_email = s.next();
        System.out.println(MAGNETA_TEXT+"Enter your password"+RESET);
        String password = s.next();
        hash.put(user_email,password);
        if (hash.containsKey(user_email) && hash.get(user_email).equals(password)) {
            System.out.println(RED_TEXT+"Successfully signed in as " + user_email +"."+RESET);
            System.out.println(RED_TEXT+"Now try logging in........."+RESET);
            login();

        } else {
            System.out.println(RED_TEXT+"Invalid user_email or password. Please try again."+RESET);
        }
    }

    public static void login(){
        System.out.println(" ");
        System.out.println(BLUE_TEXT+"You requested to login to your account"+RESET);
        System.out.println(YELLOW_TEXT+"Enter your user_email"+RESET);
        String user_email = s.next();
        System.out.println(YELLOW_TEXT+"Enter your password"+RESET);
        String password = s.next();
        if (hash.containsKey(user_email) && hash.get(user_email).equals(password)) {
            System.out.println(BLUE_TEXT+"Successfully Logged in as " + user_email+RESET);
//            Addingdata data = new Addingdata(); // Create a single instance of Addingdata
//            new Renterdisplay(data);
            Addingdata.getInstance().addDefaultData();
            ArrayList<HashMap<String, VehicleInfo>> data = Addingdata.getInstance().getData();
            Renterdisplay.getInstance().Displaymethod(data);

        } else {
            System.out.println(" ");
            System.out.println(RED_TEXT+"Invalid user_email or password. Please try again."+RESET);
            System.out.println(MAGNETA_TEXT+" ➜ 1.Go to Sigin              ➜ 2.Go to Login         3.GO to home"+RESET);
            int choice = s.nextInt();
            switch (choice){
                case 1:
                    Signin();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    Main.getInstance().startMainFlow(null);
                    break;
                default:
                    System.out.println(RED_TEXT+"Invalid choice, returning to Home."+RESET);
                    Main.getInstance().startMainFlow(null); // Default to Home
                    break;
            }
        }
    }
}
