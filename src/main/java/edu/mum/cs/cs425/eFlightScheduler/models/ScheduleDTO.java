package edu.mum.cs.cs425.eFlightScheduler.models;

/**
 * Schedule DTO
 */
public class ScheduleDTO {

    private Long flightId;

    private Long runwayId;

    private String status;

    private String time;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getRunwayId() {
        return runwayId;
    }

    public void setRunwayId(Long runwayId) {
        this.runwayId = runwayId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "flightId=" + flightId +
                ", runwayId=" + runwayId +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
