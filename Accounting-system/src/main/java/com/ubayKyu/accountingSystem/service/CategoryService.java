package com.ubayKyu.accountingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubayKyu.accountingSystem.entity.CategoryInfo;
import com.ubayKyu.accountingSystem.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	public Page<CategoryInfo> listCategoryInfos() {
		Pageable pageable = PageRequest.of(0, 10);
		return categoryRepo.findAll(pageable);
	}

	public CategoryInfo saveCategoryInfo(CategoryInfo CategoryInfo) {
		return categoryRepo.save(CategoryInfo);
	}
	
	public CategoryInfo get(Integer id) {
        return categoryRepo.findById(id).get();
    }

	public void delete(Integer id) {
		categoryRepo.deleteById(id);
	}
}
