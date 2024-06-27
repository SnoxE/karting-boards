package karting.boards.controller;

import karting.boards.database.track.TrackService;
import karting.boards.database.track.dto.TrackDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/tracks")
public class TrackController {

    public final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<TrackDto> getTracks() {
        return trackService.getTracks();
    }
}
