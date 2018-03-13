package domain;

import java.util.List;

public class Dealer {

    private String _id;
    private String _name;
    private double _latitude;
    private double _longitude;
    private List<Vehicle> _vehicles;
    private List<String> _closed;

    public Dealer(String id, String name, double latitude, double longitude, List<Vehicle> vehicles, List<String> closed){
        _id = id;
        _name = name;
        _latitude = latitude;
        _longitude = longitude;
        _vehicles = vehicles;
        _closed = closed;

    }

    public String get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public double get_latitude() {
        return _latitude;
    }

    public double get_longitude() {
        return _longitude;
    }

    public List<Vehicle> get_vehicles() {
        return _vehicles;
    }

    public List<String> get_closed() {
        return _closed;
    }
}