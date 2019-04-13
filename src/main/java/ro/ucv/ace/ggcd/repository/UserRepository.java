package ro.ucv.ace.ggcd.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.ggcd.model.User;

public interface UserRepository extends Neo4jRepository<User, Long> {

}
