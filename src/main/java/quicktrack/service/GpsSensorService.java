package quicktrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quicktrack.entity.GpsSensor;
import quicktrack.repository.GpsSensorRepository;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GpsSensorService {

    @Autowired
    GpsSensorRepository gpsSensorRepository;

    public void saveGpsSensor(GpsSensor gpsSensor){
        //if id, creation date and modified date is present the remove it
        gpsSensor.setId(null);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = formatter.format(new Date());
        gpsSensor.setCreatedDate(date);
        gpsSensor.setModifiedDate(date);
        gpsSensorRepository.save(gpsSensor);
    }

}
