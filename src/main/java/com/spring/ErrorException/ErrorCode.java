package com.spring.ErrorException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //302 REDIRECTION 리다이렉션
    REDIRECTION(302, "ERR-302", "Redirection"),

    //400 BAD_REQUEST 잘못된 요청
    BAD_REQUEST(400, "ERR-400", "Bad Request"),

    //404 NOT_FOUND 잘못된 리소스 접근
    NOT_FOUND(404, "ERR-404", "Not Found"),

    //409 CONFLICT 중복된 리소스
    ALREADY_CONFLICT(409, "ERR-409", "Server Conflict"),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "ERR-500", "Server Error");

    private final int status;
    private final String errorCode;
    private final String message;

}
