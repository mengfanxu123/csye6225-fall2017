package com.csye6225.spring.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.csye6225.spring.pojo.Account;

@Controller
public class AccountController extends MyController{

	@RequestMapping(value = "/account.htm", method = RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("Account");
	}

	@RequestMapping(value = "/account.htm", method = RequestMethod.POST)
    public ModelAndView accountPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String password= request.getParameter("password");
		String email=request.getParameter("email");
		Session session=getSession();

		List<Account> accountList=session.createQuery("from Account").list();
		Boolean flag=false;
		for (Account accountD:accountList){
			if(accountD.getEmail().equals(email)){
				flag=true;
			}

		}
		if(flag){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script type=\"text/javascript\"> "
					+ "window.alert('You input the repeat Email');"
					+ "window.location.href='account.htm';"
					+ "</script>");
		}else{
			Account account=new Account();
			session.beginTransaction();
			account.setId(1);
			account.setPassword(password);
			account.setEmail(email);
			session.save(account);
			session.beginTransaction().commit();
			return new ModelAndView("HomePage");
		}



	    return null;
    }
}

