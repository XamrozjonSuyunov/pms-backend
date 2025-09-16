package com.pms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessageException extends RuntimeException {
    private final String message;
    private final ErrorCode errorCode;
}
