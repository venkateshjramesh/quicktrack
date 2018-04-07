package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quicktrack.entity.*;
import quicktrack.repository.AlertRepository;
import quicktrack.repository.DashboardRepository;
import quicktrack.repository.TyreSensorRepository;
import quicktrack.repository.WeightSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class TyreSensorService {

    @Autowired
    TyreSensorRepository tyreSensorRepository;

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    DashboardRepository dashboardRepository;

    public void saveTyreSensor(TyreSensor tyreSensor){
        //if id, creation date and modified date is present the remove it
        tyreSensor.setId(null);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        tyreSensor.setCreatedDate(date);
        tyreSensor.setModifiedDate(date);
        tyreSensorRepository.save(tyreSensor);
        Alerts alerts = null;
        //add alert if the status is red and the comment is overspeed or sudden break
        if(tyreSensor.getStatus().equalsIgnoreCase("R")){
            alerts = new Alerts(tyreSensor.getVehicleNumber(), tyreSensor.getComments(), "TYRE", tyreSensor.getStatus(),tyreSensor.getId(),date, date, tyreSensor.getFleetId());
            alertRepository.save(alerts);
        }
        saveDashboard(tyreSensor,alerts);
    }

    //method to add the weight sensor details to dashboard
    public void saveDashboard(TyreSensor tyreSensor,Alerts alerts){
        Dashboard dashboard = dashboardRepository.findByFleetId(tyreSensor.getFleetId());
        if(dashboard == null){
            dashboard = new Dashboard();
            dashboard.setFleetId(tyreSensor.getFleetId());
        }
        List<TyreSensorDashboard> tyreSensorDashboard = dashboard.getTyreSensorDashboard();
        if(tyreSensorDashboard == null){
            tyreSensorDashboard = new LinkedList<TyreSensorDashboard>();
        }

        saveAlertsInDashboard(alerts, dashboard);

        checkForVechicleNoAvailability(tyreSensor, tyreSensorDashboard);
        dashboard.setTyreSensorDashboard(tyreSensorDashboard);
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
    private void checkForVechicleNoAvailability(TyreSensor tyreSensor, List<TyreSensorDashboard> tyreSensorDashboard) {
        for(int i=0;i<tyreSensorDashboard.size();i++){
            if(tyreSensorDashboard.get(i).getVehicleNumber().equalsIgnoreCase(tyreSensor.getVehicleNumber())){
                tyreSensorDashboard.remove(i);
            }
        }
        tyreSensorDashboard.add(convertToFuelSensorDashboard(tyreSensor));
    }


    public TyreSensorDashboard convertToFuelSensorDashboard(TyreSensor tyreSensor){
        TyreSensorDashboard tyreSensorDashboard = new TyreSensorDashboard();
        tyreSensorDashboard.setTyrePressureSensor(tyreSensor.getTyrePressureSensor());
        tyreSensorDashboard.setTyreChangeSensor(tyreSensor.getTyreChangeSensor());
        tyreSensorDashboard.setStatus(tyreSensor.getStatus());
        tyreSensorDashboard.setComments(tyreSensor.getComments());
        tyreSensorDashboard.setModifiedDate(tyreSensor.getModifiedDate());
        tyreSensorDashboard.setCreatedDate(tyreSensor.getCreatedDate());
        tyreSensorDashboard.setGpsDate(tyreSensor.getGpsDate());
        tyreSensorDashboard.setVehicleNumber(tyreSensor.getVehicleNumber());
        return tyreSensorDashboard;
    }

}
