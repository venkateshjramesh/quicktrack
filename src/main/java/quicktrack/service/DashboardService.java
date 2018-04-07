package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quicktrack.entity.Alerts;
import quicktrack.entity.Dashboard;
import quicktrack.entity.WeightSensor;
import quicktrack.entity.WeightSensorDashboard;
import quicktrack.repository.AlertRepository;
import quicktrack.repository.DashboardRepository;
import quicktrack.repository.WeightSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    DashboardRepository dashboardRepository;

    public Dashboard getDashboardByFleetId(String fleetId){
        return dashboardRepository.findByFleetId(fleetId);
    }

}
