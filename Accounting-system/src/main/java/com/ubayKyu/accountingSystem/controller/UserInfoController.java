package com.ubayKyu.accountingSystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubayKyu.accountingSystem.dto.User;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.repository.AccountingRepository;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository;
import com.ubayKyu.accountingSystem.service.UserInfoService;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserInfoController {

	@Autowired
	private UserInfoService service;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private AccountingRepository accountingRepository;

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
		model.addAttribute("newestDate", userInfoRepository.newest());
		model.addAttribute("latestDate", userInfoRepository.latest());
		model.addAttribute("memberCount", userInfoRepository.memberCount());
		model.addAttribute("accountingCount", accountingRepository.accountingCount());
		return "Default";
	}
	@RequestMapping("/UserList")
	public String UserList() {
		return "UserList";
	}
	@RequestMapping("/UserProfile")
	public String UserProfile() {
		return "UserProfile";
	}
	/*@PostMapping("/login")
	public void login(@ModelAttribute User user) {
		System.out.println(user.name);
	}*/
}