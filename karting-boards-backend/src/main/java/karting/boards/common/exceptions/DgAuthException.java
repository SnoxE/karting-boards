package karting.boards.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class DgAuthException extends RuntimeException {

    public DgAuthException(String message){
        super(message);
    }
}
