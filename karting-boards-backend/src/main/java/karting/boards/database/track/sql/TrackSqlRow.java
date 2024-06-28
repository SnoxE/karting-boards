package karting.boards.database.track.sql;

public record TrackSqlRow(
    String id,
    String name,
    String street,
    Integer streetNo,
    String city,
    String postCode,
    Integer configuration,
    Integer length) {}
