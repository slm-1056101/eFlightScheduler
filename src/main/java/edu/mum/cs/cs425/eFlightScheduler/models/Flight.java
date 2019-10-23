package edu.mum.cs.cs425.eFlightScheduler.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Flight class
 */
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String airline;
    private String description;

    public Flight() {

    }

    public Flight(String airline, String description) {
        this.airline = airline;
        this.description = description;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airline='" + airline + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
