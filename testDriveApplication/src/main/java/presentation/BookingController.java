package presentation;

import domain.Booking;
import domain.Root;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class BookingController {

    @RequestMapping(value = "/bookings/make-new" , method = RequestMethod.POST)
    public Booking makeBooking(@RequestBody Map<String,String> requestParams){
        String id = requestParams.get("id");
        String vehicleId = requestParams.get("vehicleId");
        String firstName = requestParams.get("firstName");
        String lastName = requestParams.get("lastName");
        LocalDateTime pickupDate = LocalDateTime.parse(requestParams.get("pickupDate"));
        LocalDateTime createdAt = LocalDateTime.parse(requestParams.get("createdAt"));
        try {
            return Root.getInstance().createNewBooking(id,vehicleId,firstName,lastName,pickupDate,createdAt);

        }catch (Exception e){}
        return null;
    }

    @RequestMapping(value = "/bookings/cancel" , method = RequestMethod.POST)
    public Booking cancelBooking(@RequestBody Map<String,String> requestParams){
        String id = requestParams.get("id");
        String cancelledReason = requestParams.get("cancelledReason");
        try{
            return domain.Root.getInstance().cancelBooking(id,cancelledReason);
        }catch (Exception e){}
        return null;
    }

}
