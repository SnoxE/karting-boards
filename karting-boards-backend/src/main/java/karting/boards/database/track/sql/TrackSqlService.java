package karting.boards.database.track.sql;

import karting.boards.common.problem.InternalServerErrorProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.util.List;

import static karting.boards.common.resource.ResourceManager.readSqlQuery;

@Service
public class TrackSqlService {

  private static final Logger log = LoggerFactory.getLogger(TrackSqlService.class);

  private static final String SELECT_TRACKS = readSqlQuery("sql/track/select_tracks.sql");

  NamedParameterJdbcOperations jdbcOperations;

  TrackSqlService(NamedParameterJdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  public List<TrackSqlRow> getTracks() {
    try {
      return jdbcOperations.query(SELECT_TRACKS, DataClassRowMapper.newInstance(TrackSqlRow.class));
    } catch (DataAccessException e) {
      log.error(
          "Unable to retrieve tracks due to an unexpected error message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    }
  }
}
