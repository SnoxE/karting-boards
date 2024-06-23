package karting.boards.service;

import karting.boards.common.exceptions.DgAuthException;
import karting.boards.database.user.dto.DriverDto;
import karting.boards.database.user.sql.DriverSqlService;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private final DriverSqlService driverSqlService;

    public UserService(DriverSqlService driverSqlService) {
        this.driverSqlService = driverSqlService;
    }

    public void registerUser(
            String firstName,
            String lastName,
            String nickname,
            String sex,
            String email,
            String password,
            String role) throws DgAuthException {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) {
            email = email.toLowerCase();
            if (!pattern.matcher(email).matches())
                throw new DgAuthException("Niepoprawny format adresu email");
        }

//        Integer count = driverSqlService.getCountByEmail(email);
//        if (count > 0) throw new DgAuthException("Adres email jest już w użyciu");

        driverSqlService.createUser(firstName, lastName, nickname, sex, email, password, role);

//        return driverSqlService.getUserByEmail(email);
    }

//    public DriverDto getUserByEmail(String email) {
//        return driverSqlService.getUserByEmail(email);
//    }
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
