package quicktrack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import quicktrack.entity.Privilege;
import quicktrack.entity.Role;

public interface PrivilegeRepository extends MongoRepository<Privilege, String> {
}
