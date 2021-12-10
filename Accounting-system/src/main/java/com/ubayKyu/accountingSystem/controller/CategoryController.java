package com.ubayKyu.accountingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubayKyu.accountingSystem.entity.CategoryInfo;
import com.ubayKyu.accountingSystem.repository.CategoryRepository;
import com.ubayKyu.accountingSystem.service.CategoryService;
import com.ubayKyu.accountingSystem.service.UserInfoService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryService categoryService;
	

	@RequestMapping("/CategoryList")
	public String CategoryList(Model model) {
		
		model.addAttribute("categoryInfo", categoryService.getCategoryInfos());
		model.addAttribute("category", categoryRepository.category());
		
		return "CategoryList";
	}
	
	@RequestMapping("/CategoryDetail")
	public String CategoryDetail(Model model) {
		
		CategoryInfo newCategory = new CategoryInfo();
		model.addAttribute("newCategory", newCategory);
		
		return "CategoryDetail";
	}
	
	@PostMapping("/save")
	public String saveCategory() {
		return "CategoryList";
	}
}
