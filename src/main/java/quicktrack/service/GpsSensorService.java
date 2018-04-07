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

        checkForVechicleNoAvailability(gpsSensor, gpsSensorDashboard);
        dashboard.setGpsSensorDashboard(gpsSensorDashboard);
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

    private void checkForVechicleNoAvailability(GpsSensor gpsSensor, List<GpsSensorDashboard> gpsSensorDashboard) {
        for(int i=0;i<gpsSensorDashboard.size();i++){
            if(gpsSensorDashboard.get(i).getVehicleNumber().equalsIgnoreCase(gpsSensor.getVehicleNumber())){
                gpsSensorDashboard.remove(i);
            }
        }
        gpsSensorDashboard.add(convertToFuelSensorDashboard(gpsSensor));
    }


    public GpsSensorDashboard convertToFuelSensorDashboard(GpsSensor gpsSensor){
        GpsSensorDashboard gpsSensorDashboard = new GpsSensorDashboard();
        gpsSensorDashboard.setLatitude(gpsSensor.getLatitude());
        gpsSensorDashboard.setLatitude(gpsSensor.getLongitude());
        gpsSensorDashboard.setStatus(gpsSensor.getStatus());
        gpsSensorDashboard.setComments(gpsSensor.getComments());
        gpsSensorDashboard.setModifiedDate(gpsSensor.getModifiedDate());
        gpsSensorDashboard.setCreatedDate(gpsSensor.getCreatedDate());
        gpsSensorDashboard.setGpsDate(gpsSensor.getGpsDate());
        gpsSensorDashboard.setVehicleNumber(gpsSensor.getVehicleNumber());
        return gpsSensorDashboard;
    }

}
