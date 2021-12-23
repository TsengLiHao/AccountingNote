package com.ubayKyu.accountingSystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(schema="dbo", name="ACCOUNTING")
public class AccountingInfo {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int ID;
	
	public String USERID;
	
	@NotEmpty(message = "請輸入標題名稱")
	public String Caption;
	
	@Min(value = 1, message="輸入金額不可小於1")
	@Max(value = 10000000, message="輸入金額不可大於1千萬")
	@Column(nullable = false)
	@NotNull(message = "請輸入金額")
	public int Amount;
	
	public Integer ACTTYPE;
	
	@Column(nullable = false)
	public LocalDateTime CREATEDATE;
	
	@PrePersist
	private void onCreate(){
		CREATEDATE = LocalDateTime.now();
	}
	
	public String Body;
	public String CATEGORYNAME;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getCaption() {
		return Caption;
	}
	public void setCaption(String caption) {
		Caption = caption;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public Integer getACTTYPE() {
		return ACTTYPE;
	}
	public void setACTTYPE(Integer aCTTYPE) {
		ACTTYPE = aCTTYPE;
	}
	public LocalDateTime getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(LocalDateTime cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	public String getCATEGORYNAME() {
		return CATEGORYNAME;
	}
	public void setCATEGORYNAME(String cATEGORYNAME) {
		CATEGORYNAME = cATEGORYNAME;
	}
	
	
}
