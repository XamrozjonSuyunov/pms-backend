package com.pms.exception;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.Timestamp;

import static com.pms.exception.ErrorCode.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorMessageException.class)
    public ResponseEntity<ErrorMessage> handleCustomException(ErrorMessageException ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.valueOf(ex.getErrorCode().getStatusCode());
        ErrorMessage errorMessage = new ErrorMessage(new Timestamp(System.currentTimeMillis()), ex.getErrorCode().name(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(httpStatus).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST) // 400
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(new Timestamp(System.currentTimeMillis()), BadRequest.name(), ex.getBindingResult().getFieldError().getDefaultMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST) // 400
    public ResponseEntity<ErrorMessage> badRequestException(BadRequestException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(new Timestamp(System.currentTimeMillis()), BadRequest.name(), ex.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(NOT_FOUND) // 404
    public ResponseEntity<?> dataFormatException(WebRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorMessage(new Timestamp(System.currentTimeMillis()), NotFound.name(), "Not found", request.getDescription(false)));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(NOT_FOUND) // 404
    public ResponseEntity<?> noResourceFoundException(WebRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorMessage(new Timestamp(System.currentTimeMillis()), NotFound.name(), "Not found", request.getDescription(false)));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR) // 500
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex, WebRequest request) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Timestamp(System.currentTimeMillis()), InternalServerError.name(), ex.getMessage(), request.getDescription(false)));
    }
}
