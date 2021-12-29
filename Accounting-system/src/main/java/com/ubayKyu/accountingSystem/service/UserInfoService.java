 package com.ubayKyu.accountingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubayKyu.accountingSystem.entity.AccountingInfo;
import com.ubayKyu.accountingSystem.entity.CategoryInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoRepository repository;
	
	public List<UserInfo> getUserInfos(){
		return repository.findAll();
	}
	
	//test
	public UserInfo saveUserInfo(UserInfo UserInfo) {
		return repository.save(UserInfo);
	}
	
	public List<UserInfo> getUserInfoById(List<String> ids){
		return repository.findAllById(ids);
	}
	
	public void deleteUserInfo(String id) {
		repository.deleteById(id);
	}
	
	public Page<UserInfo> listUserInfos(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber-1, 10);
		return repository.findAll(pageable);
	}
	
	public UserInfo get(String userID) {
        return repository.findById(userID).get();
    }
}
