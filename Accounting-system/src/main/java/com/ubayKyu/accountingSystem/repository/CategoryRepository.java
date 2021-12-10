package com.ubayKyu.accountingSystem.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ubayKyu.accountingSystem.entity.CategoryInfo;

public interface CategoryRepository
		extends JpaRepository<CategoryInfo, Integer> {

	@Query(value = "SELECT CategoryID,Category.UserID,Category.CreateDate , Category.CategoryName, Category.Body ,COUNT(Accounting.CategoryName) as Count"
			+ "     FROM AccountingNote.dbo.Category"
			+ "     LEFT JOIN AccountingNote.dbo.Accounting"
			+ "     ON Category.CategoryName = Accounting.CategoryName"
			+ "     GROUP BY CategoryID,Category.UserID,Category.CreateDate, Category.CategoryName, Category.Body", nativeQuery = true)
	public List<Map<String,Object>> category();

}
