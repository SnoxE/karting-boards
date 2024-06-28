package karting.boards.database.laptime.sql;

import java.sql.Date;
import java.sql.Time;

public record LaptimeSqlRow(
    String laptimeId,
    Integer laptimeMinutes,
    Integer laptimeSeconds,
    Integer laptimeMilliseconds,
    String trackId,
    String trackName,
    String sessionId,
    Date sessionDate,
    Time sessionTime,
    String driverId,
    String driverFirstName,
    String driverLastName,
    String driverNickname) {}
