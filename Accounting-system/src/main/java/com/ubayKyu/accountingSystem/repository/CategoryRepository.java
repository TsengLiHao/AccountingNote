package com.ubayKyu.accountingSystem.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ubayKyu.accountingSystem.entity.CategoryInfo;
import com.ubayKyu.accountingSystem.entity.UserInfo;

public interface CategoryRepository extends JpaRepository<CategoryInfo, Integer> {

	@Query(value = "SELECT CategoryID, Category.UserID, Category.CreateDate, Category.CategoryName, Category.Body ,COUNT(Accounting.CategoryName) as Count"
			+ "     FROM AccountingNote.dbo.Category"
			+ "     LEFT JOIN AccountingNote.dbo.Accounting"
			+ "     ON Category.CategoryName = Accounting.CategoryName"
			+ "     WHERE Category.UserID = ?1"
			+ "     GROUP BY CategoryID,Category.UserID,Category.CreateDate, Category.CategoryName, Category.Body ORDER BY Category.CreateDate DESC"
		   ,countQuery = "SELECT count(*) FROM AccountingNote.dbo.Category WHERE Category.UserID = ?1"
		   ,nativeQuery = true)
	public Page<Map<String,Object>> category(@Param("UserID")String userID, Pageable pageable);
	
	@Query(value = "SELECT COUNT(Accounting.CategoryName) as Count"
			+ "	    FROM AccountingNote.dbo.Category"
			+ "	    LEFT JOIN AccountingNote.dbo.Accounting"
			+ "	    ON Category.CategoryName = Accounting.CategoryName"
			+ "	    WHERE CategoryID = ?1", nativeQuery = true)
	public Long categoryCount(int id);
}
