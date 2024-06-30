package karting.boards.controller;

import jakarta.validation.Valid;
import karting.boards.database.user.dto.DriverDto;
import karting.boards.database.user.dto.RegisterDriverDto;
import karting.boards.database.user.DriverService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private final DriverService driverService;

    public UserController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE,
            path = "/register")
    @ResponseStatus(CREATED)
    public RegisterDriverDto registerUser(@RequestBody @Valid RegisterDriverDto driverDto) {
        return driverService.registerDriver(driverDto);
    }

    @GetMapping("/user")
    public DriverDto getLoggedDriver(Principal principal) {
        return driverService.getDriverByEmail(principal);
    }
}
