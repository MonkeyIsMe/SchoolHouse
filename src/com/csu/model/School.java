package com.csu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_school")
public class School {

	@Id
	@Column(name="school_id")
	private int SchoolId;  //主键
	
	@Column(name="school_name")
	private String SchoolName;  //学校名称
	
	@Column(name="school_address")
	private String SchoolAddress;  //学校地址
	
	@Column(name="school_street")
	private String SchoolStreet; //所属街道
	
	@Column(name="school_legal")
	private String SchoolIegalPerson;  //法人代表
	
	@Column(name="school_tele") 
	private String SchoolPhone;  //学校联系电话
	
	@Column(name="school_creattime")
	private String SchoolCreatTime; //创建时间
	
	@Column(name="area_id")
	private int AreaId; //所属街道
	
	@Column(name="area_name")
	private String AreaName; //所属学区名
	
	
	public int getSchoolId() {
		return SchoolId;
	}

	public void setSchoolId(int schoolId) {
		SchoolId = schoolId;
	}

	public String getSchoolName() {
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	public String getSchoolAddress() {
		return SchoolAddress;
	}

	public void setSchoolAddress(String schoolAddress) {
		SchoolAddress = schoolAddress;
	}

	public String getSchoolStreet() {
		return SchoolStreet;
	}

	public void setSchoolStreet(String schoolStreet) {
		SchoolStreet = schoolStreet;
	}

	public String getSchoolIegalPerson() {
		return SchoolIegalPerson;
	}

	public void setSchoolIegalPerson(String schoolIegalPerson) {
		SchoolIegalPerson = schoolIegalPerson;
	}

	public String getSchoolPhone() {
		return SchoolPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		SchoolPhone = schoolPhone;
	}

	public int getAreaId() {
		return AreaId;
	}

	public void setAreaId(int areaId) {
		AreaId = areaId;
	}

	public String getSchoolCreatTime() {
		return SchoolCreatTime;
	}

	public void setSchoolCreatTime(String schoolCreatTime) {
		SchoolCreatTime = schoolCreatTime;
	}
	public String getAreaName() {
		return AreaName;
	}

	public void setAreaName(String areaName) {
		AreaName = areaName;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("SchoolId", this.SchoolId);
		jo.put("SchoolName", this.SchoolName);
		jo.put("SchoolAddress", this.SchoolAddress);
		jo.put("SchoolStreet", this.SchoolStreet);
		jo.put("SchoolIegalPerson", this.SchoolIegalPerson);
		jo.put("SchoolPhone", this.SchoolPhone);
		jo.put("CreatTime", this.SchoolCreatTime);
		jo.put("AreaId", this.AreaId);
		jo.put("AreaName", this.AreaName);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
	
}
