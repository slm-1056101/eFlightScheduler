package edu.mum.cs.cs425.eFlightScheduler.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Schedule
 */
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne(optional = false)
    private Flight flight;

    @NotNull
    @OneToOne(optional = false)
    private Runway runway;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime time;

    @Transient
    @JsonIgnore
    public Schedule after;

    @Transient
    @JsonIgnore
    public Schedule before;

    public Schedule(@NotNull Flight flight, @NotNull Runway runway, @NotNull Status status, @NotNull LocalDateTime time) {
        this.flight = flight;
        this.runway = runway;
        this.status = status;
        this.time = time;
    }

    public Schedule(@NotNull LocalDateTime time) {
        this.time = time;
    }

    public Schedule() {

    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", flight=" + flight +
                ", runway=" + runway +
                ", status=" + status +
                ", time=" + time +
                '}';
    }

//    public static Schedule from()
}
