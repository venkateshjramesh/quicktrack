package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quicktrack.entity.FuelSensor;
import quicktrack.entity.WeightSensor;
import quicktrack.repository.FuelSensorRepository;
import quicktrack.repository.WeightSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FuelSensorService {

    @Autowired
    FuelSensorRepository  fuelSensorRepository;

    public void saveFuelSensor(FuelSensor fuelSensor){
        //if id, creation date and modified date is present the remove it
        fuelSensor.setId(null);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        fuelSensor.setCreatedDate(date);
        fuelSensor.setModifiedDate(date);
        fuelSensorRepository.save(fuelSensor);
    }

}
