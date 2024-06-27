package karting.boards.common.problem;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.Map;

public class ConflictProblem extends AbstractThrowableProblem {

    public ConflictProblem(String detail, Map<String, Object> parameters) {
        super(
                Problem.DEFAULT_TYPE,
                Status.CONFLICT.getReasonPhrase(),
                Status.CONFLICT,
                detail,
                null,
                null,
                parameters);
    }
}
