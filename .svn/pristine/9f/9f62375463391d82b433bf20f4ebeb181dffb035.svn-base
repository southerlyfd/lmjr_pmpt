package com.pmpt.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmpt.common.MainUtilityTools;

@Controller
public class LoginController {

    @RequestMapping("/login.do")
    public void login(Model model,String name,HttpServletResponse response) {
        String nameTemp = name;
        response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print("{\"hello\":\"" + nameTemp + "\"}");
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MainUtilityTools.catchException(e, this.getClass(), "");
		}
    }
    
    @RequestMapping("/login2.do")
    public void login2(Model model,String name,HttpServletResponse response) {
        String nameTemp = name;
        response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print("{\"hello2\":\"" + nameTemp + "\"}");
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MainUtilityTools.catchException(e, this.getClass(), "");
		}
    }
}