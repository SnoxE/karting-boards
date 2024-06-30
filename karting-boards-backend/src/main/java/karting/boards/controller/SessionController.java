package karting.boards.controller;

import jakarta.validation.Valid;
import karting.boards.common.dto.ContentDto;
import karting.boards.database.session.SessionService;
import karting.boards.database.session.dto.NewSessionDto;
import karting.boards.database.session.dto.SessionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/session")
public class SessionController {

  SessionService sessionService;

  public SessionController(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  public SessionDto addSession(@RequestBody @Valid NewSessionDto newSessionDto) {
    return sessionService.addSession(newSessionDto);
  }

  @GetMapping(path = "/{trackId}", produces = APPLICATION_JSON_VALUE)
  public ContentDto<SessionDto> getSession(@PathVariable String trackId) {
    return sessionService.getSessionsByTrackId(trackId);
  }
}
