package quicktrack.entity;

public class WeightDetails {
    private Double totalWeight;
    private Double weightChange;
    private String modifiedDate;
    private String createdDate;

    @Override
    public String toString() {
        return "WeightDetails{" +
                "totalWeight=" + totalWeight +
                ", weightChange=" + weightChange +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getWeightChange() {
        return weightChange;
    }

    public void setWeightChange(Double weightChange) {
        this.weightChange = weightChange;
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
}
