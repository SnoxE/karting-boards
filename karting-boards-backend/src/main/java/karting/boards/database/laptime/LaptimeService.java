package karting.boards.database.laptime;

import karting.boards.database.laptime.dto.LaptimeDto;
import karting.boards.database.laptime.sql.LaptimeSqlService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptimeService {

  LaptimeSqlService laptimeSqlService;

  public LaptimeService(LaptimeSqlService laptimeSqlService) {
    this.laptimeSqlService = laptimeSqlService;
  }

  public List<LaptimeDto> getBestLapTimesForTrack(String trackId) {
    return laptimeSqlService.getBestLapTimes(trackId).stream()
        .map(LaptimeMapper::toLaptimeDto)
        .toList();
  }
}
