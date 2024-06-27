package karting.boards.database.user.problem;

import karting.boards.common.problem.BadRequestProblem;

import java.util.Map;

public class InvalidEmailAddressProblem extends BadRequestProblem {
    public InvalidEmailAddressProblem() {
    super("Invalid Email Address", Map.of("translationKey", "INVALID_EMAIL_ADDRESS"));
    }
}
