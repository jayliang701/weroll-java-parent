package com.magicfish.weroll.exception;

import com.alibaba.fastjson.annotation.JSONField;
import com.magicfish.weroll.consts.ErrorCodes;
import org.apache.http.HttpStatus;

public class ServiceException extends Exception {
    
    private static final long serialVersionUID = -3637587713268375921L;

    @JSONField(serialize = false)
    private transient int status = HttpStatus.SC_INTERNAL_SERVER_ERROR;

    @JSONField(serialize = false)
    public int getStatus() {
        return status;
    }

    private int code = ErrorCodes.SERVER_ERROR;

    public int getCode() {
        return code;
    }

    public ServiceException() {
        super("unknown");
        this.code = ErrorCodes.SERVER_ERROR;
    }

    public ServiceException(String message) {
        super(message);
        this.code = ErrorCodes.SERVER_ERROR;
        this.status = HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }

    public ServiceException(String message, int code) {
        super(message);
        this.code = code;
        if (code == ErrorCodes.REQUEST_PARAMS_INVALID) {
            this.status = HttpStatus.SC_BAD_REQUEST;
        } else if (code == ErrorCodes.NO_PERMISSION) {
            this.status = HttpStatus.SC_FORBIDDEN;
        }
    }

    public ServiceException(String message, int code, int status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public static ServiceException wrapper(Exception e) {
        return new ServiceException(e.getMessage());
    }

    public void printErrorMessage() {
        System.err.format("[%s] %s", getCode(), getMessage());
    }

    public void printErrorMessage(String prefix) {
        System.err.format("%s [%s] %s", prefix, getCode(), getMessage());
    }
}
