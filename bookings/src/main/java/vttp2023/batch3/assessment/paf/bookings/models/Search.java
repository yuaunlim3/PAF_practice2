package vttp2023.batch3.assessment.paf.bookings.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class Search {
    
    @NotEmpty(message = "Country cannot be empty")
    @NotNull(message = "Country cannot be null")
    private String country;

    @Min(value = 1, message = "Number of pax have to be at least 1")
    @Max(value = 10, message = "Number of pax cannot exceed 10")
    private int people;

    @Min(value = 1, message = "Price have to be at least 1")
    @Max(value = 10000, message = "Price cannot exceed 10000")
    private float min;

    @Min(value = 1, message = "Price have to be at least 1")
    @Max(value = 10000, message = "Price cannot exceed 10000")
    private float max;


    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getPeople() {
        return people;
    }
    public void setPeople(int people) {
        this.people = people;
    }
    public float getMin() {
        return min;
    }
    public void setMin(float min) {
        this.min = min;
    }
    public float getMax() {
        return max;
    }
    public void setMax(float max) {
        this.max = max;
    }
    @Override
    public String toString() {
        return "Search [country=" + country + ", people=" + people + ", min=" + min + ", max=" + max + "]";
    }

    

    


}
