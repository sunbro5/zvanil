package cz.jm.coder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DefaultException extends RuntimeException {

    public DefaultException(String message) {
        super(message);
    }
}
