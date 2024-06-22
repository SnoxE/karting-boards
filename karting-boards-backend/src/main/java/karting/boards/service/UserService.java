package karting.boards.service;

import karting.boards.common.exceptions.DgAuthException;
import karting.boards.database.user.dto.UserDto;
import karting.boards.database.user.sql.UserSqlService;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private final UserSqlService userSqlService;

    public UserService(UserSqlService userSqlService) {
        this.userSqlService = userSqlService;
    }

    public UserDto registerUser(
            String firstName,
            String lastName,
            String email,
            String password,
            String role) throws DgAuthException {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if (email != null) {
            email = email.toLowerCase();
            if (!pattern.matcher(email).matches())
                throw new DgAuthException("Niepoprawny format adresu email");
        }

        Integer count = userSqlService.getCountByEmail(email);
        if (count > 0) throw new DgAuthException("Adres email jest już w użyciu");

        userSqlService.createUser(firstName, lastName, email, password, role);

        return userSqlService.getUserByEmail(email);
    }

    public UserDto getUserByEmail(String email) {
        return userSqlService.getUserByEmail(email);
    }

    public void changePassword(int userId, String password) {
        userSqlService.changePassword(userId, password);
    }

    public String getPasswordByUserId(int userId) {
        return userSqlService.getPasswordByUserId(userId);
    }

    public int getUserCountByEmail(String email)
    {
        return userSqlService.getCountByEmail(email);
    }
}
