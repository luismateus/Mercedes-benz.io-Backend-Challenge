import domain.Booking;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class BookingTest {

    private static final String id = "1c6bd910-12b1-45d6-b4d8-cdff2f37db90";
    private static final String vehicleId = "3928f780-295b-4dd0-8cc9-28c0738398d9";
    private static final String firstName = "Joanna";
    private static final String lastName = "Randolph";
    private static LocalDateTime pickupDate = LocalDateTime.parse("2018-03-03T10:30:00");
    private static LocalDateTime createdAt = LocalDateTime.parse("2018-02-26T08:42:46.3");
    private static LocalDateTime cancelledAt = LocalDateTime.parse("2018-02-28T08:42:46.3");
    private static String cancelledReason = "reasons";



    @Test
    public void success1() {
        Booking booking = new Booking(id, vehicleId, firstName, lastName, pickupDate, createdAt);
        Assert.assertEquals(id,booking.get_id());
        Assert.assertEquals(vehicleId,booking.get_vehicleId());
        Assert.assertEquals(firstName,booking.get_firstName());
        Assert.assertEquals(lastName,booking.get_lastName());
        Assert.assertEquals(pickupDate,booking.get_pickupDate());
        Assert.assertEquals(createdAt,booking.get_createdAt());
        Assert.assertNull(booking.get_cancelledAt());
        Assert.assertNull(booking.get_cancelledReason());

    }

    @Test
    public void success2() {
        Booking booking = new Booking(id, vehicleId, firstName, lastName, pickupDate, createdAt,cancelledAt,cancelledReason);
        Assert.assertEquals(id,booking.get_id());
        Assert.assertEquals(vehicleId,booking.get_vehicleId());
        Assert.assertEquals(firstName,booking.get_firstName());
        Assert.assertEquals(lastName,booking.get_lastName());
        Assert.assertEquals(pickupDate,booking.get_pickupDate());
        Assert.assertEquals(createdAt,booking.get_createdAt());
        Assert.assertEquals(cancelledAt,booking.get_cancelledAt());
        Assert.assertEquals(cancelledReason,booking.get_cancelledReason());

    }

}