package quicktrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import quicktrack.entity.GpsSensor;
import quicktrack.entity.WeightSensor;
import quicktrack.service.GpsSensorService;
import quicktrack.service.WeightSensorService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/weightSensor")
public class WeightController {

    @Autowired
    WeightSensorService weightSensorService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveFeed(HttpServletRequest request,
                           @RequestBody WeightSensor weightSensor) {
        weightSensorService.saveWeightSensor(weightSensor);
        return "success";
    }
}
