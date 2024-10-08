package karting.boards.database.session.sql;

import karting.boards.common.problem.InternalServerErrorProblem;
import karting.boards.database.session.dto.SessionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static karting.boards.common.resource.ResourceManager.readSqlQuery;

@Service
public class SessionSqlService {

  private final Logger log = LoggerFactory.getLogger(SessionSqlService.class);

  private static final String INSERT_INTO_SESSION =
      readSqlQuery("sql/session/insert_into_session.sql");
  private static final String SELECT_SESSIONS_BY_TRACK_ID =
          readSqlQuery("sql/session/select_sessions_by_track_id.sql");

  NamedParameterJdbcOperations jdbcOperations;

  public SessionSqlService(NamedParameterJdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  public void addSession(String id, String trackId, Date date, Time time) {
    MapSqlParameterSource parameterSource =
        insertSessionParameterSource(new MapSqlParameterSource(), id, trackId, date, time);

    try {
      jdbcOperations.update(INSERT_INTO_SESSION, parameterSource);
    } catch (DataAccessException e) {
      log.error("Unable to add session due to unexpected error message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    }
  }

  public List<SessionSqlRow> getSessionsByTrackId(String trackId) {
    MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("trackId", trackId);
    try {
      return jdbcOperations.query(SELECT_SESSIONS_BY_TRACK_ID, parameters, DataClassRowMapper.newInstance(SessionSqlRow.class));
    } catch (DataAccessException e) {
      log.error("Unable to get session list due to unexpected error message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    }
  }

  private MapSqlParameterSource insertSessionParameterSource(
      MapSqlParameterSource parameterSource, String id, String trackId, Date date, Time time) {
    parameterSource.addValue("id", id);
    parameterSource.addValue("track_id", trackId);
    parameterSource.addValue("date", date);
    parameterSource.addValue("time", time);
    return parameterSource;
  }
}
