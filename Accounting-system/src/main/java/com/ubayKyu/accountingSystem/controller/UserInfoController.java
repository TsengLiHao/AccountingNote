package com.ubayKyu.accountingSystem.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
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
//	@RequestMapping("/UserList")
//	public String UserList(Model model) {
//		
//		return listPage(model, 1);
//	}
//	
//	@GetMapping("/UserList/page/{pageNumber}")
//	public String listPage(Model model, @PathVariable("pageNumber") int currentPage) {
//		
//		Page<UserInfo> page = service.listUserInfos(currentPage);
//		List<UserInfo> listUser = page.getContent();
//		model.addAttribute("userInfo", listUser);
//		int totalPages = page.getTotalPages();
//		
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("users", listUser);
//		model.addAttribute("totalPages", totalPages);
//		
//		return "UserList";
//	}
	
	@RequestMapping("/UserList")
	public String AccountingList(Model model, HttpServletRequest request) {
		
		return listPage(model, request, 1);
	}
	
	@GetMapping("/UserList/page/{pageNumber}")
	public String listPage(Model model, HttpServletRequest request, @PathVariable("pageNumber") int currentPage) {
		
		Object current = request.getSession().getAttribute("loginUser");
		Page<UserInfo> page = userInfoRepository.userInfo(current.toString(), PageRequest.of(currentPage-1, 10));
		List<UserInfo> listUser = page.getContent();
		int totalPages = page.getTotalPages();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("users", listUser);
		model.addAttribute("totalPages", totalPages);
		
		return "UserList";
	}
	
	@PostMapping("/UserList/delete")
	public String deleteProduct(Map<String,Object> map, HttpServletRequest request, Model model) {
		
		Object current = request.getSession().getAttribute("loginUser");
		String admin = userInfoRepository.userAccountByID(current.toString());
		LocalDate currentDate = LocalDate.now();
		
		if(request.getParameterValues("ID") != null ) {
			for(String userID : request.getParameterValues("ID")) {
				
				String deleteOfUser = userInfoRepository.userAccountByID(userID);
				LOGGER.info("管理者" + admin + "於" + currentDate + "刪除使用者" + deleteOfUser);
				
				accountingRepository.deleteUser(userID);
				categoryRepository.deleteUser(userID);
				service.deleteUserInfo(userID);
				}
			return listPage(model, request, 1);
		}else {
			map.put("deleteMsg", "請選擇項目後刪除");
			return listPage(model, request, 1);
		}
	 }	

	@GetMapping("/UserDetail")
	public String AccountingDetail(Model model, HttpServletRequest request) {
		
		dropdownlist(model, request);
		
		return "UserDetail";
	}
	
	@PostMapping("/UserDetail/add")
	public String saveUser(@Validated @ModelAttribute("newUser") UserInfo newUser, BindingResult result, Model model, Map<String,Object> map, @RequestParam("Account") String account, HttpServletRequest request) {
		
		List<String> ListOfAccount = userInfoRepository.ListOfUserAccount(account);
		dropdownlist(model, request);
		
		Object current = request.getSession().getAttribute("loginUser");
		
		String uuid = UUID.randomUUID().toString();
		model.addAttribute("uuid", uuid);
		
		if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "/UserDetail";
        }else if(ListOfAccount.contains(account)) {
        	map.put("renameMsgOfAccount", "帳號名稱重複,請重新命名");
			return "UserDetail";
        }else {
        	service.saveUserInfo(newUser);
        	userInfoRepository.reviseDate(current.toString());
    		return listPage(model, request, 1);
        }
	} 
	
	@GetMapping("/UserList/edit/{userID}")
	public ModelAndView editPage(@PathVariable("userID") String userID, Model model, HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView("UserDetail");
	    UserInfo userInfo = service.get(userID);
	    mav.addObject("newUser", userInfo);
	    
	    dropdownlist(model, request);
	    
	    return mav;
	}
	
    public void dropdownlist(Model model, HttpServletRequest request) {
		
		List<String> listLevel = Arrays.asList("1", "0");
	    model.addAttribute("listLevel", listLevel);
	    
	    UserInfo newUser = new UserInfo();
		model.addAttribute("newUser", newUser);
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
		
		Object current = request.getSession().getAttribute("loginUser");
		
		if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "/UserProfile";
        }else {
        	service.saveUserInfo(userProfile);
        	userInfoRepository.reviseDate(current.toString());
    		return "/UserProfile";
        }
	} 
	
	/*@PostMapping("/login")
	public void login(@ModelAttribute User user) {
		System.out.println(user.name);
	}*/
}