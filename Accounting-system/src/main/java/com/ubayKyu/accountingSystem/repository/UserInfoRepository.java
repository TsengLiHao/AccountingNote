package com.ubayKyu.accountingSystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ubayKyu.accountingSystem.entity.AccountingInfo;
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
	
	@Query(value = "SELECT * FROM USERSINFO WHERE ID = ?1", nativeQuery = true)
    public UserInfo userInfoByID(String userID);
	
	@Query(value = "SELECT Account FROM USERSINFO WHERE ID = ?1", nativeQuery = true)
    public String userAccountByID(String userID);
	
	@Query(value = "SELECT Account FROM USERSINFO WHERE Account != ?1", nativeQuery = true)
    public List<String> ListOfUserAccount(String account);
	
	@Query(value = "SELECT * FROM USERSINFO WHERE ID != ?1", nativeQuery = true)
	public Page<UserInfo> userInfo(String userID, Pageable pageable);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE USERSINFO SET REVISEDATE = GETDATE() WHERE ID = ?1", nativeQuery = true)
    public void reviseDateByID(String id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE USERSINFO SET REVISEDATE = GETDATE() WHERE Account = ?1", nativeQuery = true)
    public void reviseDateByAccount(String account);
	
}
