import domain.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleTest {

    private static final String id = "3928f780-295b-4dd0-8cc9-28c0738398d9";
    private static final String model = "AMG";
    private static final  String fuel = "ELECTRIC";
    private static final  String transmission =  "AUTO";
    private static final Map<String,List<String>> availability;
    static{
        availability = new HashMap<>();
        availability.put("thursday", Arrays.asList("1000", "1030"));
        availability.put("monday", Arrays.asList("1000", "1030"));
    }

    @Test
    public void success(){
        Vehicle vehicle = new Vehicle(id, model,fuel,transmission,availability);
        Assert.assertEquals(id,vehicle.get_id());
        Assert.assertEquals(model,vehicle.get_model());
        Assert.assertEquals(fuel,vehicle.get_fuel());
        Assert.assertEquals(transmission,vehicle.get_transmission());
        Assert.assertEquals(availability.toString(),vehicle.get_availability().toString());
    }
}
