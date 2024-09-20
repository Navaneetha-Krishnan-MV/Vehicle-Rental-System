package Vehicledata;

import java.util.ArrayList;
import java.util.HashMap;

    public class Addingdata{
        static ArrayList<HashMap<String, VehicleInfo>> completeVehicleData = new ArrayList<>();

        boolean starting = false;
        private static Addingdata instance = null;


        public static Addingdata getInstance() {
            if (instance == null) {
                instance = new Addingdata();
            }
            return instance;
        }


        public static void addDefaultData() {

            if (completeVehicleData.isEmpty()) {
                // Example HashMap
                HashMap<String, VehicleInfo> map1 = new HashMap<>();
                map1.put("Swift", new VehicleInfo("TN2234", "Car ", 1, false, "$10000","Not Booked"));

                // Add the HashMap to the ArrayList
                completeVehicleData.add(map1);
                HashMap<String, VehicleInfo> map2 = new HashMap<>();
                map2.put("MT-15", new VehicleInfo("TN1234", "Bike", 1, true, "$3000","Not Booked"));

                // Add the HashMap to the ArrayList
                completeVehicleData.add(map2);

                HashMap<String, VehicleInfo> map3 = new HashMap<>();
                map3.put("R15  ", new VehicleInfo("TN3245", "Car ", 1, true, " $3000","Not Booked"));

                // Add the HashMap to the ArrayList
                completeVehicleData.add(map3);

                HashMap<String, VehicleInfo> map4 = new HashMap<>();
                map4.put("Duke ", new VehicleInfo("TN8976", "Bike", 1, true, " $3000","Not Booked"));

                // Add the HashMap to the ArrayList
                completeVehicleData.add(map4);

                HashMap<String, VehicleInfo> map5 = new HashMap<>();
                map5.put("Swift", new VehicleInfo("TN9080", "Car ", 2, true, " $10000","Not Booked"));

                // Add the HashMap to the ArrayList
                completeVehicleData.add(map5);
            }
        }

        public static ArrayList<HashMap<String, VehicleInfo>> getData() {
            return completeVehicleData;
        }

        public static void addDataToList(HashMap<String, VehicleInfo> dataToAdd) {
            completeVehicleData.add(dataToAdd);
        }

        public static void removeDataFromList(int indexToBeRemoved) {
            completeVehicleData.remove(indexToBeRemoved);
        }

        public static void modifyData(int indexToBeModified, HashMap<String, VehicleInfo> data) {
            Addingdata.completeVehicleData.set(indexToBeModified, data);
        }
    }
