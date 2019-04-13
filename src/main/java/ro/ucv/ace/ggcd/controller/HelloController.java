package ro.ucv.ace.ggcd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.ggcd.service.UserCreatingService;
import ro.ucv.ace.ggcd.repository.HelperNeo4jRepository;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

  private final HelperNeo4jRepository helperNeo4jRepository;

  private final UserCreatingService userCreatingService;

  public HelloController(HelperNeo4jRepository helperNeo4jRepository, UserCreatingService userCreatingService) {
    this.helperNeo4jRepository = helperNeo4jRepository;
    this.userCreatingService = userCreatingService;
  }

  @GetMapping
  public String hello() {
    helperNeo4jRepository.deleteGraph();

    return "Done!";
  }

}
