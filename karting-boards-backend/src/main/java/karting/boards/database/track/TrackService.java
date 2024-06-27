package karting.boards.database.track;

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

  public List<TrackDto> getTracks() {
    return trackSqlService.getTracks().stream().map(TrackMapper::toTrackDto).toList();
  }
}
