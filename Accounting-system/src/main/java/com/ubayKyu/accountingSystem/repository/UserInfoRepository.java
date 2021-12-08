package com.ubayKyu.accountingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ubayKyu.accountingSystem.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,String>{
	@Query(value = "SELECT TOP　(1) * FROM USERSINFO ORDER BY CREATEDATE ASC", nativeQuery = true)
    public List<UserInfo> newest();
	
	@Query(value = "SELECT TOP　(1) * FROM USERSINFO ORDER BY CREATEDATE DESC", nativeQuery = true)
    public List<UserInfo> latest();
	
	@Query(value = "SELECT COUNT(*) as Count FROM USERSINFO ", nativeQuery = true)
	public Long memberCount();
	
	@Query(value = "SELECT * FROM USERSINFO WHERE Account = ?1", nativeQuery = true)
    public UserInfo userInfo(String account);
}
