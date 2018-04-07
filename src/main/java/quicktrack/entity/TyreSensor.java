package quicktrack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TyreSensor {
    @Id
    private String id;
    private TyrePressureSensor tyrePressureSensor;
    private TyreChangeSensor tyreChangeSensor;
    private String status;
    private String comments;
    private String modifiedDate;
    private String createdDate;
    private String gpsDate;
    private String vehicleNumber;
    private String fleetId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TyrePressureSensor getTyrePressureSensor() {
        return tyrePressureSensor;
    }

    public void setTyrePressureSensor(TyrePressureSensor tyrePressureSensor) {
        this.tyrePressureSensor = tyrePressureSensor;
    }

    public TyreChangeSensor getTyreChangeSensor() {
        return tyreChangeSensor;
    }

    public void setTyreChangeSensor(TyreChangeSensor tyreChangeSensor) {
        this.tyreChangeSensor = tyreChangeSensor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public String getGpsDate() {
        return gpsDate;
    }

    public void setGpsDate(String gpsDate) {
        this.gpsDate = gpsDate;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getFleetId() {
        return fleetId;
    }

    public void setFleetId(String fleetId) {
        this.fleetId = fleetId;
    }

    @Override
    public String toString() {
        return "TyreSensor{" +
                "id='" + id + '\'' +
                ", tyrePressureSensor=" + tyrePressureSensor +
                ", tyreChangeSensor=" + tyreChangeSensor +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", gpsDate='" + gpsDate + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", fleetId='" + fleetId + '\'' +
                '}';
    }
}
