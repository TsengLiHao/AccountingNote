package com.ubayKyu.accountingSystem.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubayKyu.accountingSystem.entity.AccountingInfo;
import com.ubayKyu.accountingSystem.repository.AccountingRepository;
import com.ubayKyu.accountingSystem.service.AccountingService;
import com.ubayKyu.accountingSystem.service.CategoryService;


@Controller
public class AccountingController {

	@Autowired
	private AccountingRepository accountingRepository;
	
	@Autowired
	private AccountingService accountingService;

	
	@RequestMapping("/AccountingList")
	public String AccountingList(Model model, HttpServletRequest request) {
		
//		Page<CategoryInfo> page = categoryService.listCategoryInfos();
//		List<CategoryInfo> listCategory = page.getContent();
//		model.addAttribute("categoryInfo", listCategory);
		return listPage(model, request, 1);
	}
	
	@GetMapping("AccountingList/page/{pageNumber}")
	public String listPage(Model model, HttpServletRequest request, @PathVariable("pageNumber") int currentPage) {
		
		Object current = request.getSession().getAttribute("loginUser");
		Page<AccountingInfo> page = accountingRepository.accounting(current.toString(), PageRequest.of(currentPage-1, 3));
		List<AccountingInfo> listAccounting = page.getContent();
		int totalPages = page.getTotalPages();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("accounting", listAccounting);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalAmount", accountingRepository.accountingTotal(current.toString()));
		
		return "AccountingList";
	}
	
	@RequestMapping("/AccountingDetail")
	public String AccountingDetail() {
		return "AccountingDetail";
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
}
