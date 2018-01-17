package com.netease.JavaFinal.web.controller;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
import com.netease.JavaFinal.dao.ShoppingDao;
import com.netease.JavaFinal.dao.TransactionDao;
import com.netease.JavaFinal.meta.Content;
import com.netease.JavaFinal.meta.Person;
import com.netease.JavaFinal.meta.Shopping;
import com.netease.JavaFinal.meta.Transaction;
import com.netease.JavaFinal.service.TransactionService;
import com.netease.JavaFinal.utils.CurrentUser;
import com.netease.JavaFinal.web.viewmodel.ContentEditModel;
import com.netease.JavaFinal.web.viewmodel.ProductShowModel;

@Controller
public class ChannelController {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ContentDao contentDao;
	@Autowired
	private ShoppingDao shoppingDao;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private TransactionDao transactionDao;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String Index(@CurrentUser Person user, Model model,
			@RequestParam(value = "type", defaultValue = "0") int type) {
		model.addAttribute("user", user);
		model.addAttribute("title", "首页");
		List<ProductShowModel> productList = null;
		if (type != 1) {
			List<Content> list = contentDao.GetAll();
			if (list != null && list.size() > 0) {
				productList = new LinkedList<ProductShowModel>();
				for (Content item : list) {
					productList.add(new ProductShowModel(item, user));
				}
			}
		} else {
			List<Content> list = contentDao.GetUnBuy();
			if (list != null && list.size() > 0) {
				productList = new LinkedList<ProductShowModel>();
				for (Content item : list) {
					productList.add(new ProductShowModel(item, user));
				}
			}
		}
		model.addAttribute("productList", productList);
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
		if (content != null) {
			model.addAttribute("title", content.getTitle());
			ProductShowModel product = new ProductShowModel(content, user);
			model.addAttribute("product", product);
		} else {
			model.addAttribute("title", "内容不存在");
		}
		return "show";
	}

	@RequestMapping(value = "/api/addShopping", method = RequestMethod.POST)
	public ModelAndView AddShopping(@RequestParam("number") int number, @RequestParam("contentId") int contentId,
			@CurrentUser Person user) {
		try {
			ModelAndView modelView = new ModelAndView();
			Map<String, Object> modelMap = new HashMap<String, Object>();
			if (number > 0) {
				if (contentDao.GetById(contentId) != null) {
					Shopping shopping = shoppingDao.GetByContentIdAndPersonId(contentId, user.getId());
					if (shopping == null) {
						shopping = new Shopping();
						shopping.setContentId(contentId);
						shopping.setNumber(number);
						shopping.setPersonId(user.getId());
						shopping.setTime(System.currentTimeMillis());
						shoppingDao.Insert(shopping);
					} else {
						shopping.setNumber(number + shopping.getNumber());
						shopping.setTime(System.currentTimeMillis());
						shoppingDao.Update(shopping);
					}
					modelMap.put("code", 200);
					modelMap.put("message", "");
					modelMap.put("result", "");
					modelView.addAllObjects(modelMap);
				} else {
					modelMap.put("code", 400);
					modelMap.put("message", "商品不存在");
					modelMap.put("result", "");
					modelView.addAllObjects(modelMap);
				}
			} else {
				modelMap.put("code", 400);
				modelMap.put("message", "数量不正确");
				modelMap.put("result", "");
				modelView.addAllObjects(modelMap);
			}
			modelView.addAllObjects(modelMap);
			return modelView;

		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView modelView = new ModelAndView();
			Map<String, Object> modelMap = new HashMap<String, Object>();
			modelMap.put("code", 400);
			modelMap.put("message", e.toString());
			modelMap.put("result", "");
			modelView.addAllObjects(modelMap);
			return modelView;
		}
	}

	@RequestMapping(value = "/settleAccount", method = RequestMethod.GET)
	public String SettleAccount(Model model) {
		List<Shopping> shoppingList = shoppingDao.GetAll();
		model.addAttribute("title", "购物车");
		model.addAttribute("shoppingList", shoppingList);
		return "settleAccount";
	}

	@RequestMapping(value = "/api/buy", method = RequestMethod.POST)
	public ModelAndView Buy() {
		try {
			ModelAndView modelView = new ModelAndView();
			Map<String, Object> modelMap = new HashMap<String, Object>();
			transactionService.SettleAccounts();
			modelMap.put("code", 200);
			modelMap.put("message", "");
			modelMap.put("result", "");
			modelView.addAllObjects(modelMap);
			return modelView;
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView modelView = new ModelAndView();
			Map<String, Object> modelMap = new HashMap<String, Object>();
			modelMap.put("code", 400);
			modelMap.put("message", e.toString());
			modelMap.put("result", false);
			modelView.addAllObjects(modelMap);
			return modelView;
		}
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String Account(@CurrentUser Person user, Model model) {
		List<Transaction> buyList = transactionDao.GetByPersonId(user.getId());
		model.addAttribute("title", "账务");
		model.addAttribute("buyList", buyList);
		return "account";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String Edit(@RequestParam("id") int id, Model model) {
		model.addAttribute("title", "编辑");
		Content content = contentDao.GetById(id);
		if(content != null) {
			model.addAttribute("product", new ContentEditModel(content));
		}
		return "edit";
	}
	
	@RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
	public String EditSubmit(@ModelAttribute ContentEditModel contentModel, Model model) {
		try {
			Content content = contentModel.AsModel();
			contentDao.Update(content);
			model.addAttribute("title", "发布成功");
			model.addAttribute("product", content);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("title", "发布失败");
		}
		return "editSubmit";
	}

}
