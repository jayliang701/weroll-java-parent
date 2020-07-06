package com.magicfish.weroll.controller;

import com.magicfish.weroll.model.APIPostBody;
import com.magicfish.weroll.net.APIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HttpController {

    @Autowired
    @Qualifier("api")
    protected IHttpProcessor apiProcessor;

    @ResponseBody
    @PostMapping(value ="/api", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE })
    public Object api(@RequestBody APIPostBody body, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        APIAction request = new APIAction(servletRequest, servletResponse, body);
        return apiProcessor.process(request);
    }

}
