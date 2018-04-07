package quicktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import quicktrack.entity.FuelSensor;
import quicktrack.entity.WeightSensor;

public interface FuelSensorRepository extends MongoRepository<FuelSensor, String> {
}
