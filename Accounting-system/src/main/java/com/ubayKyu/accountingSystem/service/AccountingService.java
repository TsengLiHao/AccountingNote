package com.ubayKyu.accountingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubayKyu.accountingSystem.entity.AccountingInfo;
import com.ubayKyu.accountingSystem.repository.AccountingRepository;

@Service
public class AccountingService {

	@Autowired
	private AccountingRepository accountingRepo;
	
	public void delete(Integer id) {
		accountingRepo.deleteById(id);
	}
}
