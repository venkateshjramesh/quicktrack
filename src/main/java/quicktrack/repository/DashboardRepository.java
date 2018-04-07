package quicktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import quicktrack.entity.Dashboard;
import quicktrack.entity.WeightSensor;

public interface DashboardRepository extends MongoRepository<Dashboard, String> {
    public Dashboard findByFleetId(String fleetId);
}
