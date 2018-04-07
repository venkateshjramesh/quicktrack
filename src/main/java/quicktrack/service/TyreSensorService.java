package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quicktrack.entity.TyreSensor;
import quicktrack.entity.WeightSensor;
import quicktrack.repository.TyreSensorRepository;
import quicktrack.repository.WeightSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TyreSensorService {

    @Autowired
    TyreSensorRepository tyreSensorRepository;

    public void saveTyreSensor(TyreSensor tyreSensor){
        //if id, creation date and modified date is present the remove it
        tyreSensor.setId(null);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        tyreSensor.setCreatedDate(date);
        tyreSensor.setModifiedDate(date);
        tyreSensorRepository.save(tyreSensor);
    }

}
