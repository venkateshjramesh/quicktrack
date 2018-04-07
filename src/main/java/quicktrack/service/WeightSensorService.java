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
        //add alert if the status is red and the comment is overspeed or sudden break
        if(weightSensor.getStatus().equalsIgnoreCase("R")){
            Alerts alerts = new Alerts(weightSensor.getVehicleNumber(), weightSensor.getComments(), "WEIGHT", weightSensor.getStatus(),weightSensor.getId(),date, date, weightSensor.getFleetId());
            alertRepository.save(alerts);
        }
        saveDashboard(weightSensor);
    }

    //method to add the weight sensor details to dashboard
    public void saveDashboard(WeightSensor weightSensor){
        Dashboard dashboard = dashboardRepository.findByFleetId(weightSensor.getFleetId());
        if(dashboard == null){
            dashboard = new Dashboard();
            dashboard.setFleetId(weightSensor.getFleetId());
        }
        List<WeightSensorDashboard> weightSensorDashboard = dashboard.getWeightSensorDashboard();
        if(weightSensorDashboard == null){
            weightSensorDashboard = new LinkedList<WeightSensorDashboard>();
        }

        checkForVechicleNoAvailability(weightSensor, weightSensorDashboard);
        dashboard.setWeightSensorDashboard(weightSensorDashboard);
        dashboardRepository.save(dashboard);
    }

    private void checkForVechicleNoAvailability(WeightSensor weightSensor, List<WeightSensorDashboard> weightSensorDashboard) {
        for(int i=0;i<weightSensorDashboard.size();i++){
            if(weightSensorDashboard.get(i).getVehicleNumber().equalsIgnoreCase(weightSensor.getVehicleNumber())){
                weightSensorDashboard.remove(i);
            }
        }
        weightSensorDashboard.add(convertToFuelSensorDashboard(weightSensor));
    }


    public WeightSensorDashboard convertToFuelSensorDashboard(WeightSensor weightSensor){
        WeightSensorDashboard weightSensorDashboard = new WeightSensorDashboard();
        weightSensorDashboard.setTotalWeight(weightSensor.getTotalWeight());
        weightSensorDashboard.setWeightChange(weightSensor.getWeightChange());
        weightSensorDashboard.setStatus(weightSensor.getStatus());
        weightSensorDashboard.setComments(weightSensor.getComments());
        weightSensorDashboard.setModifiedDate(weightSensor.getModifiedDate());
        weightSensorDashboard.setCreatedDate(weightSensor.getCreatedDate());
        weightSensorDashboard.setGpsDate(weightSensor.getGpsDate());
        weightSensorDashboard.setVehicleNumber(weightSensor.getVehicleNumber());
        return weightSensorDashboard;
    }

}
