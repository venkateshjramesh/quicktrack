package quicktrack.entity;

public class VehicleStats {
    private String vehicleNo;
    private Double fuelConsumed;
    private Double fuelAdded;
    private Double distanceTravelled;

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Double getFuelConsumed() {
        return fuelConsumed;
    }

    public void setFuelConsumed(Double fuelConsumed) {
        this.fuelConsumed = fuelConsumed;
    }

    public Double getFuelAdded() {
        return fuelAdded;
    }

    public void setFuelAdded(Double fuelAdded) {
        this.fuelAdded = fuelAdded;
    }

    public Double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(Double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    @Override
    public String toString() {
        return "VehicleStats{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", fuelConsumed=" + fuelConsumed +
                ", fuelAdded=" + fuelAdded +
                ", distanceTravelled=" + distanceTravelled +
                '}';
    }
}
