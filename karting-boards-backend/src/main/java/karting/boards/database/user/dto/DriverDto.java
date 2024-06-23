package karting.boards.database.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DriverDto(
        @JsonProperty("id") int id,
        @JsonProperty("first_name")  String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("nickname") String nickname,
        @JsonProperty("M") String sex,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("role") String role) {}
