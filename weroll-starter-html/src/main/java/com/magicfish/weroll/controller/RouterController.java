package com.magicfish.weroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RouterController {

    @Autowired
    @Qualifier("router")
    protected IHttpProcessor routerProcessor;

    @GetMapping("/")
    public Object renderRootPage(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return renderPage(model, request, response);
    }

    @GetMapping("/**")
    public Object renderPage(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return routerProcessor.process(model, request, response);
    }

}
