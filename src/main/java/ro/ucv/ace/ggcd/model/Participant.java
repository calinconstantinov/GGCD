package ro.ucv.ace.ggcd.model;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Relationship;

@Getter
@Setter
public class Participant extends User {

  @Relationship(type = "ATTENDING", direction = Relationship.INCOMING)
  private Event event;

}
