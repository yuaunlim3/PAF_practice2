package vttp2023.batch3.assessment.paf.bookings.models;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Bookings {
        
    @NotEmpty(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @Future(message = "Date booked must be in the future")
    private Date date;

    @Min(value = 1, message = "Days book must be at least 1")
    private int stay;

    private String id;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getStay() {
        return stay;
    }
    public void setStay(int stay) {
        this.stay = stay;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    

    
}
