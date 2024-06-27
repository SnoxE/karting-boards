package karting.boards.common.problem;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.Problem;

import java.util.Map;

public class NotFoundProblem extends AbstractThrowableProblem {
  public NotFoundProblem(String detail) {
    super(Problem.DEFAULT_TYPE, Status.NOT_FOUND.getReasonPhrase(), Status.NOT_FOUND, detail);
  }

  public NotFoundProblem(String detail, Map<String, Object> parameters) {
    super(
        Problem.DEFAULT_TYPE,
        Status.NOT_FOUND.getReasonPhrase(),
        Status.NOT_FOUND,
        detail,
        null,
        null,
        parameters);
  }
}
