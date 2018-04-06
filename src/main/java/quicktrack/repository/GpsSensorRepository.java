package quicktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import quicktrack.entity.GpsSensor;

public interface GpsSensorRepository extends MongoRepository<GpsSensor, String> {
}
