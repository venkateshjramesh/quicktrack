package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quicktrack.entity.*;
import quicktrack.repository.AlertRepository;
import quicktrack.repository.DashboardRepository;
import quicktrack.repository.GpsSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class GpsSensorService {

    @Autowired
    GpsSensorRepository gpsSensorRepository;

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    DashboardRepository dashboardRepository;

    public void saveGpsSensor(GpsSensor gpsSensor){
        //if id, creation date and modified date is present the remove it
        gpsSensor.setId(null);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        gpsSensor.setCreatedDate(date);
        gpsSensor.setModifiedDate(date);
        gpsSensorRepository.save(gpsSensor);
        Alerts alerts = null;
        //add alert if the status is red and the comment is overspeed or sudden break
        if(gpsSensor.getStatus().equalsIgnoreCase("R")){
            alerts = new Alerts(gpsSensor.getVehicleNumber(), gpsSensor.getComments(), "GPS", gpsSensor.getStatus(),gpsSensor.getId(),date, date, gpsSensor.getFleetId());
            alertRepository.save(alerts);
        }
        saveDashboard(gpsSensor,alerts);
    }

    //method to add the gps sensor details to dashboard
    public void saveDashboard(GpsSensor gpsSensor,Alerts alerts){
        Dashboard dashboard = dashboardRepository.findByFleetId(gpsSensor.getFleetId());
        if(dashboard == null){
            dashboard = new Dashboard();
            dashboard.setFleetId(gpsSensor.getFleetId());
        }
        List<GpsSensorDashboard> gpsSensorDashboard = dashboard.getGpsSensorDashboard();
        if(gpsSensorDashboard == null){
            gpsSensorDashboard = new LinkedList<GpsSensorDashboard>();
        }

        saveAlertsInDashboard(alerts, dashboard);
        saveVehicleDistanceDetails(gpsSensor,dashboard);
        checkForVechicleNoAvailability(gpsSensor, gpsSensorDashboard);
        dashboard.setGpsSensorDashboard(gpsSensorDashboard);
        dashboardRepository.save(dashboard);
    }

    private void saveVehicleDistanceDetails(GpsSensor gpsSensor,Dashboard dashboard){
        List<VehicleStats> vehicleStats = dashboard.getVehicleStats();
        VehicleStats vehicleStat = null;
        Double distanceChange ;
        if(vehicleStats != null && vehicleStats.size() > 0) {
            for (int i = 0; i < vehicleStats.size(); i++) {
                if (gpsSensor.getVehicleNumber().equalsIgnoreCase(vehicleStats.get(i).getVehicleNo())) {
                    vehicleStat = vehicleStats.get(i);
                    distanceChange = gpsSensor.getDistanceChanged();
                    if (distanceChange > 0) {
                        if(vehicleStat.getDistanceTravelled() != null)
                            vehicleStat.setDistanceTravelled(vehicleStat.getDistanceTravelled() + distanceChange);
                        else
                            vehicleStat.setDistanceTravelled(distanceChange);
                    }
                    vehicleStats.remove(i);
                }else{
                    vehicleStat = new VehicleStats();
                    vehicleStat.setVehicleNo(gpsSensor.getVehicleNumber());
                    distanceChange = gpsSensor.getDistanceChanged();
                    if (distanceChange > 0) {
                        vehicleStat.setDistanceTravelled(distanceChange);
                    }
                }
            }
        }else {
            vehicleStats = new LinkedList<VehicleStats>();
            if (vehicleStat == null) {
                vehicleStat = new VehicleStats();
                vehicleStat.setVehicleNo(gpsSensor.getVehicleNumber());
                distanceChange = gpsSensor.getDistanceChanged();
                if (distanceChange > 0) {
                    vehicleStat.setDistanceTravelled(distanceChange);
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

    private void checkForVechicleNoAvailability(GpsSensor gpsSensor, List<GpsSensorDashboard> gpsSensorDashboard) {
        GpsSensorDashboard gpsSensorDashboardObj = null;
        for(int i=0;i<gpsSensorDashboard.size();i++){
            if(gpsSensorDashboard.get(i).getVehicleNumber().equalsIgnoreCase(gpsSensor.getVehicleNumber())){
                gpsSensorDashboardObj = gpsSensorDashboard.get(i);
                gpsSensorDashboard.remove(i);
            }
        }
        gpsSensorDashboard.add(convertToFuelSensorDashboard(gpsSensor,gpsSensorDashboardObj));
    }


    public GpsSensorDashboard convertToFuelSensorDashboard(GpsSensor gpsSensor,GpsSensorDashboard gpsSensorDashboard){
        if(gpsSensorDashboard == null)
        gpsSensorDashboard = new GpsSensorDashboard();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        GpsDetails gpsDetails = new GpsDetails();
        gpsDetails.setLatitude(gpsSensor.getLatitude());
        gpsDetails.setLongitude(gpsSensor.getLongitude());
        gpsDetails.setCreatedDate(date);
        gpsDetails.setModifiedDate(date);
        gpsDetails.setComments(gpsSensor.getComments());
        gpsDetails.setStatus(gpsSensor.getStatus());
        List<GpsDetails> gpsDetailsList = gpsSensorDashboard.getGpsDetails();
        if(gpsDetailsList == null){
            gpsDetailsList = new LinkedList<GpsDetails>();
        }
        if(gpsDetailsList.size() >= 5){
            gpsDetailsList.remove(0);
            gpsDetailsList.add(gpsDetails);
        }else{
            gpsDetailsList.add(gpsDetails);
        }
        gpsSensorDashboard.setGpsDetails(gpsDetailsList);
        gpsSensorDashboard.setModifiedDate(gpsSensor.getModifiedDate());
        gpsSensorDashboard.setCreatedDate(gpsSensor.getCreatedDate());
        gpsSensorDashboard.setGpsDate(gpsSensor.getGpsDate());
        gpsSensorDashboard.setVehicleNumber(gpsSensor.getVehicleNumber());
        return gpsSensorDashboard;
    }

}
