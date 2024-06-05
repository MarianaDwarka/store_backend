package com.marianadwarka.exception;

import com.marianadwarka.dto.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleAllExceptions(Exception ex, WebRequest req){

        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(new GenericResponse<>(500, "BACKEND-ERROR", Arrays.asList(errorResponse)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){

        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(new GenericResponse<>(404, "error", Arrays.asList(errorResponse)), HttpStatus.NOT_FOUND);
    }*/

    //Desde Spring Boot 3+
    @ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Model Not Found Exception");
        pd.setType(URI.create(req.getContextPath()));
        pd.setProperty("code", 404);
        pd.setProperty("message", "not-found");
        return pd;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<CustomErrorResponse>> handleBadRequest(MethodArgumentNotValidException ex, WebRequest req){

        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));

        return new ResponseEntity<>(new GenericResponse<>(400, "error", Arrays.asList(errorResponse)), HttpStatus.BAD_REQUEST);
    }
}
