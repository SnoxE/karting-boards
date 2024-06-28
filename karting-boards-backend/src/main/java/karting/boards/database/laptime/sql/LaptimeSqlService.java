package karting.boards.database.laptime.sql;

import karting.boards.common.problem.InternalServerErrorProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.util.List;

import static karting.boards.common.resource.ResourceManager.readSqlQuery;

@Service
public class LaptimeSqlService {

  private static final Logger log = LoggerFactory.getLogger(LaptimeSqlService.class);

  private static final String SELECT_BEST_LAP_TIMES_BY_TRACK_ID =
      readSqlQuery("sql/laptime/select_best_lap_times.sql");

  NamedParameterJdbcOperations jdbcOperations;

  public LaptimeSqlService(NamedParameterJdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  public List<LaptimeSqlRow> getBestLapTimes(String trackId) {
    MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("trackId", trackId);

    try {
      return jdbcOperations.query(
          SELECT_BEST_LAP_TIMES_BY_TRACK_ID,
          parameters,
          DataClassRowMapper.newInstance(LaptimeSqlRow.class));
    } catch (DataAccessException e) {
      throw new InternalServerErrorProblem();
    }
  }
}
