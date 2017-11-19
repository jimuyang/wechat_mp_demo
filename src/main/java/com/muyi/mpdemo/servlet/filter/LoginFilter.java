package com.muyi.mpdemo.servlet.filter;

import com.muyi.mpdemo.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/hello/*")
public class LoginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.warn("LoginFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        HttpSession session = request.getSession();
        String userID = session.getAttribute("userID").toString();
        if(StringUtil.isEmpty(userID)){
            //没有登录
            //直接返回 请先登录的错误码

            //filterChain.doFilter(servletRequest, servletResponse);
            return;

        }else{
            //已经登录时 继续请求
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }


    }

    @Override
    public void destroy() {
        log.warn("LoginFilter destroyed...");
    }
}
