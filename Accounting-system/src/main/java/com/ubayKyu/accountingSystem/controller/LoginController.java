package com.ubayKyu.accountingSystem.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository;
import com.ubayKyu.accountingSystem.service.UserInfoService;

@Controller
public class LoginController {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@GetMapping("/Login")
	public String Login(HttpServletRequest request, Model model) {
		if(request.getSession().getAttribute("loginUser") != null) {
			Object current = request.getSession().getAttribute("loginUser");
			UserInfo userProfile = userInfoRepository.userInfoByID(current.toString());
			model.addAttribute("editProfile", userProfile);
			
			if(userProfile == null) {
				return "Login";
			}else {
				return "/UserProfile";
			}
		}
		return "Login";
	}
	
	@PostMapping("/LoginSuccese")
	public String UserLogin(@RequestParam("account") String account,
			                 @RequestParam("password") String password,
			                 Map<String,Object> map, HttpSession session, Model model, HttpServletRequest request) {
		
		if(userInfoRepository.userInfo(account) != null) {
			
			String userAccount = userInfoRepository.userInfo(account).Account;
			String userPWD = userInfoRepository.userInfo(account).PWD;
			
			if(userAccount.equals(account) && userPWD.equals(password)){
				session.setAttribute("loginLevel", userInfoRepository.userInfo(account).USERLEVEL);
				session.setAttribute("loginUser", userInfoRepository.userInfo(account).ID);
				
				Object current = request.getSession().getAttribute("loginUser");
				UserInfo userProfile = userInfoRepository.userInfoByID(current.toString());
				model.addAttribute("editProfile", userProfile);
				
				return "UserProfile";
			}
	        else {
	        	map.put("msg", "帳號或密碼錯誤");
	        	return "Login";
	        }
		}else {
			map.put("msg", "輸入的帳號不存在");
        	return "Login";
		}
	}
	
	@GetMapping("/Logout")
	public String UserLogout(HttpServletRequest request) {
		
        HttpSession session = request.getSession();
        
        if(session.getAttribute("loginLevel") != null && session.getAttribute("loginUser") != null ) {
        	session.removeAttribute("loginLevel");
        	session.removeAttribute("loginUser");
        	return "Login";
        }
        return "Login";
		
	}
}
