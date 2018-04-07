package quicktrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import quicktrack.entity.FuelSensor;
import quicktrack.entity.WeightSensor;
import quicktrack.service.FuelSensorService;
import quicktrack.service.WeightSensorService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/fuelSensor")
public class FuelController {

    @Autowired
    FuelSensorService fuelSensorService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveFeed(HttpServletRequest request,
                           @RequestBody FuelSensor fuelSensor) {
        fuelSensorService.saveFuelSensor(fuelSensor);
        return "success";
    }
}
