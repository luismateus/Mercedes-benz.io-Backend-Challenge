package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.io.FileReader;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




public class Root {

    private static List<Dealer> _dealers;
    private static List<Booking> _bookings;
    private static List<Vehicle> _vehicles;
    private static Root instance = null;

    public Root(){
        _dealers = new ArrayList<>();
        _bookings = new ArrayList<>();
        _vehicles = new ArrayList<>();
    }


    public static Root getInstance(){
        if(instance == null){
            instance = new Root();
        }
        return instance;
    }

    public List<Dealer> get_dealers() {
        return _dealers;
    }

    public List<Booking> get_bookings() {
        return _bookings;
    }

    public static List<Vehicle> get_vehicles() {
        return _vehicles;
    }

    public void set_dealers(){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("dataset.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray vehicles=null;
            JSONArray dealers = (JSONArray) jsonObject.get("dealers");
            for (Object o : dealers){
                JSONObject dealer = (JSONObject) o;
                String idD = (String) dealer.get("id");
                String name = (String) dealer.get("name");
                double latitude = (double) dealer.get("latitude");
                double longitude = (double) dealer.get("longitude");
                List<String> closed = (List<String>) dealer.get("closed");
                List<Vehicle> vehicleList = new ArrayList<>();
                vehicles = (JSONArray) dealer.get("vehicles");
                for (Object ob : vehicles) {
                    JSONObject vehicle = (JSONObject) ob;
                    String id = (String) vehicle.get("id");
                    String model = (String) vehicle.get("model");
                    String fuel = (String) vehicle.get("fuel");
                    String transmission = (String) vehicle.get("transmission");
                    Map<String, List<String>> availability = (Map<String, List<String>>) vehicle.get("availability");
                    Vehicle newV = new Vehicle(id,model,fuel,transmission, availability);
                    _vehicles.add(newV);
                    vehicleList.add(newV);
                }
                Dealer newDealer = new Dealer(idD,name ,latitude,longitude,vehicleList,closed);
                _dealers.add(newDealer);
            }
        }catch(Exception e){

        }
    }

    public void set_bookings() {
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader("dataset.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray bookingsArray = (JSONArray) jsonObject.get("bookings");
            for(Object o : bookingsArray){
                JSONObject booking = (JSONObject) o;
                String id = (String) booking.get("id");
                String firstName = (String) booking.get("firstName");
                String lastName = (String) booking.get("lastName");
                String vehicleId = (String) booking.get("vehicleId");
                String pickup = (String) booking.get("pickupDate");
                String created = (String) booking.get("createdAt");
                LocalDateTime pickupDate = LocalDateTime.parse(pickup);
                LocalDateTime createdAt = LocalDateTime.parse(created);
                if(booking.containsKey("cancelledAt")){
                    String cancelled = (String) booking.get("cancelledAt");
                    LocalDateTime cancelledAt = LocalDateTime.parse(cancelled);
                    String cancelledReason = (String) booking.get("cancelledReason");
                    Booking newBooking = new Booking(id,firstName, lastName, vehicleId, pickupDate, createdAt, cancelledAt, cancelledReason);
                    _bookings.add(newBooking);
                }else{
                    Booking newBooking = new Booking(id,firstName, lastName, vehicleId, pickupDate, createdAt);
                    _bookings.add(newBooking);
                }

            }
        }catch (Exception e){

        }
    }

    public  List<Vehicle> listVehiclesByModel(String model){
        updateVehicles();
        List<Vehicle> temp = new ArrayList<Vehicle>();
        for(Vehicle v : _vehicles){
            if(v.get_model().equals(model)){
                temp.add(v);
            }
        }return temp;
    }

    public  List<Vehicle> listVehiclesByFuel(String fuel){
        updateVehicles();
        List<Vehicle> temp = new ArrayList<Vehicle>();
        for(Vehicle v : _vehicles){
            if(v.get_fuel().equals(fuel)){
                temp.add(v);
            }
        }return temp;
    }

    public  List<Vehicle> listVehiclesByTransmission(String transmission){
        updateVehicles();
        List<Vehicle> temp = new ArrayList<Vehicle>();
        for(Vehicle v : _vehicles){
            if(v.get_transmission().equals(transmission)){
                temp.add(v);
            }
        }return temp;
    }

    public  List<Vehicle> listVehiclesByDealer(String dealerName){
        updateVehicles();
        for(Dealer d : _dealers){
            if(d.get_name().equals(dealerName)){
                return d.get_vehicles();
            }
        }return null;
    }

    private void updateVehicles(){
        _vehicles.clear();
        for (Dealer d : _dealers){
            _vehicles.addAll(d.get_vehicles());
        }
    }


    public List<Dealer> findDealersByLocation(String model, String fuel, String transmission, double latitude, double longitude){
        Map<Double,Dealer> temp = new TreeMap<>() ;
        double dist;
        for(Dealer d : _dealers){
            for (Vehicle v : d.get_vehicles()){
                if((v.get_model().equals(model) || model.equals(""))
                        && (v.get_transmission().equals(transmission)||transmission.equals(""))
                        && (v.get_fuel().equals(fuel) || fuel.equals(""))){
                    dist = Math.sqrt(Math.pow(latitude - d.get_latitude(),2) + Math.pow(longitude - d.get_longitude(),2));
                    temp.put(dist,d);

                }
            }
        }
        return new ArrayList<Dealer>(temp.values());
    }

    public Booking createNewBooking(String id,String vehicleId , String firstName, String lastName, LocalDateTime pickupDate, LocalDateTime createdAt){
        updateVehicles();
        String weekDay = pickupDate.getDayOfWeek().toString().toLowerCase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String hour = pickupDate.format(formatter);
        for (Booking b : _bookings) {
            if (b.get_pickupDate().equals(pickupDate) && b.get_cancelledAt() != null) {
                return null;
            }else{
                for (Vehicle v : _vehicles) {
                    if (v.get_id().equals(vehicleId)
                            && v.get_availability().containsKey(weekDay)
                            && v.get_availability().get(weekDay).contains(hour)) {
                        Booking newBooking = new Booking(id, vehicleId, firstName, lastName, pickupDate, createdAt);
                        _bookings.add(newBooking);
                        return newBooking;
                    }
                }

            }
        }

        return null;
    }

    public Booking cancelBooking(String id , String cancelledReason){
        for (Booking b : _bookings){
            if (b.get_id().equals(id)){
                LocalDateTime now = LocalDateTime.now();
                b.set_cancelledAt(now);
                b.set_cancelledReason(cancelledReason);
                return b;
            }
        }return null;
    }

}