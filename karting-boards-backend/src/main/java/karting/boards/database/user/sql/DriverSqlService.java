package karting.boards.database.user.sql;

import static karting.boards.common.resource.ResourceManager.readSqlQuery;

import java.sql.*;

import karting.boards.database.user.dto.DriverDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DriverSqlService {

    private static final Logger log = LoggerFactory.getLogger(DriverSqlService.class);

    private static final String INSERT_INTO_DRIVER = readSqlQuery("sql/driver/insert_into_driver.sql");
    private static final String SELECT_COUNT_BY_EMAIL =
            readSqlQuery("sql/driver/select_user_count_by_email.sql");
    private static final String SELECT_USER_BY_ID = readSqlQuery("sql/driver/select_driver_by_id.sql");
    private static final String SELECT_USER_BY_EMAIL =
            readSqlQuery("sql/driver/select_driver_by_email.sql");

    private final NamedParameterJdbcOperations jdbcOperations;
    private final PasswordEncoder passwordEncoder;

    public DriverSqlService(
            NamedParameterJdbcOperations jdbcOperations, PasswordEncoder passwordEncoder) {
        this.jdbcOperations = jdbcOperations;
        this.passwordEncoder = passwordEncoder;
    }

    public Integer createUser(
            String id,
            String firstName,
            String lastName,
            String nickname,
            String sex,
            String email,
            String password,
            String role) {

        MapSqlParameterSource parameters =
                insertUserParameterSource(
                        new MapSqlParameterSource(),
                        id,
                        firstName,
                        lastName,
                        nickname,
                        sex,
                        email,
                        passwordEncoder.encode(password),
                        role);
        try {
            return jdbcOperations.update(INSERT_INTO_DRIVER, parameters);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public MapSqlParameterSource insertUserParameterSource(
            MapSqlParameterSource parameterSource,
            String id,
            String firstName,
            String lastName,
            String nickname,
            String sex,
            String email,
            String password,
            String role) {
        parameterSource.addValue("id", id);
        parameterSource.addValue("first_name", firstName);
        parameterSource.addValue("last_name", lastName);
        parameterSource.addValue("nickname", nickname);
        parameterSource.addValue("sex", sex);
        parameterSource.addValue("email", email);
        parameterSource.addValue("password", password);
        parameterSource.addValue("role", role);
        return parameterSource;
    }

    public Integer getCountByEmail(String email) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("email", email);
        return jdbcOperations.queryForObject(SELECT_COUNT_BY_EMAIL, parameters, Integer.class);
    }

    public DriverDto getDriverByEmail(String email) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("email", email);
        return jdbcOperations.queryForObject(SELECT_USER_BY_EMAIL, parameters,
                DataClassRowMapper.newInstance(DriverDto.class));
    }
}
