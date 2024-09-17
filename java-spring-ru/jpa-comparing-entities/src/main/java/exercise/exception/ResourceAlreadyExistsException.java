package exercise.exception;

import org.springframework.http.ResponseEntity;

// BEGIN
public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
// END
