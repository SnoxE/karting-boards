package karting.boards.database.laptime.sql;

import karting.boards.common.problem.InternalServerErrorProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import static karting.boards.common.resource.ResourceManager.readSqlQuery;

@Service
public class LapTimeSqlService {

    private static final Logger log = LoggerFactory.getLogger(LapTimeSqlService.class);

    private static final String SELECT_BEST_LAP_TIMES_BY_TRACK_ID =
            readSqlQuery("sql/laptime/select_best_lap_times.sql");
    private static final String INSERT_INTO_LAP_TIME = readSqlQuery("sql/laptime/insert_into_laptime.sql");

    NamedParameterJdbcOperations jdbcOperations;

    public LapTimeSqlService(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public List<LapTimeSqlRow> getLeaderboard(String trackId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("trackId", trackId);

        try {
            return jdbcOperations.query(
                    SELECT_BEST_LAP_TIMES_BY_TRACK_ID,
                    parameters,
                    DataClassRowMapper.newInstance(LapTimeSqlRow.class));
        } catch (DataAccessException e) {
            throw new InternalServerErrorProblem();
        }
    }

    public void addLapTime(
            String lapTimeId,
            String counterLapTimeId,
            String trackId,
            String sessionId,
            String driverId,
            String time) {
        MapSqlParameterSource parameters =
                insertLapTimeParameterSource(
                        new MapSqlParameterSource(), lapTimeId, counterLapTimeId, trackId, sessionId, driverId, time);

        try {
            jdbcOperations.update(INSERT_INTO_LAP_TIME, parameters);
        } catch (DataAccessException e) {
            log.error("Unable to add lap time due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MapSqlParameterSource insertLapTimeParameterSource(
            MapSqlParameterSource parameterSource,
            String laptimeId,
            String counterLapTimeId,
            String trackId,
            String sessionId,
            String driverId,
            String time) {
        parameterSource.addValue("id", laptimeId);
        parameterSource.addValue("counter_id", counterLapTimeId);
        parameterSource.addValue("track_id", trackId);
        parameterSource.addValue("session_id", sessionId);
        parameterSource.addValue("driver_id", driverId);
        parameterSource.addValue("time", time);
        return parameterSource;
    }
}
