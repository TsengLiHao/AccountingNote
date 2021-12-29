package com.ubayKyu.accountingSystem.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="USERSINFO")
public class UserInfo {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public String ID;
	
	@NotEmpty(message = "請輸入帳號")
	@Size(max = 20, message = "輸入字數限制為20字")
	public String Account; 
	//@Column(name="PWD", length=50, nullable=false, unique=false)
	public String PWD;
	//@Column(name="NAME", length=50, nullable=false, unique=false)
	@NotEmpty(message = "請輸入姓名")
	@Size(max = 20, message = "輸入字數限制為20字")
	public String Name;
	//@Column(name="EMAIL", length=50, nullable=false, unique=false)
	@NotEmpty(message = "請輸入Email")
	@Size(max = 100, message = "輸入字數限制為100字")
	public String Email;
	//@Column(name="USER_LEVEL", length=50, nullable=false, unique=false)
	public Integer USERLEVEL;
	//@Column(name="CREATE_DATE", length=50, nullable=false, unique=false)
	
	@Column(nullable = false)
	public LocalDateTime CREATEDATE;
	
	@PrePersist
	private void onCreate(){
		CREATEDATE = LocalDateTime.now();
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public String getPWD() {
		return PWD;
	}
	public void setPWD(String pWD) {
		PWD = pWD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Integer getUSERLEVEL() {
		return USERLEVEL;
	}
	public void setUSERLEVEL(Integer uSERLEVEL) {
		USERLEVEL = uSERLEVEL;
	}
	public LocalDateTime getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(LocalDateTime cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	
}
