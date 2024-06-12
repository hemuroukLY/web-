package com.ly.Filter;

import com.ly.utils.jwtUtils;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import static java.lang.System.out;


public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //判断是否是login界面
        String url = request.getRequestURL().toString();
        String method = request.getMethod();

        if(     method.equals("OPTIONS") ||
                url.contains("login") ||
                url.contains("register") ||
                url.equals("http://localhost:8080/favicon.ico") ||
                url.equals("http://localhost:8080/")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        //获取jwt判断是否有token
        String jwt = request.getHeader("token");

        if(jwt == null){
            return ;
        }
        //解析令牌
        try {
            Claims claims = jwtUtils.parseJwt(jwt);
            Object username = claims.get("username");
            filterChain.doFilter(servletRequest, servletResponse);
            response.getWriter().write((String) username);
        } catch (Exception e) {
            return ;
        }
        //放行

    }

    @Override
    public void destroy() {

    }
}
