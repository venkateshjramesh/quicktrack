package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quicktrack.entity.WeightSensor;
import quicktrack.repository.GpsSensorRepository;
import quicktrack.repository.WeightSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class WeightSensorService {

    @Autowired
    WeightSensorRepository weightSensorRepository;

    public void saveWeightSensor(WeightSensor weightSensor){
        //if id, creation date and modified date is present the remove it
        weightSensor.setId(null);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        weightSensor.setCreatedDate(date);
        weightSensor.setModifiedDate(date);
        weightSensorRepository.save(weightSensor);
    }

}
