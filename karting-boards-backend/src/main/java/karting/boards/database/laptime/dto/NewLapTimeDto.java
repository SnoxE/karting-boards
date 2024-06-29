package karting.boards.database.laptime.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;

public record NewLapTimeDto(
        @JsonProperty("trackId") @NotNull String trackId,
        @JsonProperty("sessionId") @NotNull String sessionId,
        @JsonProperty("driverId") @NotNull String driverId,
        @JsonProperty("time") @NotNull String time) {}
