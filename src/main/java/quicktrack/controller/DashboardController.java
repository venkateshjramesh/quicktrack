package quicktrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quicktrack.entity.Dashboard;
import quicktrack.entity.WeightSensor;
import quicktrack.service.DashboardService;
import quicktrack.service.WeightSensorService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/dashboardSensor")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @RequestMapping(value = "/getDashboard", method = RequestMethod.GET)
    public Dashboard saveFeed(HttpServletRequest request,
                              @RequestParam String fleetId) {
        return dashboardService.getDashboardByFleetId(fleetId.trim());
    }
}
