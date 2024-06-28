package karting.boards.database.laptime;

import karting.boards.database.laptime.dto.LaptimeDto;
import karting.boards.database.laptime.sql.LaptimeSqlRow;

public class LaptimeMapper {

  public static LaptimeDto toLaptimeDto(LaptimeSqlRow laptimeSqlRow) {
    return new LaptimeDto(
        laptimeSqlRow.laptimeId(),
        laptimeSqlRow.laptimeMinutes(),
        laptimeSqlRow.laptimeSeconds(),
        laptimeSqlRow.laptimeMilliseconds(),
        laptimeSqlRow.trackId(),
        laptimeSqlRow.trackName(),
        laptimeSqlRow.sessionId(),
        laptimeSqlRow.sessionDate(),
        laptimeSqlRow.sessionTime(),
        laptimeSqlRow.driverId(),
        laptimeSqlRow.driverFirstName(),
        laptimeSqlRow.driverLastName(),
        laptimeSqlRow.driverNickname());
  }
}
