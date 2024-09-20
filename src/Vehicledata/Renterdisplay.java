package Vehicledata;



import Vehiclerental.Main;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Renterdisplay  {

    static int servicedeposit = 30000;
    static int caution_deposit = 30000;
    String RESET = "\u001B[0m";
    String YELLOW_TEXT = "\u001B[33m";
    String RED_TEXT = "\u001B[31m";
    String GREEN_TEXT = "\u001B[32m";
    String MAGNETA_TEXT = "\u001B[35m";
    String BLUE_TEXT = "\u001B[34m";

    Scanner s = new Scanner(System.in);
    static ArrayList<HashMap<String,VehicleInfo>> x;
    HashMap<String, VehicleInfo> cart = new HashMap<>();
    HashMap<String,VehicleInfo> Booked = new HashMap<>();

    private static Renterdisplay instance = null;
    public static Renterdisplay getInstance(){
        if(instance == null){
            instance = new Renterdisplay();
        }
        return instance;
    }
  //  Addingdata x;
//    public  Renterdisplay(Addingdata x) {
//        this.x = x;
//        Displaymethod();
//    }
    Console console = System.console();



    public void Displaymethod(ArrayList<HashMap<String, VehicleInfo>> data ) {
        x = data;
        int k = 1;
        System.out.println(GREEN_TEXT + "----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("S.no" + "    Name  " + "         Number_Plate " + "  Availability" + "    Type" + "           isDue      " + "  Security-Deposit"+"                 BookingStatus");
        for (HashMap<String, VehicleInfo> map : x) {
            for (String name : map.keySet()) {
                if (map.get(name).isDueStatus()) {
                    System.out.print(" " + k + "     " + name + map.get(name));
                    k++;
                }
            }
            System.out.println(" ");
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);

        System.out.println(" ");
        System.out.println("1. Search by name");
        System.out.println("2. Search by Number plate");
        System.out.println("3. Cart Details");
        System.out.println("4. Book your vehicles in Cart...");
        System.out.println("5. To see past Bookings...");
        System.out.println("6. View Your Balances...");
        System.out.println("7. Return the vehicle");
        System.out.println("8. Go to home");
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
                cart();
                break;
            case 4:
                booking();
                break;
            case 5:
                pastBookings();
                break;
            case 6:
                System.out.println(BLUE_TEXT+" \nService Deposit Balance is "+servicedeposit+"\nCaution Deposit Balance is "+caution_deposit+"\n"+RESET);
                Displaymethod(x);
                break;
            case 7:
                returnVehicle();
                break;
            case 8:
                Main.getInstance().startMainFlow(null);
                break;
        }

    }
    static int car_rent = 4000;
    static  int bike_rent = 2000;
    static  int bike_count = 0;
    static int car_count = 0;
    static double fine = 0;
    static double fine_damage = 15000;

    private void returnVehicle() {

        if(!Booked.isEmpty()) {

            System.out.println();
            for (String name : Booked.keySet()) {
                if (Booked.get(name).type().equals("Bike")) {
                    bike_count += 1;
                } else if (Booked.get(name).type().equals("Car ")) {
                    car_count += 1;
                }
            }
            System.out.println("If your vehicle run more than 500kms..(Y/N) : ");
            String did = s.next();
            if (did.equals("Y")) {
                int temp_b = bike_count;
                int temp_c = car_count;
                while (temp_b != 0) {
                    fine = (fine + bike_rent * 0.15);
                    temp_b -= 1;

                }
                while (temp_c != 0) {
                    fine += car_rent * 0.15;
                    temp_c -= 1;
                }
                System.out.println("Then you have a fine of amount " + fine);
            } else {
                System.out.println(BLUE_TEXT + "There is no extra 15% fine for You......." + RESET);
            }

            cardamage();
            modeofp();
        }else{
            System.out.println("First book your vehicle and raid.......");
            Displaymethod(x);
        }

    }

    private void modeofp(){
        System.out.println(RED_TEXT + "Your final fine amount is " + fine + RESET);
        System.out.println("Do you like to pay the fine through cash or reduce from security deposit....");
        System.out.println(" ");
        System.out.println(BLUE_TEXT + "1. CASH      2. SECURITY DEPOSIT 3.TO GO BACK " + RESET);
        int pref = s.nextInt();
        if (pref == 1) {
            System.out.println("Collecting Payment...");
            try {
                Thread.sleep(4000); // Delay for 1 second
            } catch (InterruptedException ie) {
                System.out.println("Collecting payment.........");
            }
            System.out.println(GREEN_TEXT+"Payment received thank you....."+RESET);
            for ( String name2 : Booked.keySet()) {
                for (HashMap<String, VehicleInfo> map : x) {
                    for (String name : map.keySet()) {
                        if (map.get(name).getNumberPlate().equals(Booked.get(name2).getNumberPlate())){
                            map.get(name).setBookingStatus("Not Booked");
                            x = Addingdata.getInstance().getData();
                        }
                    }
                }
            }
            Displaymethod(x);
        } else if (pref == 2){
            if (fine > servicedeposit) {
                System.out.println("Security deposit is of difference "+(servicedeposit-fine));
                System.out.println("Do you like to pay through cash or increase security deposit..(1.CASH 2.SECURITY DEPOSIT) ");
                int wish = s.nextInt();
                if(wish == 1) {

                    try {
                        Thread.sleep(1000); // Delay for 1 second
                    } catch (InterruptedException ie) {
                        System.out.println("Collecting payment.........");
                    }
                    System.out.println("Payment successful...");
                    for ( String name2 : Booked.keySet()) {
                        for (HashMap<String, VehicleInfo> map : x) {
                            for (String name : map.keySet()) {
                                if (map.get(name).getNumberPlate().equals(Booked.get(name2).getNumberPlate())) {
                                    map.get(name).setBookingStatus("Not Booked");
                                    x = Addingdata.getInstance().getData();
                                }
                            }
                        }
                    }
                    Displaymethod(x);
                }else if(wish == 2){
                    System.out.println("Enter the amount to deposit on security deposit ");
                    int deposit = s.nextInt();
                    if(deposit >= (fine-servicedeposit)){
                        servicedeposit+=deposit;
                        servicedeposit -= (int) fine;
                        System.out.println(GREEN_TEXT+"Success"+RESET);
                        for ( String name2 : Booked.keySet()) {
                            for (HashMap<String, VehicleInfo> map : x) {
                                for (String name : map.keySet()) {
                                    if (map.get(name).getNumberPlate().equals(Booked.get(name2).getNumberPlate())) {
                                        map.get(name).setBookingStatus("Not Booked");
                                        x = Addingdata.getInstance().getData();
                                    }
                                }
                            }
                        }
                        Displaymethod(x);
                    }else{
                        System.out.println("Insufficient Security deposit balance ");
                        modeofp();
                    }
                }

            }
            servicedeposit-= (int) fine;
            System.out.println("Fine is paid..");
            System.out.println("Your service deposit balance is " + servicedeposit);
            for ( String name2 : Booked.keySet()) {
                for (HashMap<String, VehicleInfo> map : x) {
                    for (String name : map.keySet()) {
                        if (name.equals(name2)) {
                            map.get(name).setBookingStatus("Not Booked");
                        }
                    }
                }
            }
            Displaymethod(x);
        }else if(pref == 3){
            returnVehicle();
        }else{
            System.out.println("Invalid choice ");
            modeofp();
        }
    }

    private void cardamage(){
        if(car_count>0) {
            System.out.println("Do you done any damage to the car.....(Y/N) :");
            String damage = s.next();
            if (damage.equals("Y")) {
                System.out.println("How much the car get damaged....(LOW | MEDIUM | HIGH ) : ");
                String damaget = s.next();
                if (damaget.equals("LOW") | damaget.equals("Low") | damaget.equals("low")) {
                  fine += fine_damage*0.2;
                } else if (damaget.equals("Medium") | damaget.equals("MEDIUM") | damaget.equals("medium")) {
                  fine+= fine_damage*0.5;
                } else if (damaget.equals("HIGH") | damaget.equals("High") | damaget.equals("high")) {
                  fine+= fine_damage*0.75;
                } else {
                    System.out.println("Invalid Choice");
                    cardamage();
                }
                System.out.println("Your fine amount including car damage is "+fine);
            }else{
                System.out.println("There is no extra fine for you");
            }
        }
    }

    private void pastBookings(){
        System.out.println(YELLOW_TEXT + "--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("S.no" + "    Name  " + "         Number_Plate " + "  Availability" + "    Type" + "           isDue      " + "  Security-Deposit"+"                BookingStatus");
        int i = 1;
        for (String name : Booked.keySet()) {
            System.out.print(" " + i + ".      " + name + Booked.get(name));
            System.out.println(" ");
            i++;
        }
        if(i==1){
            System.out.println(' ');
            System.out.println("             No booking done yet                  ");
            System.out.println(' ');
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
        Displaymethod(x);

    }

    private void cart(){
        System.out.println("1. View Cart");
        System.out.println("2. Add to cart by number plate");
        System.out.println("3. Remove from cart using number plate");
        System.out.println("4. To go back");
        System.out.println(" ");
        System.out.println(" ");
        int choice1 = s.nextInt();
        switch (choice1){
            case 1:
                viewCart();
                break;
            case 2:
                addToCart();
                break;
            case 3:
                removeFromCart();
                break;
            case 4:
                Displaymethod(x);
                break;
        }
    }

    private void booking(){

        if(servicedeposit >= 13000){
            int total = 0;
            for ( String name2 : cart.keySet()) {
               String x = cart.get(name2).getSecurityDeposit();
               x = x.replace("$", "");
                x = x.trim();
               total += Integer.parseInt(x);
            }
        System.out.println("Are you like to confirm your booking of amount "+total+" (Y/N) :");
            System.out.println("Keep in mind that Vehicle rented must be returned same day.......");
        String choice = s.next();
        if(choice.equals("Y")){
            System.out.println(GREEN_TEXT+"Booking Confirmed"+RESET);
            for ( String name2 : cart.keySet()) {
                for (HashMap<String, VehicleInfo> map : x) {
                    for (String name : map.keySet()) {
                        if (map.get(name).getNumberPlate().equals(cart.get(name2).getNumberPlate())) {
                            map.get(name).setBookingStatus("Booked");
                            x = Addingdata.getInstance().getData();
                        }
                    }
                }
               Booked.put(name2,cart.get(name2));
               cart.remove(name2);
            }
            Displaymethod(x);

        }else if(choice.equals("N")) {
            Displaymethod(x);
        }}else{
            System.out.println("Security servicedeposit is less...");
            System.out.println("Would you like to increase your Security servicedeposit (Y/N) : ");
            String choice = s.next();
            if(choice.equals("Y")){
                System.out.println("Enter the amount to pay !");
                int amount = s.nextInt();
                servicedeposit+=amount;
            }else if(choice.equals("N")){
                Displaymethod(x);
            }

        }
    }

    private void searchByName() {
        System.out.println("Enter the name of the vehicle:");
        String name = s.next();
        System.out.println(" ");
        boolean found = false;
        System.out.println("Details of the selected Vehicle\n");
        System.out.println(BLUE_TEXT + "-------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("S.no" + "    Name  " + "         Number_Plate " + "  Availability" + "    Type" + "           isDue      " + "  Security-Deposit"+"                 BookingStatus");
        int l = 1;
        for (HashMap<String, VehicleInfo> map : x) {
            for (String name1 : map.keySet()) {
                if (name1.trim().equals(name) && map.get(name1).isDueStatus()) {
                    System.out.print(" " + l + ".      " + name + map.get(name1));
                    System.out.println(" ");
                    l++;
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println(" ");
            System.out.println("                                        No vehicle found with the given name.");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
            searchByName();
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
        System.out.println(" ");
        Displaymethod(x);
    }

    private void searchByNumberPlate() {
        System.out.println("Enter the number plate of the vehicle:");
        String numberplate = s.next();
        System.out.println(" ");
        boolean found = false;
        System.out.println(RED_TEXT + "--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("S.no" + "    Name  " + "         Number_Plate " + "  Availability" + "    Type" + "           isDue      " + "  Security-Deposit"+"                 BookingStatus");
        for (HashMap<String, VehicleInfo> map : x) {
            for (String name1 : map.keySet()) {
                if (map.get(name1).getNumberPlate().equals(numberplate) && map.get(name1).isDueStatus()) {
                    System.out.print(" " + "1." + "     " + name1 + map.get(name1));
                    System.out.println(" ");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println(" ");
            System.out.println("                                        No vehicle found with the given number plate.");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
            searchByNumberPlate();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
        System.out.println(" ");
        Displaymethod(x);
    }

    private void viewCart() {
        if (cart.isEmpty()) {
            System.out.println(RED_TEXT+"Your Cart is Empty...");
            System.out.println("If you'd like to add to the cart, search as you like and add."+RESET);
            Displaymethod(x);
        }
            System.out.println(BLUE_TEXT+"Your cart has..."+RESET);
            System.out.println(RED_TEXT + "--------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("S.no" + "    Name  " + "         Number_Plate " + "  Availability" + "    Type" + "           isDue      " + "  Security-Deposit"+"                  BookingStatus");
            int i = 1;
            for (String name : cart.keySet()) {
                System.out.print(" " + i + ".      " + name + cart.get(name));
                System.out.println(" ");
                i++;
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);

        Displaymethod(x);

    }

    private void addToCart() {
        if (cart.size() < 2) {
            System.out.println("Enter the number plate of the vehicle to add to your cart...");
            String nplate = s.next();
            boolean found = false;
            for (HashMap<String, VehicleInfo> map : x) {
                for (String name1 : map.keySet()) {
                    if (map.get(name1).getNumberPlate().equals(nplate)) {
                        cart.put(name1, map.get(name1));
                        found = true;
                        viewCart();
                    }
                }
            }
            if (!found) {
                System.out.println("Invalid Number plate no.......");
                Displaymethod(x);
            }
        } else {
            System.out.println(MAGNETA_TEXT + "Only 2 vehicles can be added to the cart at a time......." + RESET);
            viewCart();
        }
    }

    private void removeFromCart() {
        boolean found = false;
        if(cart.isEmpty()){
            System.out.println("Cart is empty; you can't delete a vehicle...first add to the cart...");
            addToCart();
        }
            System.out.println("Enter the number plate to remove from the cart...");
            String nuplate = s.next();
            for (String name : cart.keySet()) {
                if (cart.get(name).getNumberPlate().equals(nuplate)) {
                    cart.remove(name);
                    found = true;
                    viewCart(); // Exit the loop once the vehicle is found and removed
                }
            }
            if (!found) {
                System.out.println("Invalid Number plate no.......");
                Displaymethod(x);
            } else {
                viewCart(); // Update and show the cart after removal
            }



    }
}
