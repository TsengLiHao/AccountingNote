package com.ubayKyu.accountingSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ubayKyu.accountingSystem.entity.CategoryInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.repository.CategoryRepository;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository;

@SpringBootApplication
public class AccountingSystemApplication implements CommandLineRunner{

	@Autowired
	private UserInfoRepository UserInfoRepo;
	
	@Autowired
	private CategoryRepository CategoryRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(AccountingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CategoryInfo> category = CategoryRepo.findAll();
		
		category.forEach(System.out :: println);
		
	}

}