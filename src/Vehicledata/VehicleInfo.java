package Vehicledata;

public class VehicleInfo {


        private String numberPlate;
        private int availability;
        private boolean dueStatus;
        private String securityDeposit;
        private String bookingStatus;
        private String type;

        public VehicleInfo(String numberPlate, String type, int availability, boolean dueStatus, String securityDeposit,String bookingStatus){
                this.numberPlate = numberPlate;
                this.type = type;
                this.availability = availability;
                this.dueStatus = dueStatus;
                this.securityDeposit = securityDeposit;
                this.bookingStatus = bookingStatus;
        }

        public  String getBookingStatus(){
        return bookingStatus;
        }

        public void setBookingStatus(String bookingStatus){
           this.bookingStatus = bookingStatus;
        }
        // Getters and Setters
        public String getNumberPlate () {
            return numberPlate;
        }

        public void setNumberPlate (String numberPlate){
            this.numberPlate = numberPlate;
        }

        public String type () {
            return type;
        }

        public void setType (String type){
            this.type = type;
        }

        public int getAvailability () {
            return availability;
        }

        public void setAvailability ( int availability){
            this.availability = availability;
        }

        public boolean isDueStatus () {
            return dueStatus;
        }

        public void setDueStatus ( boolean dueStatus){
            this.dueStatus = dueStatus;
        }

        public String getSecurityDeposit () {
            return securityDeposit;
        }

        public void setSecurityDeposit (String securityDeposit){
            this.securityDeposit = securityDeposit;
        }

        @Override
        public String toString () {
            return "           " + numberPlate + "                " + availability + "         " + type + "           " + dueStatus + "          " + securityDeposit + "                      "+bookingStatus;
        }

}

