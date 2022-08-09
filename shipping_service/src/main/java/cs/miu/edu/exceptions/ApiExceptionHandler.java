package cs.miu.edu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Prabhat Gyawali
 * @created 24-Jun-2021 - 2:24 PM
 * @project lab3restapi
 */
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<?> handleResourceNotExist(Exception ex){
        return new ResponseEntity<>(new CustomErrorMessage(ex.getMessage()), HttpStatus.OK);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleNotValidException(MethodArgumentNotValidException ex){
        Map<String, Object> responseData = new HashMap<>();
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(x->x.getField(), y->y.getDefaultMessage()));
        responseData.put("status", HttpStatus.NOT_ACCEPTABLE);
        responseData.put("missing", fieldErrors);
        return new ResponseEntity<>(responseData, HttpStatus.NOT_ACCEPTABLE);
    }
}