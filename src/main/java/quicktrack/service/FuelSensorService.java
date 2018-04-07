package quicktrack.service;

import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quicktrack.entity.*;
import quicktrack.repository.AlertRepository;
import quicktrack.repository.DashboardRepository;
import quicktrack.repository.FuelSensorRepository;
import quicktrack.repository.WeightSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class FuelSensorService {

    @Autowired
    FuelSensorRepository  fuelSensorRepository;

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    DashboardRepository dashboardRepository;

    public void saveFuelSensor(FuelSensor fuelSensor){
        //if id, creation date and modified date is present the remove it
        fuelSensor.setId(null);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        fuelSensor.setCreatedDate(date);
        fuelSensor.setModifiedDate(date);
        fuelSensorRepository.save(fuelSensor);
        Alerts alerts = null;
        //add alert if the status is red and the comment is overspeed or sudden break
        if(fuelSensor.getStatus().equalsIgnoreCase("R")){
            alerts = new Alerts(fuelSensor.getVehicleNumber(), fuelSensor.getComments(), "FUEL", fuelSensor.getStatus(),fuelSensor.getId(),date, date, fuelSensor.getFleetId());
            alertRepository.save(alerts);
        }
        saveDashboard(fuelSensor,alerts);
    }

    //method to add the fuel sensor details to dashboard
    public void saveDashboard(FuelSensor fuelSensor,Alerts alerts){
        Dashboard dashboard = dashboardRepository.findByFleetId(fuelSensor.getFleetId());
        if(dashboard == null){
            dashboard = new Dashboard();
            dashboard.setFleetId(fuelSensor.getFleetId());
        }
        List<FuelSensorDashboard> fuelSensorDashboard = dashboard.getFuelSensorDashboard();
        if(fuelSensorDashboard == null){
            fuelSensorDashboard = new LinkedList<FuelSensorDashboard>();
        }

        saveAlertsInDashboard(alerts, dashboard);

        checkForVechicleNoAvailability(fuelSensor, fuelSensorDashboard);
        dashboard.setFuelSensorDashboard(fuelSensorDashboard);
        dashboardRepository.save(dashboard);
    }

    private void saveAlertsInDashboard(Alerts alerts, Dashboard dashboard) {
        if(alerts != null){
            List<Alerts> alertList = dashboard.getAlerts();
            if(alertList == null) {
                alertList = new LinkedList<Alerts>();
            }
            if(alertList.size() > 150){
                alertList.remove(0);
            }
            alertList.add(alerts);
            dashboard.setAlerts(alertList);
        }
    }

    private void checkForVechicleNoAvailability(FuelSensor fuelSensor, List<FuelSensorDashboard> fuelSensorDashboard) {
        for(int i=0;i<fuelSensorDashboard.size();i++){
            if(fuelSensorDashboard.get(i).getVehicleNumber().equalsIgnoreCase(fuelSensor.getVehicleNumber())){
                fuelSensorDashboard.remove(i);
            }
        }
        fuelSensorDashboard.add(convertToFuelSensorDashboard(fuelSensor));
    }


    public FuelSensorDashboard convertToFuelSensorDashboard(FuelSensor fuelSensor){
        FuelSensorDashboard fuelSensorDashboard = new FuelSensorDashboard();
        fuelSensorDashboard.setTotalFuel(fuelSensor.getTotalFuel());
        fuelSensorDashboard.setFuelChange(fuelSensor.getFuelChange());
        fuelSensorDashboard.setStatus(fuelSensor.getStatus());
        fuelSensorDashboard.setComments(fuelSensor.getComments());
        fuelSensorDashboard.setModifiedDate(fuelSensor.getModifiedDate());
        fuelSensorDashboard.setCreatedDate(fuelSensor.getCreatedDate());
        fuelSensorDashboard.setGpsDate(fuelSensor.getGpsDate());
        fuelSensorDashboard.setVehicleNumber(fuelSensor.getVehicleNumber());
        return fuelSensorDashboard;
    }
}
