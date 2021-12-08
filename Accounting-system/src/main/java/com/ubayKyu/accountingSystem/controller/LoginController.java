package com.ubayKyu.accountingSystem.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@PostMapping("/Login")
	public String UserLogin(@RequestParam("account") String account,
			                 @RequestParam("password") String password,
			                 Map<String,Object> map, HttpSession session) {
		
		if(userInfoRepository.userInfo(account) != null) {
			
			String userAccount = userInfoRepository.userInfo(account).Account;
			String userPWD = userInfoRepository.userInfo(account).PWD;
			
			if(userAccount.equals(account) && userPWD.equals(password)){
				session.setAttribute("loginLevel", userInfoRepository.userInfo(account).USERLEVEL);
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
        
        if(session.getAttribute("loginLevel") != null ) {
        	session.removeAttribute("loginLevel");
        	return "Login";
        }
        return "Login";
		
	}
}
