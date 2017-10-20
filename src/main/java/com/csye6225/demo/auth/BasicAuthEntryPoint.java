package com.csye6225.demo.auth;

/*
 *  Shuangshuang Xu  001239511 xu.shua@husky.neu.edu
 *  Mengfei Zhang    001115548 zhang.mengf@husky.neu.edu
 *  Mengfan Xu       001238833 xu.mengf@husky.neu.edu
 *  YeHui Rong       001957596 rong.ye@husky.neu.edu
 */

import com.google.gson.JsonObject;
import org.apache.http.entity.ContentType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class BasicAuthEntryPoint extends BasicAuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException, ServletException {
    response.addHeader("WWW-Authenticate","Basic realm=\""+getRealmName()+"\"");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    PrintWriter writer =response.getWriter();
    writer.println("HTTP Status 403 - " + authEx.getMessage());

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    setRealmName("csye6225");
    super.afterPropertiesSet();
  }
}
