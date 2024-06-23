package karting.boards.database.user.sql.model;

public record DriverSqlRow(
        int id,
        String firstName,
        String lastName,
        String email,
        String password,
        String role) {}
