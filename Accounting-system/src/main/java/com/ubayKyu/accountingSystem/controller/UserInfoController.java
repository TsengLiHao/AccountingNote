package com.ubayKyu.accountingSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.entity.AccountingInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.repository.AccountingRepository;
import com.ubayKyu.accountingSystem.repository.CategoryRepository;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository;
import com.ubayKyu.accountingSystem.service.AccountingService;
import com.ubayKyu.accountingSystem.service.CategoryService;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserInfoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	private UserInfoService service;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private AccountingRepository accountingRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping("/addUserInfo")
	public UserInfo addClient(@RequestBody UserInfo userInfo) {
		return service.saveUserInfo(userInfo);
	}

	@GetMapping("/userInfos")
	public List<UserInfo> findAllClient() {
		return service.getUserInfos();
	}

	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("message", "Hello Thymeleaf!!");
		return "hello";
	}
	@GetMapping("/Default")
	public String Default_page(Model model) {
		model.addAttribute("newestDate", accountingRepository.newest());
		model.addAttribute("latestDate", accountingRepository.latest());
		model.addAttribute("memberCount", userInfoRepository.memberCount());
		model.addAttribute("accountingCount", accountingRepository.accountingCount());
		return "Default";
	}
	@RequestMapping("/UserList")
	public String UserList(Model model) {
		
		return listPage(model, 1);
	}
	
	@GetMapping("/UserList/page/{pageNumber}")
	public String listPage(Model model, @PathVariable("pageNumber") int currentPage) {
		
		Page<UserInfo> page = service.listUserInfos(currentPage);
		List<UserInfo> listUser = page.getContent();
		model.addAttribute("userInfo", listUser);
		int totalPages = page.getTotalPages();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("users", listUser);
		model.addAttribute("totalPages", totalPages);
		
		return "UserList";
	}
	
	@PostMapping("/UserList/delete")
	public String deleteProduct(Map<String,Object> map, HttpServletRequest request, Model model) {
		
		if(request.getParameterValues("ID") != null ) {
			for(String userID : request.getParameterValues("ID")) {
				accountingRepository.deleteUser(userID);
				categoryRepository.deleteUser(userID);
				service.deleteUserInfo(userID);
				}
			return listPage(model, 1);
		}else {
			map.put("deleteMsg", "請選擇項目後刪除");
			return listPage(model, 1);
		}
	 }	
	
	@GetMapping("/UserProfile")
	public String UserProfile(Model model, HttpServletRequest request) {
		
		Object current = request.getSession().getAttribute("loginUser");
		UserInfo userProfile = userInfoRepository.userInfoByID(current.toString());
		model.addAttribute("editProfile", userProfile);
		
		return "UserProfile";
	}
	
	@PostMapping("/UserProfile/edit")
	public String saveCategory(@Validated @ModelAttribute("editProfile") UserInfo userProfile, BindingResult result, Model model, HttpServletRequest request) {
		
		if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "/UserProfile";
        }else {
        	service.saveUserInfo(userProfile);
    		return "/UserProfile";
        }
	} 
	
	/*@PostMapping("/login")
	public void login(@ModelAttribute User user) {
		System.out.println(user.name);
	}*/
}