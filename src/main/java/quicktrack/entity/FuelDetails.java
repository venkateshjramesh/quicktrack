package quicktrack.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FuelDetails {
    private Double totalFuel;
    private Double fuelChange;
    private String modifiedDate;
    private String createdDate;
    private String status;
    private String comments;

    @Override
    public String toString() {
        return "FuelDetails{" +
                "totalFuel=" + totalFuel +
                ", fuelChange=" + fuelChange +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }

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
}
