package id.my.hendisantika.productservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

/**
 * Created by IntelliJ IDEA.
 * Project : product-service
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/7/24
 * Time: 07:35
 * To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException entityError, HttpServletRequest request) {
        int status = HttpStatus.NOT_FOUND.value();
        StandardError error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(status);
        error.setError("Resource not found");
        error.setMessage(entityError.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(error);

    }
}
