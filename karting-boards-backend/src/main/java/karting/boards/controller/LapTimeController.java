package karting.boards.controller;

import jakarta.validation.Valid;
import karting.boards.database.laptime.LapTimeService;
import karting.boards.database.laptime.dto.NewLapTimeDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/laptime")
public class LapTimeController {

    LapTimeService lapTimeService;

    public LapTimeController(LapTimeService lapTimeService) {
        this.lapTimeService = lapTimeService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void addLapTime(@RequestBody @Valid NewLapTimeDto newLapTimeDto) {
        lapTimeService.addLapTime(newLapTimeDto);
    }
}
