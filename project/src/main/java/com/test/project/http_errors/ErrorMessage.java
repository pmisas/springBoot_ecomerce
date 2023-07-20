package com.test.project.http_errors;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
class ErrorMessage {

    private final boolean error;
    private final String message;
    private final Integer code;

    ErrorMessage(Exception exception, Integer code) {
        this.error = true;
        this.message = exception.getMessage();
        this.code = code;
    }

}
