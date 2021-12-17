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
import org.springframework.web.servlet.ModelAndView;

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
	public String saveCategory(@Validated @ModelAttribute("newCategory") CategoryInfo newCategory, BindingResult result, Model model, HttpServletRequest request, Map<String,Object> map, @RequestParam("CATEGORYNAME") String categoryName) {
		
		Object current = request.getSession().getAttribute("loginUser");
		List<String> ListOfCategoryName = categoryRepository.ListOfcategoryName(current.toString());
		
		if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "CategoryDetail";
        }else if(ListOfCategoryName.contains(categoryName)) {
        	map.put("renameMsg", "標題名稱重複,請重新命名");
			return "CategoryDetail";
        }else {
    		categoryService.saveCategoryInfo(newCategory);
    		return listPage(model, request, 1);
        }
	}
	
	@GetMapping("/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("/CategoryDetail");
	    CategoryInfo categoryInfo = categoryService.get(id);
	    mav.addObject("newCategory", categoryInfo);
	    
	    return mav;
	}
	
	@PostMapping("/delete")
	public String deleteProduct(Map<String,Object> map, HttpServletRequest request, Model model) {
		
		if(request.getParameterValues("categoryID") != null ) {
			for(String categoryIDStr : request.getParameterValues("categoryID")) {
				int categoryID = Integer.parseInt(categoryIDStr);
				Long categoryCount = categoryRepository.categoryCount(categoryID);
				if(categoryCount != 0) {
				    map.put("deleteMsg", "此分類裡已有流水帳,無法刪除");
			    }else {
			    	categoryService.delete(categoryID);
			    }
			}
			return listPage(model, request, 1);
		}else {
			map.put("deleteMsg", "請選擇項目後刪除");
			return listPage(model, request, 1);
		}
	 }	
	
}
