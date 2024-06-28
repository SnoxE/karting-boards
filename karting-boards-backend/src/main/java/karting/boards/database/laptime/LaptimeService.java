package karting.boards.database.laptime;

import karting.boards.common.dto.ContentDto;
import karting.boards.database.laptime.dto.LaptimeDto;
import karting.boards.database.laptime.sql.LaptimeSqlService;
import org.springframework.stereotype.Service;

@Service
public class LaptimeService {

  LaptimeSqlService laptimeSqlService;

  public LaptimeService(LaptimeSqlService laptimeSqlService) {
    this.laptimeSqlService = laptimeSqlService;
  }

  public ContentDto<LaptimeDto> getLeaderboard(String trackId) {
    return new ContentDto<>(laptimeSqlService.getLeaderboard(trackId).stream()
        .map(LaptimeMapper::toLaptimeDto)
        .toList());
  }
}
