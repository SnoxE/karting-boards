package karting.boards.database.track;

import karting.boards.common.dto.ContentDto;
import karting.boards.database.track.dto.NewTrackDto;
import karting.boards.database.track.dto.TrackDto;
import karting.boards.database.track.sql.TrackSqlService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

  TrackSqlService trackSqlService;

  public TrackService(TrackSqlService trackSqlService) {
    this.trackSqlService = trackSqlService;
  }

  public ContentDto<TrackDto> getTracks() {
    return new ContentDto<>(trackSqlService.getTracks().stream().map(TrackMapper::toTrackDto).toList());
  }

  public TrackDto addTrack(NewTrackDto newTrackDto) {

    String trackId = newTrackDto.name().replaceAll(" ", ".").toLowerCase();
    trackSqlService.addTrack(
        trackId,
        newTrackDto.name(),
        newTrackDto.street(),
        newTrackDto.streetNo(),
        newTrackDto.city(),
        newTrackDto.postCode(),
        newTrackDto.configuration(),
        newTrackDto.length());

    return TrackMapper.toTrackDto(
            trackId,
            newTrackDto.name(),
            newTrackDto.street(),
            newTrackDto.streetNo(),
            newTrackDto.city(),
            newTrackDto.postCode(),
            newTrackDto.configuration(),
            newTrackDto.length());
  }
}
