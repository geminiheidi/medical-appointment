package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @GetMapping("/doctor")
    public Collection<Doctor> getAll(){
        return repository.findAll();

    }

    @GetMapping("/doctor/{id}")
    public Doctor get(@PathVariable  Long id) {
        return repository.findById(id).orElseThrow(() -> new DoctorNotFoundException(id));
    }

    @PostMapping("/doctor")
    public Doctor post(@RequestBody Doctor doctor) {
        return repository.save(doctor);
    }

    @DeleteMapping("/doctor/{id}")
    public void delete(@PathVariable  Long id) {
        repository.deleteById(id);
    }



}
