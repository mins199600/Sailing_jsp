package com.boot.sailing_jsp.v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v2")
public class HomeConV2 {

    @GetMapping("/home")
    public String doHome(){

        return "/v2/home/home";
    }

    @GetMapping("/rest2")
    @ResponseBody
    public String doRest2(){

        String strHtml="<html><body> Hi Rest , Controller + ResponseBody !!! <hr> Hi. </body></html>";

        return strHtml;

    }

}
