package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quicktrack.entity.*;
import quicktrack.repository.AlertRepository;
import quicktrack.repository.DashboardRepository;
import quicktrack.repository.FuelSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        saveVehicleFuelDetails( fuelSensor, dashboard);
        checkForVechicleNoAvailability(fuelSensor, fuelSensorDashboard);
        dashboard.setFuelSensorDashboard(fuelSensorDashboard);
        dashboardRepository.save(dashboard);
    }

    private void saveVehicleFuelDetails(FuelSensor fuelSensor,Dashboard dashboard){
        List<VehicleStats> vehicleStats = dashboard.getVehicleStats();
        VehicleStats vehicleStat = null;
        Double fuelChange ;
        if(vehicleStats != null && vehicleStats.size() > 0) {
            for (int i = 0; i < vehicleStats.size(); i++) {
                if (fuelSensor.getVehicleNumber().equalsIgnoreCase(vehicleStats.get(i).getVehicleNo())) {
                    vehicleStat = vehicleStats.get(i);
                    fuelChange = fuelSensor.getFuelChange();
                    if (fuelChange > 0) {
                        if(vehicleStat.getFuelAdded() != null)
                            vehicleStat.setFuelAdded(vehicleStat.getFuelAdded() + fuelChange);
                        else
                            vehicleStat.setFuelAdded(fuelChange);
                    } else if (fuelChange < 0) {
                        if(vehicleStat.getFuelConsumed() != null)
                            vehicleStat.setFuelConsumed(vehicleStat.getFuelConsumed() + fuelChange);
                        else
                            vehicleStat.setFuelConsumed(fuelChange);
                    }
                    vehicleStats.remove(i);
                }else{
                    vehicleStat = new VehicleStats();
                    vehicleStat.setVehicleNo(fuelSensor.getVehicleNumber());
                    fuelChange = fuelSensor.getFuelChange();
                    if (fuelChange > 0) {
                        vehicleStat.setFuelAdded(fuelChange);
                    } else if (fuelChange < 0) {
                        vehicleStat.setFuelConsumed(fuelChange);
                    }
                }
            }
        }else {
            vehicleStats = new LinkedList<VehicleStats>();
            if (vehicleStat == null) {
                vehicleStat = new VehicleStats();
                vehicleStat.setVehicleNo(fuelSensor.getVehicleNumber());
                fuelChange = fuelSensor.getFuelChange();
                if (fuelChange > 0) {
                    vehicleStat.setFuelAdded(fuelChange);
                } else if (fuelChange < 0) {
                    vehicleStat.setFuelConsumed(fuelChange);
                }
            }
        }
        vehicleStats.add(vehicleStat);
        dashboard.setVehicleStats(vehicleStats);
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
        FuelSensorDashboard fuelSensorDashboardObj = null;
        for(int i=0;i<fuelSensorDashboard.size();i++){
            if(fuelSensorDashboard.get(i).getVehicleNumber().equalsIgnoreCase(fuelSensor.getVehicleNumber())){
                fuelSensorDashboardObj = fuelSensorDashboard.get(i);
                fuelSensorDashboard.remove(i);
            }
        }
        fuelSensorDashboard.add(convertToFuelSensorDashboard(fuelSensor,fuelSensorDashboardObj));
    }


    public FuelSensorDashboard convertToFuelSensorDashboard(FuelSensor fuelSensor,FuelSensorDashboard fuelSensorDashboard){
        if(fuelSensorDashboard == null)
        fuelSensorDashboard = new FuelSensorDashboard();
        FuelDetails fuelDetail = new FuelDetails();
        fuelDetail.setFuelChange(fuelSensor.getFuelChange());
        fuelDetail.setTotalFuel(fuelSensor.getTotalFuel());
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        fuelDetail.setCreatedDate(date);
        fuelDetail.setModifiedDate(date);
        List<FuelDetails> fuelDetails = fuelSensorDashboard.getFuelDetails();
        if(fuelDetails == null){
            fuelDetails = new LinkedList<FuelDetails>();
        }
        if(fuelDetails.size() >= 5){
            fuelDetails.remove(0);
            fuelDetails.add(fuelDetail);
        }else{
            fuelDetails.add(fuelDetail);
        }
        fuelSensorDashboard.setFuelDetails(fuelDetails);
        fuelSensorDashboard.setStatus(fuelSensor.getStatus());
        fuelSensorDashboard.setComments(fuelSensor.getComments());
        fuelSensorDashboard.setModifiedDate(fuelSensor.getModifiedDate());
        fuelSensorDashboard.setCreatedDate(fuelSensor.getCreatedDate());
        fuelSensorDashboard.setGpsDate(fuelSensor.getGpsDate());
        fuelSensorDashboard.setVehicleNumber(fuelSensor.getVehicleNumber());
        return fuelSensorDashboard;
    }
}
