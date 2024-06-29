package karting.boards.database.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterDriverDto(
        @JsonProperty("firstname") @NotNull @Size(max=30) String firstName,
        @JsonProperty("lastname") @Size(max=30) String lastName,
        @JsonProperty("nickname") @Size(max=30) String nickname,
        @JsonProperty("sex") @NotNull @Size(max=1) String sex,
        @JsonProperty("email") @NotNull @Size(max=30) String email,
        @JsonProperty("password") @NotNull String password,
        @JsonProperty("role") String role) {}
