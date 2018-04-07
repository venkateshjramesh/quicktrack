package quicktrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import quicktrack.entity.TyreChangeSensor;
import quicktrack.entity.TyreSensor;
import quicktrack.entity.WeightSensor;
import quicktrack.service.TyreSensorService;
import quicktrack.service.WeightSensorService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tyreSensor")
public class TyreController {

    @Autowired
    TyreSensorService tyreSensorService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveFeed(HttpServletRequest request,
                           @RequestBody TyreSensor tyreSensor) {
        tyreSensorService.saveTyreSensor(tyreSensor);
        return "success";
    }
}
