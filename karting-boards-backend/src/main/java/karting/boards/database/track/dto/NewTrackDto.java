package karting.boards.database.track.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record NewTrackDto(
    @JsonProperty("name") @NotNull String name,
    @JsonProperty("street") @NotNull String street,
    @JsonProperty("streetNo") @NotNull String streetNo,
    @JsonProperty("city") @NotNull String city,
    @JsonProperty("postCode") @NotNull String postCode,
    @JsonProperty("configuration") Integer configuration,
    @JsonProperty("length") Integer length) {}
