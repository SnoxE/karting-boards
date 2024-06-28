package karting.boards.database.session;

import karting.boards.database.session.dto.NewSessionDto;
import karting.boards.database.session.dto.SessionDto;
import karting.boards.database.session.sql.SessionSqlService;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

  SessionSqlService sessionSqlService;

  public SessionService(SessionSqlService sessionSqlService) {
    this.sessionSqlService = sessionSqlService;
  }

  public SessionDto addSession(NewSessionDto newSessionDto) {

    String dateString = newSessionDto.date().toString();
    String timeString = newSessionDto.time().toString().replaceAll(":", "_");
    String sessionId = String.format("%s.%s.%s", newSessionDto.trackId(), dateString, timeString);

    sessionSqlService.addSession(
        sessionId, newSessionDto.trackId(), newSessionDto.date(), newSessionDto.time());

    return SessionMapper.toSessionDto(
        sessionId, newSessionDto.trackId(), newSessionDto.date(), newSessionDto.time());
  }
}
