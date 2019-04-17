package com.tongansz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_schooluser")
public class SchoolUser {
	
	@Id
	@Column(name="user_id") 
	private int UserId; //主键 
	
	@Column(name="user_account")
	private String UserAccount; //账号

	@Column(name="user_pwd")
	private String UserPassword; //密码
	
	@Column(name="user_pwds")
	private String UserPasswords; //密码
	
	@Column(name="user_name")
	private String UserName; //用户昵称
	
	@Column(name="user_logintime")
	private String UserLoginTime; //登录时间
	
	@Column(name="user_creattime")
	private String UserCreatTime; //创建时间
	
	@Column(name="user_flag")
	private int UserFlag; //用户标志位，用于区分身份，-1代表未审核，1代表教育局管理员，0代表学校管理员
	
	@Column(name="user_tele")
	private String UserPhone; //用户电话
	
	@Column(name="user_email")
	private String UserEmail; //用户邮箱
	
	@Column(name="user_updatetime")
	private String UserUpdateTime; //更新时间
	
	@Column(name="school_id")
	private int SchoolId; //学校编号
	
	@Column(name="school_name")
	private String SchoolName; //学校名称
	
	public String getUserEmail() {
		return UserEmail;
	}

	public String getSchoolName() {
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserLoginTime() {
		return UserLoginTime;
	}

	public void setUserLoginTime(String userLoginTime) {
		UserLoginTime = userLoginTime;
	}

	public String getUserCreatTime() {
		return UserCreatTime;
	}

	public void setUserCreatTime(String userCreatTime) {
		UserCreatTime = userCreatTime;
	}

	public int getUserFlag() {
		return UserFlag;
	}

	public void setUserFlag(int userFlag) {
		UserFlag = userFlag;
	}

	public String getUserPhone() {
		return UserPhone;
	}

	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}

	public String getUserUpdateTime() {
		return UserUpdateTime;
	}

	public void setUserUpdateTime(String userUpdateTime) {
		UserUpdateTime = userUpdateTime;
	}


	public int getSchoolId() {
		return SchoolId;
	}

	public void setSchoolId(int schoolId) {
		SchoolId = schoolId;
	}

	
	
	public String getUserPasswords() {
		return UserPasswords;
	}

	public void setUserPasswords(String userPasswords) {
		UserPasswords = userPasswords;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("UserId", this.UserId);
		jo.put("UserAccount", this.UserAccount);
		jo.put("UserPassword", this.UserPassword);
		jo.put("UserName", this.UserName);
		jo.put("UserLoginTime", this.UserLoginTime);
		jo.put("UserCreatTime", this.UserCreatTime);
		jo.put("UserFlag", this.UserFlag);
		jo.put("UserPhone", this.UserPhone);
		jo.put("UserUpdateTime", this.UserUpdateTime);
		jo.put("UserEmail", this.UserEmail);
		jo.put("SchoolId", this.SchoolId);
		jo.put("SchoolName", this.SchoolName);
		jo.put("UserPasswords", this.UserPasswords);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
