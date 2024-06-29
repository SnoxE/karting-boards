package karting.boards.database.laptime.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;

public record LapTimeDto(
        @JsonProperty("laptimeId") String laptimeId,
        @JsonProperty("laptimeMinutes") Integer laptimeMinutes,
        @JsonProperty("laptimeSeconds") Integer laptimeSeconds,
        @JsonProperty("laptimeMilliseconds") Integer laptimeMilliseconds,
        @JsonProperty("trackId") String trackId,
        @JsonProperty("trackName") String trackName,
        @JsonProperty("sessionId") String sessionId,
        @JsonProperty("sessionDate") Date sessionDate,
        @JsonProperty("sessionTime") Time sessionTime,
        @JsonProperty("driverId") String driverId,
        @JsonProperty("driverFirstName") String driverFirstName,
        @JsonProperty("driverLastName") String driverLastName,
        @JsonProperty("driverNickname") String driverNickname
) {}
