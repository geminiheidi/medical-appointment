
package hello;

public class AppointmentWrongTimeException extends RuntimeException{
    public AppointmentWrongTimeException() {
        super("Appointment wrong time");
    }
}
