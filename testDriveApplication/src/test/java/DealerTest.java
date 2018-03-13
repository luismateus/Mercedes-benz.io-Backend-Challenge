import domain.Dealer;
import domain.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class DealerTest {
    private static Vehicle vehicle;
    private List<Vehicle> vehicles;
    private static final String id = "ab49f56a-451d-4721-8319-efdca5e69680";
    private static final String name ="MB Albufeira";
    private static final double latitude = 37.104404;
    private static final double longitude = -8.236308;
    private List<String> closed = new ArrayList<String>( Arrays.asList("tuesday", "wednesday"));

    @Before
    public void before(){
        String idV = "3928f780-295b-4dd0-8cc9-28c0738398d9";
        String modelV = "AMG";
        String fuelV = "ELECTRIC";
        String transmissionV =  "AUTO";
        Map<String,List<String>>  availabilityV;
        availabilityV = new HashMap<>();
        availabilityV.put("thursday", Arrays.asList("1000", "1030"));
        availabilityV.put("monday", Arrays.asList("1000", "1030"));
        vehicle = new Vehicle(idV,modelV,fuelV,transmissionV,availabilityV);
        vehicles = new ArrayList<>();
        vehicles.add(vehicle);

    }

    @Test
    public void success(){
        Dealer dealer = new Dealer(id,name,latitude,longitude,vehicles,closed);
        Assert.assertEquals(id,dealer.get_id());
        Assert.assertEquals(name,dealer.get_name());
        Assert.assertEquals(latitude,dealer.get_latitude(),0.00001);
        Assert.assertEquals(longitude,dealer.get_longitude(),0.00001);
        Assert.assertEquals(vehicles,dealer.get_vehicles());
        Assert.assertEquals(closed,dealer.get_closed());


    }
}
