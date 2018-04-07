package quicktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import quicktrack.entity.Alerts;
import quicktrack.entity.FuelSensor;

public interface AlertRepository extends MongoRepository<Alerts, String> {
}
