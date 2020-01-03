package hello;

public class AppointmentNotFoundException extends RuntimeException{
    public AppointmentNotFoundException(Long id) {
        super("Appointment id not found : " + id);
    }
}
