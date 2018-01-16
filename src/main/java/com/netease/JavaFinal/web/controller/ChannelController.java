package com.netease.JavaFinal.web.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.netease.JavaFinal.dao.ContentDao;
import com.netease.JavaFinal.meta.Content;
import com.netease.JavaFinal.meta.Person;
import com.netease.JavaFinal.utils.CurrentUser;
import com.netease.JavaFinal.web.viewmodel.ContentEditModel;
import com.netease.JavaFinal.web.viewmodel.ProductShowModel;

@Controller
public class ChannelController {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ContentDao contentDao;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String Index(@CurrentUser Person user, Model model,
			@RequestParam(value = "type", defaultValue = "0") int type) {
		model.addAttribute("user", user);
		model.addAttribute("title", "首页");
		return "index";
	}

	@RequestMapping(value = "/public", method = RequestMethod.GET)
	public String Public(Model model) {
		model.addAttribute("title", "发布");
		return "public";
	}
	
	@RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
	public String PublicSubmit(@ModelAttribute ContentEditModel contentModel, Model model) {
		try {
			Content content = contentModel.AsModel();
			contentDao.Insert(content);
			model.addAttribute("title", "发布成功");
			model.addAttribute("product", content);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("title", "发布失败");
		}
		return "publicSubmit";
	}

	@RequestMapping(value = "/api/upload", method = RequestMethod.POST)
	public ModelAndView Upload(@RequestParam("file") MultipartFile file) {
		try {
			ModelAndView modelView = new ModelAndView();
			Map<String, Object> modelMap = new HashMap<String, Object>();
			if (!file.isEmpty()) {
				String extension = file.getOriginalFilename()
						.substring(file.getOriginalFilename().lastIndexOf(".") + 1);
				String filePath = "image/" + UUID.randomUUID() + "." + extension;
				file.transferTo(new File(request.getSession().getServletContext().getRealPath("/") + filePath));
				modelMap.put("code", 200);
				modelMap.put("message", "");
				modelMap.put("result", filePath);
				modelView.addAllObjects(modelMap);
			} else {
				modelMap.put("code", 400);
				modelMap.put("message", "文件不能为空");
				modelMap.put("result", "");
				modelView.addAllObjects(modelMap);
			}
			return modelView;
		} catch (Exception e) {
			ModelAndView modelView = new ModelAndView();
			Map<String, Object> modelMap = new HashMap<String, Object>();
			modelMap.put("code", 400);
			modelMap.put("message", e.toString());
			modelMap.put("result", "");
			modelView.addAllObjects(modelMap);
			return modelView;
		}
	}
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String Show(@RequestParam("id") int id, @CurrentUser Person user, Model model) {
		Content content = contentDao.GetById(id);
		if(content != null) {
			model.addAttribute("title", content.getTitle());
			ProductShowModel product = new ProductShowModel(content, user);
			model.addAttribute("product", product);
		}
		else {
			model.addAttribute("title", "内容不存在");
		}
		return "show";
	}

}
