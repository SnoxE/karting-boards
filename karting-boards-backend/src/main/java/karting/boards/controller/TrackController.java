package karting.boards.controller;

import karting.boards.database.laptime.LaptimeService;
import karting.boards.database.laptime.dto.LaptimeDto;
import karting.boards.database.track.TrackService;
import karting.boards.database.track.dto.TrackDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/tracks")
public class TrackController {

  TrackService trackService;
  LaptimeService laptimeService;

  public TrackController(TrackService trackService, LaptimeService laptimeService) {
    this.trackService = trackService;
    this.laptimeService = laptimeService;
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<TrackDto> getTracks() {
    return trackService.getTracks();
  }

  @GetMapping(path = "/{trackId}/leaderboard", produces = APPLICATION_JSON_VALUE)
  public List<LaptimeDto> getBestLapTimes(@PathVariable String trackId) {
    return laptimeService.getBestLapTimesForTrack(trackId);
  }
}
