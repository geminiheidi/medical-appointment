package hello;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;

    private Long docId;
    private String patientFirstName;
    private String patientLastName;
    private Date date;
    private String kind;

    public Appointment(){

    }

    public Appointment(Long id, Long docId, String patientFirstName, String patientLastName, Date date, String kind){
        this.id = id;
        this.docId = docId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.date = date;
        this.kind = kind;
    }


    public Long getId() {
        return id;
    }

    public Long getDocId(){
        return docId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }
    public String getPatientLastName(){
        return patientLastName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate(){
        return date;
    }


    public String getKind(){
        return kind;
    }
    public void setDocId(Long docId){
        this.docId = docId;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setPatientFirstName(String patientFirstName){
        this.patientFirstName = patientFirstName;
    }
    public void setPatientLastName(String patientLastName){
        this.patientLastName = patientLastName;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public void setKind(String kind){
        this.kind = kind;
    }
}

