package mx.tec.lab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -8551776627125404871L;

	public RoleNotFoundException() {
		super();
	}
}
