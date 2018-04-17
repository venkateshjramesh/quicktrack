package quicktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import quicktrack.entity.Alerts;
import quicktrack.entity.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    public Role findByfleetIdAndName(String fleetId,String name);
}
