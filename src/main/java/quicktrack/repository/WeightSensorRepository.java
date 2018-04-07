package quicktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import quicktrack.entity.WeightSensor;

public interface WeightSensorRepository extends MongoRepository<WeightSensor, String> {
}
