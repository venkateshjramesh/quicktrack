package quicktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import quicktrack.entity.Fleet;

public interface FleetRepository extends MongoRepository<Fleet, String> {
    public Fleet findByFleetId(String fleetId);
}
