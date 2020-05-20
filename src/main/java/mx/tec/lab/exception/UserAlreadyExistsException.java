package mx.tec.lab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 2634928523065616174L;

	public UserAlreadyExistsException() {
    	super();
    }
}
