package karting.boards.common.problem;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.Problem;

import java.util.Map;

public class BadRequestProblem extends AbstractThrowableProblem {
    public BadRequestProblem(String detail) {
        super(Problem.DEFAULT_TYPE, Status.BAD_REQUEST.getReasonPhrase(), Status.BAD_REQUEST, detail);
    }

    public BadRequestProblem(String detail, Map<String, Object> parameters) {
        super(
                Problem.DEFAULT_TYPE,
                Status.BAD_REQUEST.getReasonPhrase(),
                Status.BAD_REQUEST,
                detail,
                null,
                null,
                parameters);
    }
}