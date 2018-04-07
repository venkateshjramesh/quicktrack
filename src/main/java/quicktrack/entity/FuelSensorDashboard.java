package quicktrack.entity;




public class FuelSensorDashboard {

    private Double totalFuel;
    private Double fuelChange;
    private String status;
    private String comments;
    private String modifiedDate;
    private String createdDate;
    private String gpsDate;
    private String vehicleNumber;
    private String fleetId;



    public Double getTotalFuel() {
        return totalFuel;
    }

    public void setTotalFuel(Double totalFuel) {
        this.totalFuel = totalFuel;
    }

    public Double getFuelChange() {
        return fuelChange;
    }

    public void setFuelChange(Double fuelChange) {
        this.fuelChange = fuelChange;
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
        return "FuelSensor{" +
                ", totalFuel=" + totalFuel +
                ", fuelChange=" + fuelChange +
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
