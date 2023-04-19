package com.spring.ErrorException;

import lombok.Data;

@Data
public class ErrorResponse {

    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }
}
