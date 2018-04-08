package quicktrack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Dashboard {
    @Id
    private String id;
    private String status;
    private String comments;
    private String modifiedDate;
    private String createdDate;
    private String gpsDate;
    private String vehicleNumber;
    private String fleetId;

   private List<FuelSensorDashboard> fuelSensorDashboard;
   private List<GpsSensorDashboard>  gpsSensorDashboard;
   private List<WeightSensorDashboard> weightSensorDashboard;
   private List<TyreSensorDashboard> tyreSensorDashboard;

   private List<Alerts> alerts;

   private List<VehicleStats> vehicleStats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<FuelSensorDashboard> getFuelSensorDashboard() {
        return fuelSensorDashboard;
    }

    public void setFuelSensorDashboard(List<FuelSensorDashboard> fuelSensorDashboard) {
        this.fuelSensorDashboard = fuelSensorDashboard;
    }

    public List<GpsSensorDashboard> getGpsSensorDashboard() {
        return gpsSensorDashboard;
    }

    public void setGpsSensorDashboard(List<GpsSensorDashboard> gpsSensorDashboard) {
        this.gpsSensorDashboard = gpsSensorDashboard;
    }

    public List<WeightSensorDashboard> getWeightSensorDashboard() {
        return weightSensorDashboard;
    }

    public void setWeightSensorDashboard(List<WeightSensorDashboard> weightSensorDashboard) {
        this.weightSensorDashboard = weightSensorDashboard;
    }

    public List<TyreSensorDashboard> getTyreSensorDashboard() {
        return tyreSensorDashboard;
    }

    public void setTyreSensorDashboard(List<TyreSensorDashboard> tyreSensorDashboard) {
        this.tyreSensorDashboard = tyreSensorDashboard;
    }

    public List<Alerts> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alerts> alerts) {
        this.alerts = alerts;
    }

    public List<VehicleStats> getVehicleStats() {
        return vehicleStats;
    }

    public void setVehicleStats(List<VehicleStats> vehicleStats) {
        this.vehicleStats = vehicleStats;
    }

    @Override
    public String toString() {
        return "Dashboard{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", gpsDate='" + gpsDate + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", fleetId='" + fleetId + '\'' +
                ", fuelSensorDashboard=" + fuelSensorDashboard +
                ", gpsSensorDashboard=" + gpsSensorDashboard +
                ", weightSensorDashboard=" + weightSensorDashboard +
                ", tyreSensorDashboard=" + tyreSensorDashboard +
                ", alerts=" + alerts +
                ", vehicleStats=" + vehicleStats +
                '}';
    }
}
