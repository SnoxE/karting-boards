package karting.boards.database.user.sql.model;

public record DriverSqlRow(
        String id,
        String firstName,
        String lastName,
        String nickname,
        String sex,
        String email,
        String password,
        String role) {
}
