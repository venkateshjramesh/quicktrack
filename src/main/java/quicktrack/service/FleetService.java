package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quicktrack.entity.Dashboard;
import quicktrack.entity.Fleet;
import quicktrack.entity.Vehicle;
import quicktrack.repository.DashboardRepository;
import quicktrack.repository.FleetRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class FleetService {

    @Autowired
    FleetRepository fleetRepository;

    public String saveFleet(Fleet fleet){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        fleet.setId(null);
        fleet.setCreatedDate(date);
        fleet.setModifiedDate(date);
        fleetRepository.save(fleet);
        return "success";
    }

    public String updateVehicle(Vehicle vehicle,String fleetId){
        boolean available = false;
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        Fleet fleet = fleetRepository.findByFleetId(fleetId.trim());
        List<Vehicle> vehicles = fleet.getVehicles();
        vehicle.setModifiedDate(date);
        if(vehicles == null) {
            vehicles = new LinkedList<Vehicle>();
        }
        if(vehicles != null && vehicles.size() > 0){
            for(int i=0;i<vehicles.size();i++){
                if(vehicles.get(i).getNo().trim().equalsIgnoreCase(vehicle.getNo().trim())){
                    available = true;
                    vehicle.setCreatedDate(vehicles.get(i).getCreatedDate());
                    vehicles.remove(i);
                    vehicles.add(vehicle);
                }
            }
            if(!available) {
                vehicle.setCreatedDate(date);
                vehicles.add(vehicle);
            }
        }else{
            vehicle.setCreatedDate(date);
            vehicles.add(vehicle);
        }
        fleet.setVehicles(vehicles);
        fleetRepository.save(fleet);
        return "success";
    }



    public Fleet getFleet(String fleetId){
        return fleetRepository.findByFleetId(fleetId);
    }

}
