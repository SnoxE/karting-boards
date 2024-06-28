package karting.boards.database.track.sql;

import karting.boards.common.problem.DuplicateKeyErrorProblem;
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
public class TrackSqlService {

  private static final Logger log = LoggerFactory.getLogger(TrackSqlService.class);

  private static final String SELECT_TRACKS = readSqlQuery("sql/track/select_tracks.sql");
  private static final String INSERT_INTO_TRACK = readSqlQuery("sql/track/insert_into_track.sql");

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

  public void addTrack(
      String trackId,
      String trackName,
      String street,
      String streetNo,
      String city,
      String postCode,
      Integer configuration,
      Integer length) {
    MapSqlParameterSource parameterSource =
        insertTrackParameterSource(
            new MapSqlParameterSource(),
            trackId,
            trackName,
            street,
            streetNo,
            city,
            postCode,
            configuration,
            length);

    try {
      jdbcOperations.update(INSERT_INTO_TRACK, parameterSource);
    } catch (DuplicateKeyErrorProblem e) {
      log.error("Unable to add track due to duplicate key error message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    } catch (DataAccessException e) {
      log.error("Unable to add track due to an unexpected error message={}", e.getMessage(), e);
      throw new InternalServerErrorProblem();
    }
  }

  private MapSqlParameterSource insertTrackParameterSource(
      MapSqlParameterSource parameterSource,
      String trackId,
      String trackName,
      String street,
      String streetNo,
      String city,
      String postCode,
      Integer configuration,
      Integer length) {
    parameterSource.addValue("id", trackId);
    parameterSource.addValue("name", trackName);
    parameterSource.addValue("street", street);
    parameterSource.addValue("street_no", streetNo);
    parameterSource.addValue("city", city);
    parameterSource.addValue("post_code", postCode);
    parameterSource.addValue("configuration", configuration);
    parameterSource.addValue("length", length);
    return parameterSource;
  }
}
