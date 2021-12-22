package com.ubayKyu.accountingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubayKyu.accountingSystem.entity.AccountingInfo;
import com.ubayKyu.accountingSystem.entity.CategoryInfo;
import com.ubayKyu.accountingSystem.repository.AccountingRepository;

@Service
public class AccountingService {

	@Autowired
	private AccountingRepository accountingRepo;
	
	public void delete(Integer id) {
		accountingRepo.deleteById(id);
	}
	
	public AccountingInfo saveAccountingInfo(AccountingInfo AccountingInfo) {
		return accountingRepo.save(AccountingInfo);
	}
	
	public AccountingInfo get(Integer id) {
        return accountingRepo.findById(id).get();
    }
}
