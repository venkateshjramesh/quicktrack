package quicktrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import quicktrack.entity.GpsSensor;
import quicktrack.service.GpsSensorService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/gpsSensor")
public class GpsController {

    @Autowired
    GpsSensorService gpsSensorService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveFeed(HttpServletRequest request,
                           @RequestBody GpsSensor gpsSensor) {
        gpsSensorService.saveGpsSensor(gpsSensor);
        return "success";
    }
}
