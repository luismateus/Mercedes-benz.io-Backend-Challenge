package presentation;


import org.springframework.web.bind.annotation.*;
import domain.Root;
import domain.Vehicle;

import java.util.List;
import java.util.Map;

@RestController
public class VehicleController {

    @RequestMapping(value = "/vehicles/by-model" , method = RequestMethod.POST)
    public List<Vehicle> listVehiclesByModel(@RequestBody Map<String,String> requestParams){
        String model = requestParams.get("model");
        try{
            return Root.getInstance().listVehiclesByModel(model);
        }catch (Exception e){}
        return null;}

    @RequestMapping(value = "/vehicles/by-fuel" , method = RequestMethod.POST)
    public List<Vehicle> listVehiclesByFuel(@RequestBody Map<String,String> requestParams){
        String fuel = requestParams.get("fuel");
        try{
            return Root.getInstance().listVehiclesByFuel(fuel);
        }catch (Exception e){}
        return null;}


    @RequestMapping(value = "/vehicles/by-transmission" , method = RequestMethod.POST)
    public List<Vehicle> listVehiclesByTransmission(@RequestBody Map<String,String> requestParams){
        String transmission = requestParams.get("transmission");
        try{
            return Root.getInstance().listVehiclesByTransmission(transmission);
        }catch (Exception e){}
        return null;}

    @RequestMapping(value = "/vehicles/by-dealer" , method = RequestMethod.POST)
    public List<Vehicle> listVehiclesByDealer(@RequestBody Map<String,String> requestParams){
        String name = requestParams.get("name");
        try{
            return Root.getInstance().listVehiclesByDealer(name);
        }catch (Exception e){}
        return null;}



}
