package com.csye6225.spring.controller;

import com.csye6225.spring.pojo.Account;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class LoginController extends MyController{

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public ModelAndView loginPage()  {
        return new ModelAndView("Login");
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String password=request.getParameter("password");
        String email=request.getParameter("email");

        Session session=getSession();

        List<Account> accountList=session.createQuery("from Account").list();
        Boolean flag=false;
        for (Account accountD:accountList){
            if(accountD.getEmail().equals(email)&&accountD.getPassword().equals(password)){
                flag=true;
            }
        }
        if(flag){
            return new ModelAndView("HomePage","email",email);
        }else{
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print("<script type=\"text/javascript\"> "
                    + "window.alert('Please Check Your Email or Password');"
                    + "window.location.href='login.htm';"
                    + "</script>");
        }

        return null;
    }
}
