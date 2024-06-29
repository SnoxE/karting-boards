package karting.boards.controller;

import jakarta.validation.Valid;
import karting.boards.common.dto.ContentDto;
import karting.boards.database.laptime.LapTimeService;
import karting.boards.database.laptime.dto.LapTimeDto;
import karting.boards.database.track.TrackService;
import karting.boards.database.track.dto.NewTrackDto;
import karting.boards.database.track.dto.TrackDto;
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
@RequestMapping(path = "/api/tracks")
public class TrackController {

  TrackService trackService;
  LapTimeService laptimeService;

  public TrackController(TrackService trackService, LapTimeService laptimeService) {
    this.trackService = trackService;
    this.laptimeService = laptimeService;
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public ContentDto<TrackDto> getTracks() {
    return trackService.getTracks();
  }

  @GetMapping(path = "/{trackId}/leaderboard", produces = APPLICATION_JSON_VALUE)
  public ContentDto<LapTimeDto> getLeaderboard(@PathVariable String trackId) {
    return laptimeService.getLeaderboard(trackId);
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  public TrackDto addTrack(@RequestBody @Valid NewTrackDto newTrackDto) {
    return trackService.addTrack(newTrackDto);
  }
}
