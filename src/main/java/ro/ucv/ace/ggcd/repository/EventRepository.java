package ro.ucv.ace.ggcd.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ro.ucv.ace.ggcd.model.Event;

public interface EventRepository extends Neo4jRepository<Event, Long> {

}
