package com.ubayKyu.accountingSystem.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ubayKyu.accountingSystem.entity.AccountingInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo;

@Repository
public interface AccountingRepository extends JpaRepository<AccountingInfo,Integer>{

	@Query(value = "SELECT TOP　(1) * FROM Accounting ORDER BY CREATEDATE ASC", nativeQuery = true)
    public List<AccountingInfo> newest();
	
	@Query(value = "SELECT TOP　(1) * FROM Accounting ORDER BY CREATEDATE DESC", nativeQuery = true)
    public List<AccountingInfo> latest();
	
	@Query(value = "SELECT COUNT(*) as Count FROM ACCOUNTING ", nativeQuery = true)
	public Long accountingCount();
    
	@Query(value = "SELECT * FROM ACCOUNTING WHERE USERID = ?1", nativeQuery = true)
	public Page<AccountingInfo> accounting(String userID, Pageable pageable);
	
	@Query(value = " SELECT(SELECT ISNULL(SUM(Amount),0) FROM Accounting WHERE ActType='1' AND UserID = ?1)"
			+ "     -(SELECT ISNULL(SUM(Amount),0) FROM Accounting  WHERE ActType='0' AND UserID = ?1)　AS Total ", nativeQuery = true)
	public Long accountingTotal(String userID);
	
	@Query(value = " SELECT(SELECT ISNULL(SUM(Amount),0) FROM Accounting WHERE ActType='1' AND UserID = ?1 "
			+ "      AND CreateDate >= DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1) AND  CreateDate <= EOMONTH(GETDATE()))"
			+ "     -(SELECT ISNULL(SUM(Amount),0) FROM Accounting  WHERE ActType='0' AND UserID = ?1 "
			+ "      AND CreateDate >= DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 1) AND  CreateDate <= EOMONTH(GETDATE()))　AS Total ", nativeQuery = true)
	public Long accountingMonthTotal(String userID);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM ACCOUNTING WHERE USERID = ?1", nativeQuery = true)
    public void deleteUser(String userID);
}
