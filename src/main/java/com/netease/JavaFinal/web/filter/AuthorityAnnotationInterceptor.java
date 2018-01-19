package com.netease.JavaFinal.web.filter;

import java.io.Writer;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.netease.JavaFinal.meta.Person;
import com.netease.JavaFinal.utils.AuthorityFilter;

public class AuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean isAjaxRequest = false;  
        if("*/*".equals(request.getHeader("Accept"))){  
            isAjaxRequest = true;  
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        AuthorityFilter authorityFilter = handlerMethod.getMethodAnnotation(AuthorityFilter.class);
        if(authorityFilter != null) {
    		if(request.getSession().getAttribute("user") == null) {
    			if(!isAjaxRequest) {
    				response.setCharacterEncoding("utf-8");
    				response.sendRedirect(request.getContextPath()  + "/login");
    			}
    			else {
    				response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=UTF-8");
                    Writer writer = response.getWriter();
                    writer.write("{\"result\":false,\"code\":400,\"message\":\"请登录\"}");
    			}
    			return false;
    		}
    		Person user = (Person)request.getSession().getAttribute("user");
    		if(authorityFilter.type() > -1 && user.getUserType() != authorityFilter.type()) {
    			if(!isAjaxRequest) {
    				response.sendRedirect(request.getContextPath()  + "/error?message=" + URLEncoder.encode("权限不足", "UTF-8"));
    			}
    			else {
    				response.setCharacterEncoding("utf-8");
                    response.setContentType("application/json;charset=UTF-8");
                    Writer writer = response.getWriter();
                    writer.write("{\"result\":false,\"code\":400,\"message\":\"权限不足\"}");
    			}
    		}
        }
		return true;
	}
	
}
