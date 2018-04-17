package quicktrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import quicktrack.entity.Fleet;
import quicktrack.entity.FuelSensor;
import quicktrack.entity.Vehicle;
import quicktrack.service.FleetService;
import quicktrack.service.FuelSensorService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/fleet")
public class FleetController {

    @Autowired
    FleetService fleetService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveFleet(HttpServletRequest request,
                            @RequestBody Fleet fleet) {
        fleetService.saveFleet(fleet);
        return "success";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Fleet getFleet(HttpServletRequest request,
                           @RequestParam String fleetId) {
        return fleetService.getFleet(fleetId);
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.POST)
    public String saveFleet(HttpServletRequest request,
                            @RequestBody Vehicle vehicle,@RequestParam String fleetId) {
        fleetService.updateVehicle(vehicle,fleetId);
        return "success";
    }
}
