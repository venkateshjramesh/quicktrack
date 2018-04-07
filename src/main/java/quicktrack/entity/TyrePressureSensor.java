package quicktrack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TyrePressureSensor {

    private Double frontLeft;
    private Double frontRight;
    private Double backLeft;
    private Double backRight;


    @Override
    public String toString() {
        return "TyrePressureSensor{" +
                "frontLeft=" + frontLeft +
                ", frontRight=" + frontRight +
                ", backLeft=" + backLeft +
                ", backRight=" + backRight +
                '}';
    }

    public Double getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(Double frontLeft) {
        this.frontLeft = frontLeft;
    }

    public Double getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(Double frontRight) {
        this.frontRight = frontRight;
    }

    public Double getBackLeft() {
        return backLeft;
    }

    public void setBackLeft(Double backLeft) {
        this.backLeft = backLeft;
    }

    public Double getBackRight() {
        return backRight;
    }

    public void setBackRight(Double backRight) {
        this.backRight = backRight;
    }
}
