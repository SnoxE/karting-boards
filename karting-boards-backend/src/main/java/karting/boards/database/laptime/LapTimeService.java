package karting.boards.database.laptime;

import karting.boards.common.dto.ContentDto;
import karting.boards.database.laptime.dto.LapTimeDto;
import karting.boards.database.laptime.dto.NewLapTimeDto;
import karting.boards.database.laptime.sql.LapTimeSqlService;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class LapTimeService {

    LapTimeSqlService laptimeSqlService;

    public LapTimeService(LapTimeSqlService laptimeSqlService) {
        this.laptimeSqlService = laptimeSqlService;
    }

    public ContentDto<LapTimeDto> getLeaderboard(String trackId) {
        return new ContentDto<>(laptimeSqlService.getLeaderboard(trackId).stream()
                .map(LapTimeMapper::toLaptimeDto)
                .toList());
    }

    public void addLapTime(NewLapTimeDto newLapTimeDto) {

        String lapTimeId = buildLapTimeId(newLapTimeDto);
        String counterLapTimeId = buildCounterLapTimeId(newLapTimeDto);

        laptimeSqlService.addLapTime(
                lapTimeId,
                counterLapTimeId,
                newLapTimeDto.trackId(),
                newLapTimeDto.sessionId(),
                newLapTimeDto.driverId(),
                newLapTimeDto.time());
    }

    private String buildLapTimeId(NewLapTimeDto newLapTimeDto) {
        return String.format("%s.%s", newLapTimeDto.sessionId(), newLapTimeDto.driverId());
    }

    private String buildCounterLapTimeId(NewLapTimeDto newLapTimeDto) {
        return String.format("%s%s.%s%s", "%", newLapTimeDto.sessionId(), newLapTimeDto.driverId(), "%");
    }

    private Duration buildLapTimeAsDuration(NewLapTimeDto newLapTimeDto) {
        String[] parts = newLapTimeDto.time().split(":");
        int minutes = Integer.parseInt(parts[0]);
        String[] secondsParts = parts[1].split("\\.");
        int seconds = Integer.parseInt(secondsParts[0]);
        int milliseconds = Integer.parseInt(secondsParts[1]);

        return Duration.ofMinutes(minutes)
                .plusSeconds(seconds)
                .plusMillis(milliseconds);
    }
}
