package edu.mum.cs.cs425.eFlightScheduler.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Runway class
 */
@Entity
@Table(name = "runways")
public class Runway {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String code;

    public Runway(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Runway{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}
