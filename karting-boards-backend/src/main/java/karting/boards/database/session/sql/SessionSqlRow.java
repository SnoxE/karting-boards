package karting.boards.database.session.sql;

import java.sql.Date;
import java.sql.Time;

public record SessionSqlRow(String id, String trackId, Date date, Time time) {}
