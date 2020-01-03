package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Patient {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    public Patient(){

    }

    public Patient(Long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setAge(String firstName){
        this.firstName = firstName;
    }
    public void setGender(String lastName){
        this.lastName = lastName;
    }
}

