package karting.boards.database.user.sql;

import static karting.boards.common.resource.ResourceManager.readSqlQuery;

import java.sql.*;

import karting.boards.common.problem.DgAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DriverSqlService {

  private static final Logger log = LoggerFactory.getLogger(DriverSqlService.class);

  private static final String INSERT_INTO_DRIVER = readSqlQuery("sql/user/insert_into_driver.sql");
  private static final String SELECT_COUNT_BY_EMAIL =
      readSqlQuery("sql/user/select_user_count_by_email.sql");
  private static final String SELECT_USER_BY_ID = readSqlQuery("sql/user/select_driver_by_id.sql");
  private static final String SELECT_USER_BY_EMAIL =
      readSqlQuery("sql/user/select_user_by_email.sql");
  private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD =
      readSqlQuery("sql/user/select_user_by_email_and_password.sql");
  private static final String SELECT_PASSWORD_BY_ID =
      readSqlQuery("sql/user/select_user_password_by_id.sql");
  private static final String UPDATE_PASSWORD_BY_ID =
      readSqlQuery("sql/user/update_user_password.sql");

  private final NamedParameterJdbcOperations jdbcOperations;
  private final PasswordEncoder passwordEncoder;

  public DriverSqlService(
      NamedParameterJdbcOperations jdbcOperations, PasswordEncoder passwordEncoder) {
    this.jdbcOperations = jdbcOperations;
    this.passwordEncoder = passwordEncoder;
  }

  private PreparedStatement preparedInsertIntoUsersQuery(
      Connection connection,
      String firstName,
      String lastName,
      String email,
      String password,
      String role)
      throws SQLException {
    PreparedStatement statement = connection.prepareStatement(INSERT_INTO_DRIVER);

    int parameterIndex = 0;

    statement.setString(++parameterIndex, firstName);
    if (lastName != null) statement.setString(++parameterIndex, lastName);
    else statement.setNull(++parameterIndex, JDBCType.VARCHAR.getVendorTypeNumber());
    statement.setString(++parameterIndex, email);
    statement.setString(++parameterIndex, passwordEncoder.encode(password));
    if (role != null) statement.setString(++parameterIndex, role.toUpperCase());
    else statement.setString(++parameterIndex, "USER");

    return statement;
  }

  private PreparedStatement updatePasswordPreparedStatement(
      Connection connection, int userId, String password) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD_BY_ID);

    int parameterIndex = 0;

    statement.setString(++parameterIndex, password);
    statement.setInt(++parameterIndex, userId);

    return statement;
  }

  public Integer createUser(
      String firstName,
      String lastName,
      String nickname,
      String sex,
      String email,
      String password,
      String role) {

    MapSqlParameterSource parameters =
        insertUserParameterSource(
            new MapSqlParameterSource(), firstName, lastName, nickname, sex, email, password, role);
    try {
      return jdbcOperations.update(INSERT_INTO_DRIVER, parameters);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  public MapSqlParameterSource insertUserParameterSource(
      MapSqlParameterSource parameterSource,
      String firstName,
      String lastName,
      String nickname,
      String sex,
      String email,
      String password,
      String role) {
    parameterSource.addValue("first_name", firstName);
    parameterSource.addValue("last_name", lastName);
    parameterSource.addValue("nickname", nickname);
    parameterSource.addValue("sex", sex);
    parameterSource.addValue("email", email);
    parameterSource.addValue("password", password);
    parameterSource.addValue("role", role);
    return parameterSource;
  }

  //    public DriverDto getUserByEmailAndPassword(String email, String password) throws
  // DgAuthException {
  //        return jdbcOperations.queryForObject(
  //                SELECT_USER_BY_EMAIL_AND_PASSWORD,
  //                userRowMapper,
  //                DriverDto.class,
  //                email,
  //                password);
  //    }

  public Integer getCountByEmail(String email) {
    MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("email", email);
    return jdbcOperations.queryForObject(SELECT_COUNT_BY_EMAIL, parameters, Integer.class);
  }

  //    public void changePassword(int userId, String password) throws ResponseStatusException {
  //        try {
  //            jdbcOperations.update(con -> updatePasswordPreparedStatement(
  //                    con,
  //                    userId,
  //                    password));
  //        } catch (Exception e) {
  //            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
  //        }
  //    }

  //    public DriverDto getUserById(int id) {
  //        return jdbcOperations.queryForObject(SELECT_USER_BY_ID, userRowMapper, id);
  //    }

  //    public DriverDto getUserByEmail(String email) {
  //        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("email", email);
  //        return jdbcOperations.queryForObject(SELECT_USER_BY_EMAIL, parameters, new
  // RowMapper(DriverDto.class) {
  //        });
  //    }

  //    public String getPasswordByUserId(int userId) {
  //        return jdbcOperations.queryForObject(
  //                SELECT_PASSWORD_BY_ID,
  //                String.class,
  //                userId);
  //    }

  //    private final RowMapper<DriverDto> driverRowMapper = ((resultSet, rowNum) -> {
  //        return new DriverDto(
  //                resultSet.getInt(DriverSqlRow.ID),
  //                resultSet.getString(DriverSqlRow.FIRST_NAME),
  //                resultSet.getString(DriverSqlRow.LAST_NAME),
  //                resultSet.getString(DriverSqlRow.EMAIL),
  //                resultSet.getString(DriverSqlRow.PASSWORD),
  //                resultSet.getString(DriverSqlRow.ROLE));
  //    });
}
