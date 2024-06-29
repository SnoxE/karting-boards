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

//  private final PasswordEncoder passwordEncoder;

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

    //    @PostMapping("/{userId}/add-car")
    //    public ResponseEntity<ResponseDto> addCar(
    //            @PathVariable("userId") String userId,
    //            @RequestBody CarDto carDto) {
    //        carService.addCar(
    //                Integer.parseInt(userId),
    //                carDto.make(),
    //                carDto.model(),
    //                carDto.productionYear(),
    //                carDto.size(),
    //                carDto.colour());
    //
    //        return ResponseEntity.ok().build();
    //    }
    //
    //    @PutMapping("/{userId}/change-password")
    //    public ResponseEntity<ResponseDto> changePassword(
    //            @PathVariable("userId") String userId,
    //            @RequestBody PasswordDto passwordDto) {
    //        String oldPassword = userService.getPasswordByUserId(Integer.parseInt(userId));
    //        if (!Objects.equals(oldPassword, passwordEncoder.encode(passwordDto.oldPassword()))) {
    //            return ResponseEntity.badRequest().build();
    //        } else if (Objects.equals(oldPassword,
    // passwordEncoder.encode(passwordDto.newPassword()))) {
    //            return ResponseEntity.badRequest().build();
    //        } else {
    //            userService.changePassword(
    //                Integer.parseInt(userId),
    //                passwordEncoder.encode(passwordDto.newPassword()));
    //        }
    //
    //        return ResponseEntity.ok().build();
    //    }

    //
    //    @GetMapping("/email")
    //    public int getUserCountByEmail(@RequestParam("email") String email) {
    //        return userService.getUserCountByEmail(email);
    //    }

    //    @GetMapping("/{userId}/cars")
    //    public ContentDto<CarDto> getUserCars(@PathVariable("userId") String userId) {
    //        return carService.getUserCars(userId);
    //    }

    //    @DeleteMapping("/{userId}/delete-car/{carId}")
    //    public ResponseEntity<ResponseDto> deleteCar(
    //            @PathVariable("userId") String userId,
    //            @PathVariable("carId") String carId) {
    //        carService.deleteCar(Integer.parseInt(userId), Integer.parseInt(carId));
    //        return ResponseEntity.ok().build();
    //    }
    //
    //    @GetMapping("/{userId}/reservations")
    //    public ContentDto<ReservationDto> getUserReservations(@PathVariable("userId") String userId)
    // {
    //        return reservationService.getUserReservations(userId);
    //    }
}
