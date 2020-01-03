package hello;

public class AppointmentFullException extends RuntimeException{
    public AppointmentFullException() {
        super("Appointment full");
    }
}
