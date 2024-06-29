package karting.boards.database.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DriverDto(
        @JsonProperty("id") String id,
        @JsonProperty("firstname")  String firstName,
        @JsonProperty("lastname") String lastName,
        @JsonProperty("nickname") String nickname,
        @JsonProperty("sex") String sex,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("role") String role) {}
