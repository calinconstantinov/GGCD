package ro.ucv.ace.ggcd.model;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Relationship;

@Getter
@Setter
public class Host extends User {

  @Relationship(type = "HOSTING")
  private Event event;
}
