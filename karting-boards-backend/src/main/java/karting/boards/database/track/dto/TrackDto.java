package karting.boards.database.track.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TrackDto(
    @JsonProperty("id") String id,
    @JsonProperty("name") String name,
    @JsonProperty("street") String street,
    @JsonProperty("streetNo") String streetNo,
    @JsonProperty("city") String city,
    @JsonProperty("postCode") String postCode,
    @JsonProperty("configuration") Integer configuration,
    @JsonProperty("length") Integer length) {}
