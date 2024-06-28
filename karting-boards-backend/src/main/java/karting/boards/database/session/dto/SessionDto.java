package karting.boards.database.session.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;

public record SessionDto(
    @JsonProperty("id") @NotNull String id,
    @JsonProperty("trackId") @NotNull String trackId,
    @JsonProperty("date") @NotNull Date date,
    @JsonProperty("time") @NotNull Time time) {}
