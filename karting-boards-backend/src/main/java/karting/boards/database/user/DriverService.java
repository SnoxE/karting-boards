package karting.boards.database.user;

import karting.boards.common.ShortUuidGenerator;
import karting.boards.common.problem.DuplicateKeyErrorProblem;
import karting.boards.database.user.dto.DriverDto;
import karting.boards.database.user.dto.RegisterDriverDto;
import karting.boards.database.user.problem.EmailAddressNotFoundProblem;
import karting.boards.database.user.problem.InvalidEmailAddressProblem;
import karting.boards.database.user.sql.DriverSqlService;

import java.security.Principal;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DriverService {

    private final DriverSqlService driverSqlService;

    public DriverService(DriverSqlService driverSqlService) {
        this.driverSqlService = driverSqlService;
    }

    public RegisterDriverDto registerDriver(RegisterDriverDto driverDto) {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (driverDto.email() != null) {
            if (!pattern.matcher(driverDto.email().toLowerCase()).matches()) throw new InvalidEmailAddressProblem();
        }

        Integer count = driverSqlService.getCountByEmail(driverDto.email().toLowerCase());
        if (count > 0) throw new DuplicateKeyErrorProblem();

        String id = String.format("%s.%s.%s", driverDto.firstName(), ShortUuidGenerator.generateRandomString(4), "id");

        driverSqlService.createUser(
                id,
                driverDto.firstName(),
                driverDto.lastName(),
                driverDto.nickname(),
                driverDto.sex(),
                driverDto.email(),
                driverDto.password(),
                driverDto.role());

        return driverDto;
    }

    public DriverDto getDriverByEmail(Principal principal) {
        try {
            return driverSqlService.getDriverByEmail(principal.getName());
        } catch (NullPointerException e) {
            throw new EmailAddressNotFoundProblem();
        }
    }
    //
    //    public void changePassword(int userId, String password) {
    //        driverSqlService.changePassword(userId, password);
    //    }
    //
    //    public String getPasswordByUserId(int userId) {
    //        return driverSqlService.getPasswordByUserId(userId);
    //    }
    //
    //    public int getUserCountByEmail(String email)
    //    {
    //        return driverSqlService.getCountByEmail(email);
    //    }

}
