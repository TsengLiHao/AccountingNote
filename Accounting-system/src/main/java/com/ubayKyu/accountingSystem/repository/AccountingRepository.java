package com.ubayKyu.accountingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ubayKyu.accountingSystem.entity.AccountingInfo;

public interface AccountingRepository extends  JpaRepository<AccountingInfo,String>{

	@Query(value = "SELECT COUNT(*) as Count FROM ACCOUNTING ", nativeQuery = true)
	public Long accountingCount();
}
