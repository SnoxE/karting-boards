package karting.boards.database.session;

import karting.boards.database.session.dto.SessionDto;
import karting.boards.database.session.sql.SessionSqlRow;

import java.sql.Date;
import java.sql.Time;

public class SessionMapper {
  public static SessionDto toSessionDto(SessionSqlRow sessionSqlRow) {
    return new SessionDto(
        sessionSqlRow.id(), sessionSqlRow.trackId(), sessionSqlRow.date(), sessionSqlRow.time());
  }

  public static SessionDto toSessionDto(String id, String trackId, Date date, Time time) {
    return new SessionDto(id, trackId, date, time);
  }
}
