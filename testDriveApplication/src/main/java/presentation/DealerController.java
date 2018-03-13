package presentation;


import org.springframework.web.bind.annotation.*;
import domain.Dealer;
import domain.Root;
import java.util.List;
import java.util.Map;

@RestController
public class DealerController {

    @RequestMapping("/v1/ping")
    public String test(){
        return "ping";
    }
    @RequestMapping(value = "/dealers/by-location" , method = RequestMethod.POST)
    public List<Dealer> listVehiclesByLocation(@RequestBody Map<String,String> requestParams){

        String model = requestParams.get("model");
        String fuel = requestParams.get("fuel");
        String transmission = requestParams.get("transmission");
        double latitude = Double.parseDouble(requestParams.get("latitude"));
        double longitude = Double.parseDouble(requestParams.get("longitude"));

        if(model==null){
            model="";
        }
        if(fuel==null){
            fuel="";
        }
        if(transmission==null){
            transmission="";
        }

        try{
            return Root.getInstance().findDealersByLocation(model,fuel,transmission,latitude,longitude);
        }catch (Exception e){}
        return null;}


}
