package quicktrack.entity;

public class TyreDetails {

    private TyrePressureSensor tyrePressureSensor;
    private TyreChangeSensor tyreChangeSensor;
    private String modifiedDate;
    private String createdDate;
    private String status;
    private String comments;

    @Override
    public String toString() {
        return "TyreDetails{" +
                "tyrePressureSensor=" + tyrePressureSensor +
                ", tyreChangeSensor=" + tyreChangeSensor +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", status='" + status + '\'' +
                ", comments='" + comments + '\'' +
                '}';
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
