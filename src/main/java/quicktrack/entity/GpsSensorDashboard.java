package quicktrack.entity;

import java.util.List;

public class GpsSensorDashboard {

    private List<GpsDetails> gpsDetails;
    private String status;
    private String comments;
    private String modifiedDate;
    private String createdDate;
    private String gpsDate;
    private String vehicleNumber;
    private String fleetId;

    @Override
    public String toString() {
        return "GpsSensorDashboard{" +
                "gpsDetails=" + gpsDetails +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", gpsDate='" + gpsDate + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", fleetId='" + fleetId + '\'' +
                '}';
    }

    public List<GpsDetails> getGpsDetails() {
        return gpsDetails;
    }

    public void setGpsDetails(List<GpsDetails> gpsDetails) {
        this.gpsDetails = gpsDetails;
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
}
