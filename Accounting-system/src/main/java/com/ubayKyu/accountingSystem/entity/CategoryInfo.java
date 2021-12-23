package com.ubayKyu.accountingSystem.entity;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.catalina.Session;
import org.apache.catalina.connector.Request;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="CATEGORY")
public class CategoryInfo {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int CATEGORYID;
	
	@NotEmpty(message = "請輸入分類標題名稱")
    @Size(max = 20, message = "輸入字數限制為20字")
	public String CATEGORYNAME;
	
	public String USERID;
	
	@Column(nullable = false)
	public LocalDate CREATEDATE;
	
	@PrePersist
	private void onCreate(){
		CREATEDATE = LocalDate.now(); 
	}

	@Column(nullable = true)
	public String Body;
	
	public int getCATEGORYID() {
		return CATEGORYID;
	}
	public void setCATEGORYID(int cATEGORYID) {
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
