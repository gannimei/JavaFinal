package com.netease.JavaFinal.web.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.netease.JavaFinal.dao.PersonDao;
import com.netease.JavaFinal.meta.Person;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private PersonDao personDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("userName")) {
						Person person = personDao.GetByUserName(cookie.getValue());
						if(person != null) {
							session.setAttribute("user", person);
						}
					}
				}
			}
		}
		return true;
	}
	
}
