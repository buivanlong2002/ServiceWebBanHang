package com.example.demo1.exception;

public enum ErrorCode {
    UNCATE_EXCEPTION("0.999","kb loi gi"),
    USER_EXISTED("0.1","user existed"),
    USER_NOT_EXISTED("0.2","user not existed"),
    UNAUTHENTICATED("0.3","authenticated"),
    ADMIN_EXISTED("0.4","user existed")
    ;
    private String code ;
    private String message ;
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
