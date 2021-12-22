package com.ubayKyu.accountingSystem.entity;

import java.time.LocalDate;
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

import lombok.Data;

@Data
@Entity
@Table(schema="dbo", name="ACCOUNTING")
public class AccountingInfo {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer ID;
	
	public String USERID;
	
	@NotEmpty(message = "請輸入標題名稱")
	public String Caption;
	
	@NotEmpty(message = "請輸入金額")
	@Min(value = 1, message="輸入金額不可小於1")
	@Max(value = 10000000, message="輸入金額不可大於1千萬")
	public Integer Amount;
	
	public Integer ACTTYPE;
	
	@Column(nullable = false)
	public Date CREATEDATE;
	
	@PrePersist
	private void onCreate(){
		CREATEDATE = new Date();
	}
	
	public String Body;
	public String CATEGORYNAME;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
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
	public Integer getAmount() {
		return Amount;
	}
	public void setAmount(Integer amount) {
		Amount = amount;
	}
	public Integer getACTTYPE() {
		return ACTTYPE;
	}
	public void setACTTYPE(Integer aCTTYPE) {
		ACTTYPE = aCTTYPE;
	}
	public Date getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(Date cREATEDATE) {
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
