package karting.boards.database.track;

import karting.boards.database.track.dto.TrackDto;
import karting.boards.database.track.sql.TrackSqlRow;

public final class TrackMapper {
  public static TrackDto toTrackDto(TrackSqlRow trackSqlRow) {
    return new TrackDto(
        trackSqlRow.id(),
        trackSqlRow.name(),
        trackSqlRow.street(),
        trackSqlRow.streetNo(),
        trackSqlRow.city(),
        trackSqlRow.postCode(),
        trackSqlRow.configuration(),
        trackSqlRow.configuration());
  }
}
