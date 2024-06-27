package karting.boards.database.user.problem;

import karting.boards.common.problem.BadRequestProblem;

import java.util.Map;

public class EmailAddressNotFoundProblem extends BadRequestProblem {
  public EmailAddressNotFoundProblem() {
    super("Email Address Not Found", Map.of("translationKey", "EMAIL_ADDRESS_NOT_FOUND"));
  }
}
