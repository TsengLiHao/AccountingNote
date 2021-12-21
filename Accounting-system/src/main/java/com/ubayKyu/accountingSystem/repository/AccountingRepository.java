package com.ubayKyu.accountingSystem.repository;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ubayKyu.accountingSystem.entity.AccountingInfo;

@Repository
public interface AccountingRepository extends JpaRepository<AccountingInfo,Integer>{

	@Query(value = "SELECT COUNT(*) as Count FROM ACCOUNTING ", nativeQuery = true)
	public Long accountingCount();
    
	@Query(value = "SELECT * FROM ACCOUNTING WHERE USERID = ?1", nativeQuery = true)
	public Page<AccountingInfo> accounting(String userID, Pageable pageable);
	
	@Query(value = " SELECT(SELECT SUM(Amount) FROM Accounting WHERE ActType='1' AND UserID = ?1)"
			+ "     -(SELECT SUM(Amount) FROM Accounting  WHERE ActType='0' AND UserID = ?1)　AS Total ", nativeQuery = true)
	public Long accountingTotal(String userID);
}
