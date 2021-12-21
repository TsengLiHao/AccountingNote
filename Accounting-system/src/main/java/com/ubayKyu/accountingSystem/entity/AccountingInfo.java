package com.ubayKyu.accountingSystem.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(schema="dbo", name="ACCOUNTING")
public class AccountingInfo {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer ID;
	
	public String USERID;
	public String Caption;
	public Integer Amount;
	public Integer ACTTYPE;
	public Date CREATEDATE;
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
