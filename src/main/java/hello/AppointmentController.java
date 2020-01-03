package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


@RestController
public class AppointmentController {

    @Autowired
    private AppointmentRepository repository;

    @GetMapping("/appointment")
    public Collection<Appointment> getAll(@RequestParam(value="docId", defaultValue = "" ) String docId, @RequestParam(value="date", defaultValue = "") String dateStr){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            if(docId.equals("") || dateStr.equals("")){
                return repository.findAll();
            } else {
                Date date = formatter.parse(dateStr);
                Collection<Appointment> r = repository.findAll();
                List<Appointment> res = new ArrayList<>();
                for(Appointment appointment : r){

                if(appointment.getDocId() == Long.parseLong(docId) && isSameDay(appointment.getDate(), date)){
                    res.add(appointment);
                }
                }
                return res;
            }

        } catch (ParseException e) {
            throw new InvalidDateException();
        }
    }

    public List<Appointment> getAppointment(Long docId, Date date){
        Collection<Appointment> r = repository.findAll();
        List<Appointment> res = new ArrayList<>();
        for(Appointment appointment : r){

            if(appointment.getDocId() == docId && isSameDay(appointment.getDate(), date)){
                res.add(appointment);
            }
        }
        return res;

    }


    public static boolean isSameDay(Date date1, Date date2) {
        System.out.println(date1 + "date1, " + date1.getTime());
        System.out.println(date2 + "date2, " + date2.getTime());
        LocalDate localDate1 = date1.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate localDate2 = date2.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return localDate1.isEqual(localDate2);
    }



    @GetMapping("/appointment/{id}")
    public Appointment get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
    }

    @PostMapping("/appointment")
    public Appointment post(@RequestBody Appointment appointment) {
        Date date = appointment.getDate();
        System.out.println(date + ", " + date.getTime());
        List<Appointment> list = getAppointment(appointment.getDocId(), appointment.getDate());

        if(date.getMinutes() % 15 == 0){
            int count = 0;
            for(Appointment a : list){
                if(a.getDate().getTime() == date.getTime()){
                    count++;
                }
            }
            if(count < 3){
                return repository.save(appointment);
            } else {
                throw new AppointmentFullException();
            }
        }
        throw new AppointmentWrongTimeException();
    }

    @DeleteMapping("/appointment/{id}")
    public void delete(@PathVariable  Long id) {
        repository.deleteById(id);
    }





}
