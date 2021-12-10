package com.ubayKyu.accountingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubayKyu.accountingSystem.entity.CategoryInfo;
import com.ubayKyu.accountingSystem.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<CategoryInfo> getCategoryInfos(){
		return categoryRepo.findAll();
	}
	
}
