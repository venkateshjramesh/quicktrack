package quicktrack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Alerts {

    @Id
    private String id;
    private String vehicleNo;
    private String comments;
    private String sensor;
    private String status;
    private String sensorId;
    private String modifiedDate;
    private String createdDate;
    private String fleetId;

    public Alerts(String vehicleNo, String comments, String sensor, String status, String sensorId, String modifiedDate, String createdDate, String fleetId) {
        this.vehicleNo = vehicleNo;
        this.comments = comments;
        this.sensor = sensor;
        this.status = status;
        this.sensorId = sensorId;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
        this.fleetId = fleetId;
    }

    @Override
    public String toString() {
        return "Alerts{" +
                "id='" + id + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", comments='" + comments + '\'' +
                ", sensor='" + sensor + '\'' +
                ", status='" + status + '\'' +
                ", sensorId='" + sensorId + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", fleetId='" + fleetId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getFleetId() {
        return fleetId;
    }

    public void setFleetId(String fleetId) {
        this.fleetId = fleetId;
    }
}
