package com.ubayKyu.accountingSystem.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	public String CategoryList(Model model, HttpServletRequest request) {
		
//		Page<CategoryInfo> page = categoryService.listCategoryInfos();
//		List<CategoryInfo> listCategory = page.getContent();
//		model.addAttribute("categoryInfo", listCategory);
		return listPage(model, request, 1);
		
	}
	
	@GetMapping("/page/{pageNumber}")
	public String listPage(Model model, HttpServletRequest request, @PathVariable("pageNumber") int currentPage) {
		
		Object current = request.getSession().getAttribute("loginUser");
		Page<Map<String,Object>> page = categoryRepository.category(current.toString(), PageRequest.of(currentPage-1, 10));
		List<Map<String,Object>> listCategory = page.getContent();
		int totalPages = page.getTotalPages();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("category", listCategory);
		model.addAttribute("totalPages", totalPages);
		
		return "CategoryList";
	}
	
	@GetMapping("/CategoryDetail")
	public String CategoryDetail(Model model) {
		
		CategoryInfo newCategory = new CategoryInfo();
		model.addAttribute("newCategory", newCategory);
		
		return "CategoryDetail";
	}
	
	@PostMapping("/add")
	public String saveCategory(@Validated @ModelAttribute("newCategory") CategoryInfo newCategory, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "CategoryDetail";
        }else {
    		categoryService.saveCategoryInfo(newCategory);
    		
    		return "CategoryList";
        }
	}
	
	@GetMapping("CategoryDetail/edit/{id}")
	public String editCategory() {
		
		
		return "CategoryDetail";
	}
	
}
