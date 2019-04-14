package ro.ucv.ace.ggcd.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NodeEntity
public class User {

  @Setter(AccessLevel.NONE)
  private Long id;

  @EqualsAndHashCode.Include
  @Index(unique = true)
  private Integer uuid;

  @Index
  private String name;

  @Relationship(type = "FRIENDS", direction = Relationship.UNDIRECTED)
  private Set<User> friends = new LinkedHashSet<>();

  public User addFriend(User... oneOrMoreFriends) {
    friends.addAll(Arrays.asList(oneOrMoreFriends));
    Arrays.stream(oneOrMoreFriends).forEach(u -> u.getFriends().add(this));
    return this;
  }
}
