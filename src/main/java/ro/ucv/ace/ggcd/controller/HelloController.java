package ro.ucv.ace.ggcd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.ggcd.model.Event;
import ro.ucv.ace.ggcd.model.Host;
import ro.ucv.ace.ggcd.model.User;
import ro.ucv.ace.ggcd.repository.EventRepository;
import ro.ucv.ace.ggcd.repository.HelperNeo4jRepository;
import ro.ucv.ace.ggcd.repository.UserRepository;
import ro.ucv.ace.ggcd.service.ParticipantCreatingService;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

  private final HelperNeo4jRepository helperNeo4jRepository;

  private final EventRepository eventRepository;

  private final UserRepository userRepository;

  private final ParticipantCreatingService participantCreatingService;

  public HelloController(HelperNeo4jRepository helperNeo4jRepository, EventRepository eventRepository, UserRepository userRepository, ParticipantCreatingService participantCreatingService) {
    this.helperNeo4jRepository = helperNeo4jRepository;
    this.eventRepository = eventRepository;
    this.userRepository = userRepository;
    this.participantCreatingService = participantCreatingService;
  }

  @GetMapping
  public String hello() {
    helperNeo4jRepository.deleteGraph();

    Event event = new Event();
    event.setName("Bucharest Graph Celebration Day");
    event.setDescription("We will be hosting a Launch and Learn session and a discussion about how graphs are changing the world");
    eventRepository.save(event);

    Host host = new Host();
    host.setUuid(0);
    host.setName("Calin");
    host.setEvent(event);
    userRepository.save(host);

    //S
    User ssIoan = participantCreatingService.createParticipant("Ioan", event);
    User ssAdrian = userRepository.save(participantCreatingService.createParticipant("Adrian", event).addFriend(ssIoan));
    User ssDarius = userRepository.save(participantCreatingService.createParticipant("Darius", event).addFriend(ssAdrian));
    User ssRadu = userRepository.save(participantCreatingService.createParticipant("Radu", event).addFriend(ssDarius));
    User ssViorel = userRepository.save(participantCreatingService.createParticipant("Viorel", event).addFriend(ssRadu));
    userRepository.save(participantCreatingService.createParticipant("Robert", event).addFriend(ssViorel));

    //A
    User saMaria = participantCreatingService.createParticipant("Maria", event);
    User saEmilian = userRepository.save(participantCreatingService.createParticipant("Emilian", event).addFriend(saMaria));
    User saEduard = userRepository.save(participantCreatingService.createParticipant("Eduard", event).addFriend(saEmilian));
    User saAnaMaria = userRepository.save(participantCreatingService.createParticipant("Ana-Maria", event).addFriend(saEduard, saEmilian));
    userRepository.save(participantCreatingService.createParticipant("Gabriel", event).addFriend(saAnaMaria));

    //L
    User slAdrian = participantCreatingService.createParticipant("Adrian", event);
    User slMonica = userRepository.save(participantCreatingService.createParticipant("Monica", event).addFriend(slAdrian));
    userRepository.save(participantCreatingService.createParticipant("Marius", event).addFriend(slMonica));

    //U
    User suIonut = participantCreatingService.createParticipant("Ionut", event);
    User suAndra = userRepository.save(participantCreatingService.createParticipant("Andra", event).addFriend(suIonut));
    User suOana = userRepository.save(participantCreatingService.createParticipant("Oana", event).addFriend(suAndra));
    userRepository.save(participantCreatingService.createParticipant("Valentin", event).addFriend(suOana));

    //T
    User stMihai = participantCreatingService.createParticipant("Mihai", event);
    User stAlexandru = userRepository.save(participantCreatingService.createParticipant("Alexandru", event).addFriend(stMihai));
    userRepository.save(participantCreatingService.createParticipant("Razvan", event).addFriend(stAlexandru));
    userRepository.save(participantCreatingService.createParticipant("Cristina", event).addFriend(stAlexandru));

    return "Done!";
  }

}
