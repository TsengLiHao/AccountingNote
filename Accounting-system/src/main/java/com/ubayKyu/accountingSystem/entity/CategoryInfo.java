package com.ubayKyu.accountingSystem.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class CategoryInfo {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer CATEGORYID;
	
	public String CATEGORYNAME; 
	public String USERID;
	public LocalDate CREATEDATE;
	public String Body;
	public Integer getCATEGORYID() {
		return CATEGORYID;
	}
	public void setCATEGORYID(Integer cATEGORYID) {
		CATEGORYID = cATEGORYID;
	}
	public String getCATEGORYNAME() {
		return CATEGORYNAME;
	}
	public void setCATEGORYNAME(String cATEGORYNAME) {
		CATEGORYNAME = cATEGORYNAME;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public LocalDate getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(LocalDate cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	public String getBody() {
		return Body;
	}
	public void setBody(String body) {
		Body = body;
	}
	
}
