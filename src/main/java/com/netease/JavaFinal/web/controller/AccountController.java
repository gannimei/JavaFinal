package com.netease.JavaFinal.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.netease.JavaFinal.dao.PersonDao;
import com.netease.JavaFinal.meta.Person;
import com.netease.JavaFinal.web.viewmodel.LoginEditModel;

@Controller
public class AccountController {

	@Autowired
	private PersonDao personDao;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("title", "登录");
		return "login";
	}
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute LoginEditModel loginModel, HttpSession session) {
		ModelAndView modelView = new ModelAndView();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Person person = personDao.GetByUserName(loginModel.getUserName());
		if(person == null) {
			modelMap.put("code", 400);
			modelMap.put("result", false);
			modelMap.put("message", "该账户不存在");
		}
		else if(!person.getPassword().equals(loginModel.getPassword())) {
			modelMap.put("code", 400);
			modelMap.put("result", false);
			modelMap.put("message", "密码不正确");
		}
		else {
			modelMap.put("code", 200);
			modelMap.put("result", true);
			modelMap.put("message", "");
			session.setAttribute("user", person);
		}
		modelView.addAllObjects(modelMap);
		return modelView;
	}
	
}
