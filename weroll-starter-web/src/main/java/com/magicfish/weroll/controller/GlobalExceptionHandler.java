package com.magicfish.weroll.controller;

import com.alibaba.fastjson.JSONObject;
import com.magicfish.weroll.consts.ErrorCodes;
import com.magicfish.weroll.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public Object notFoundErrorHandler(HttpServletRequest request, NoHandlerFoundException e) throws Exception {
        return new ResponseEntity("Page Not Found", HttpStatus.valueOf(org.apache.http.HttpStatus.SC_NOT_FOUND));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object generalErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        JSONObject res = new JSONObject();
        res.put("msg", e.getMessage());
        res.put("code", ErrorCodes.SERVER_ERROR);

        ResponseEntity<Object> entity = new ResponseEntity(res, HttpStatus.valueOf(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR));

        return entity;
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Object serviceErrorHandler(HttpServletRequest request, ServiceException e) throws Exception {
        JSONObject res = new JSONObject();
        res.put("msg", e.getMessage());
        res.put("code", e.getCode());

        ResponseEntity<Object> entity = new ResponseEntity(res, HttpStatus.valueOf(e.getStatus()));

        return entity;
    }
}
