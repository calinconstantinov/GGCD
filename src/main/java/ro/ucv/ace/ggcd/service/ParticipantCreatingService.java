package ro.ucv.ace.ggcd.service;

import org.springframework.stereotype.Service;
import ro.ucv.ace.ggcd.model.Event;
import ro.ucv.ace.ggcd.model.Participant;
import ro.ucv.ace.ggcd.repository.UserRepository;

@Service
public class ParticipantCreatingService {

  private static int numberOfUsersCreated = 0;

  private final UserRepository userRepository;

  public ParticipantCreatingService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Participant createParticipant(String name, Event event) {

    Participant participant = new Participant();
    participant.setUuid(++numberOfUsersCreated);
    participant.setEvent(event);
    participant.setName(name);
    return userRepository.save(participant);
  }
}
