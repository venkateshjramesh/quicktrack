package quicktrack.entity;

public class TyreDetails {

    private TyrePressureSensor tyrePressureSensor;
    private TyreChangeSensor tyreChangeSensor;
    private String modifiedDate;
    private String createdDate;

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

    @Override
    public String toString() {
        return "TyreDetails{" +
                "tyrePressureSensor=" + tyrePressureSensor +
                ", tyreChangeSensor=" + tyreChangeSensor +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
