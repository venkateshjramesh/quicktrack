package quicktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import quicktrack.entity.TyreSensor;
import quicktrack.entity.WeightSensor;

public interface TyreSensorRepository extends MongoRepository<TyreSensor, String> {
}
