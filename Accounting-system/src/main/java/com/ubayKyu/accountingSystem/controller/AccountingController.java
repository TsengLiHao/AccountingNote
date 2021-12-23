package com.ubayKyu.accountingSystem.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ubayKyu.accountingSystem.entity.AccountingInfo;
import com.ubayKyu.accountingSystem.entity.CategoryInfo;
import com.ubayKyu.accountingSystem.repository.AccountingRepository;
import com.ubayKyu.accountingSystem.repository.CategoryRepository;
import com.ubayKyu.accountingSystem.service.AccountingService;
import com.ubayKyu.accountingSystem.service.CategoryService;


@Controller
public class AccountingController {

	@Autowired
	private AccountingRepository accountingRepository;
	
	@Autowired
	private AccountingService accountingService;
	
	@Autowired
	private CategoryRepository categoryRepository;

	
	@RequestMapping("/AccountingList")
	public String AccountingList(Model model, HttpServletRequest request) {
		
//		Page<CategoryInfo> page = categoryService.listCategoryInfos();
//		List<CategoryInfo> listCategory = page.getContent();
//		model.addAttribute("categoryInfo", listCategory);
		return listPage(model, request, 1);
	}
	
	@GetMapping("/AccountingList/page/{pageNumber}")
	public String listPage(Model model, HttpServletRequest request, @PathVariable("pageNumber") int currentPage) {
		
		Object current = request.getSession().getAttribute("loginUser");
		Page<AccountingInfo> page = accountingRepository.accounting(current.toString(), PageRequest.of(currentPage-1, 3));
		List<AccountingInfo> listAccounting = page.getContent();
		int totalPages = page.getTotalPages();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("accounting", listAccounting);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalAmount", accountingRepository.accountingTotal(current.toString()));
		model.addAttribute("totalMonthAmount", accountingRepository.accountingMonthTotal(current.toString()));
		
		return "AccountingList";
	}
	
	@PostMapping("/AccountingList/delete")
	public String deleteProduct(Map<String,Object> map, HttpServletRequest request, Model model) {
		
		if(request.getParameterValues("ID") != null ) {
			for(String idStr : request.getParameterValues("ID")) {
				int accountingID = Integer.parseInt(idStr);
				accountingService.delete(accountingID); 
			}
			return listPage(model, request, 1);
		}else {
			map.put("deleteMsg", "請選擇項目後刪除");
			return listPage(model, request, 1);
		}
	 }	
	
	@GetMapping("/AccountingDetail")
	public String AccountingDetail(Model model, HttpServletRequest request) {
		
		AccountingInfo newAccounting = new AccountingInfo();
		model.addAttribute("newAccounting", newAccounting);
		
		dropdownlist(model, request);
		
		return "AccountingDetail";
	}
	
	@PostMapping("/AccountingDetail/add")
	public String saveCategory(@Validated @ModelAttribute("newAccounting") AccountingInfo newAccounting, BindingResult result, Model model, HttpServletRequest request) {
		
		dropdownlist(model, request);
		
		if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "/AccountingDetail";
        }else {
        	accountingService.saveAccountingInfo(newAccounting);
    		return listPage(model, request, 1);
        }
	} 
	
	@GetMapping("/AccountingList/edit/{id}")
	public ModelAndView editPage(@PathVariable("id") int id, Model model, HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView("AccountingDetail");
	    AccountingInfo accountingnfo = accountingService.get(id);
	    mav.addObject("newAccounting", accountingnfo);
	    
	    dropdownlist(model, request);
	    
	    return mav;
	}
	
	public void dropdownlist(Model model, HttpServletRequest request) {
		
		List<String> listInOut = Arrays.asList("1", "0");
	    model.addAttribute("listInOut", listInOut);
	    
	    Object current = request.getSession().getAttribute("loginUser");
	    List<String> listCategoryName = categoryRepository.ListOfcategoryName(current.toString());
	    model.addAttribute("listCategoryName", listCategoryName);
	}
}
