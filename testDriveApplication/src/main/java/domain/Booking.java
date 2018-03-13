package domain;
import java.time.LocalDateTime;

public class Booking {

    private String id;
    private String vehicleId;
    private String firstName;
    private String lastName;
    private LocalDateTime pickupDate;
    private LocalDateTime createdAt;
    private LocalDateTime cancelledAt;
    private String cancelledReason;

    public Booking(String id, String vehicleId, String firstName, String lastName, LocalDateTime pickupDate, LocalDateTime createdAt, LocalDateTime cancelledAt, String cancelledReason){
        this.id = id;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickupDate = pickupDate;
        this.createdAt = createdAt;
        this.cancelledAt = cancelledAt;
        this.cancelledReason = cancelledReason;
    }
    public Booking(String id, String vehicleId, String firstName, String lastName, LocalDateTime pickupDate, LocalDateTime createdAt) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickupDate = pickupDate;
        this.createdAt = createdAt;
        this.cancelledAt = null;
        this.cancelledReason = null;
    }

    public String get_id() {
        return id;
    }

    public LocalDateTime get_cancelledAt() {
        return cancelledAt;
    }

    public LocalDateTime get_createdAt() {
        return createdAt;
    }

    public LocalDateTime get_pickupDate() {
        return pickupDate;
    }

    public String get_cancelledReason() {
        return cancelledReason;
    }

    public String get_firstName() {
        return firstName;
    }

    public String get_lastName() {
        return lastName;
    }

    public String get_vehicleId() {
        return vehicleId;
    }

    public void set_cancelledAt(LocalDateTime _cancelledAt) {
        this.cancelledAt = _cancelledAt;
    }

    public void set_cancelledReason(String _cancelledReason) {
        this.cancelledReason = _cancelledReason;
    }
}