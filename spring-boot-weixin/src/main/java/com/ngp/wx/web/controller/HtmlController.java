package com.ngp.wx.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wxh")
public class HtmlController {

    @RequestMapping("/login")
    public String loginHtml(){
        return "test";
    }
}
