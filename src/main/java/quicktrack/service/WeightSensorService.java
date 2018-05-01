package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quicktrack.entity.*;
import quicktrack.repository.AlertRepository;
import quicktrack.repository.DashboardRepository;
import quicktrack.repository.GpsSensorRepository;
import quicktrack.repository.WeightSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class WeightSensorService {

    @Autowired
    WeightSensorRepository weightSensorRepository;

    @Autowired
    AlertRepository alertRepository;

    @Autowired
    DashboardRepository dashboardRepository;

    public void saveWeightSensor(WeightSensor weightSensor){
        //if id, creation date and modified date is present the remove it
        weightSensor.setId(null);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        weightSensor.setCreatedDate(date);
        weightSensor.setModifiedDate(date);
        weightSensorRepository.save(weightSensor);
        Alerts alerts = null;
        //add alert if the status is red and the comment is overspeed or sudden break
        if(weightSensor.getStatus().equalsIgnoreCase("R")){
            alerts = new Alerts(weightSensor.getVehicleNumber(), weightSensor.getComments(), "WEIGHT", weightSensor.getStatus(),weightSensor.getId(),date, date, weightSensor.getFleetId());
            alertRepository.save(alerts);
        }
        saveDashboard(weightSensor,alerts);
    }

    //method to add the weight sensor details to dashboard
    public void saveDashboard(WeightSensor weightSensor,Alerts alerts){
        Dashboard dashboard = dashboardRepository.findByFleetId(weightSensor.getFleetId());
        if(dashboard == null){
            dashboard = new Dashboard();
            dashboard.setFleetId(weightSensor.getFleetId());
        }
        List<WeightSensorDashboard> weightSensorDashboard = dashboard.getWeightSensorDashboard();
        if(weightSensorDashboard == null){
            weightSensorDashboard = new LinkedList<WeightSensorDashboard>();
        }

        saveAlertsInDashboard(alerts, dashboard);

        checkForVechicleNoAvailability(weightSensor, weightSensorDashboard);
        dashboard.setWeightSensorDashboard(weightSensorDashboard);
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

    private void checkForVechicleNoAvailability(WeightSensor weightSensor, List<WeightSensorDashboard> weightSensorDashboard) {
        WeightSensorDashboard weightSensorDashboardObj = null;
        for(int i=0;i<weightSensorDashboard.size();i++){
            if(weightSensorDashboard.get(i).getVehicleNumber().equalsIgnoreCase(weightSensor.getVehicleNumber())){
                weightSensorDashboardObj = weightSensorDashboard.get(i);
                weightSensorDashboard.remove(i);
            }
        }
        weightSensorDashboard.add(convertToFuelSensorDashboard(weightSensor,weightSensorDashboardObj));
    }


    public WeightSensorDashboard convertToFuelSensorDashboard(WeightSensor weightSensor,WeightSensorDashboard weightSensorDashboard){
        if(weightSensorDashboard == null)
        weightSensorDashboard = new WeightSensorDashboard();
        /*weightSensorDashboard.setTotalWeight(weightSensor.getTotalWeight());
        weightSensorDashboard.setWeightChange(weightSensor.getWeightChange());*/
        WeightDetails weightDetails = new WeightDetails();
        weightDetails.setTotalWeight(weightSensor.getTotalWeight());
        weightDetails.setWeightChange(weightSensor.getWeightChange());
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        weightDetails.setCreatedDate(date);
        weightDetails.setModifiedDate(date);
        weightDetails.setComments(weightSensor.getComments());
        weightDetails.setStatus(weightSensor.getStatus());
        List<WeightDetails> weightDetailsList = weightSensorDashboard.getWeightDetails();
        if(weightDetailsList == null){
            weightDetailsList = new LinkedList<WeightDetails>();
        }
        if(weightDetailsList.size() >= 5){
            weightDetailsList.remove(0);
            weightDetailsList.add(weightDetails);
        }else{
            weightDetailsList.add(weightDetails);
        }
        weightSensorDashboard.setWeightDetails(weightDetailsList);
        weightSensorDashboard.setModifiedDate(weightSensor.getModifiedDate());
        weightSensorDashboard.setCreatedDate(weightSensor.getCreatedDate());
        weightSensorDashboard.setGpsDate(weightSensor.getGpsDate());
        weightSensorDashboard.setVehicleNumber(weightSensor.getVehicleNumber());
        return weightSensorDashboard;
    }

}
