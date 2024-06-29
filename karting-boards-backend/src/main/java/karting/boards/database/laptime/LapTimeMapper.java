package karting.boards.database.laptime;

import karting.boards.database.laptime.dto.LapTimeDto;
import karting.boards.database.laptime.sql.LapTimeSqlRow;

public class LapTimeMapper {

  public static LapTimeDto toLaptimeDto(LapTimeSqlRow laptimeSqlRow) {
    return new LapTimeDto(
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
