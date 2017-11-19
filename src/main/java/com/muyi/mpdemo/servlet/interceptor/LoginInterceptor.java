package com.muyi.mpdemo.servlet.interceptor;

import com.muyi.mpdemo.enums.ResultEnum;
import com.muyi.mpdemo.exception.BizException;
import com.muyi.mpdemo.utils.StringUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //在请求处理之前进行调用(Controller方法调用之前)

        HttpSession session = httpServletRequest.getSession(false);

        if(session == null || session.getAttribute("userID") == null){
            //Not login user will be intercepted
            throw new BizException(ResultEnum.LOGIN_FIRST);
        }
        // 只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        //请求处理之后进行调用，但是在视图被渲染之前(Controller方法调用之后)

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        //在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行(主要是用于进行资源清理工作
    }
}
