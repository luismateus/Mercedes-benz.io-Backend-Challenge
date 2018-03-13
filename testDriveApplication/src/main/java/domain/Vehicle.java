package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vehicle {

    private String _id;
    private String _model;
    private String _fuel;
    private String _transmission;
    private Map<String,List<String>> _availability;

    public Vehicle(String id, String model, String fuel, String transmission, Map<String,List<String>> availability){
        _id = id;
        _model = model;
        _fuel = fuel;
        _transmission = transmission;
        _availability = availability;
    }

    public String get_model() {
        return _model;
    }

    public String get_id() {
        return _id;
    }

    public String get_fuel() {
        return _fuel;
    }

    public String get_transmission() {
        return _transmission;
    }

    public Map<String, List<String>> get_availability() {
        return _availability;
    }

}

