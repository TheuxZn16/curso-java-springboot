package TheuxZn16.com.github.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedMathOpperationException extends RuntimeException {
  public UnsuportedMathOpperationException(String message) {
    super(message);
  }
}
