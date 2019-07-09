package com.tongansz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_id")
	private int StudentId;  //主键
	
	@Column(name="student_name")
	private String StudentName;  //学生姓名

	@Column(name="student_idcard")
	private String StudentIdCard;  //学生身份证号码
	
	@Column(name="student_father")
	private String StudentFather; //父亲姓名
	
	@Column(name="student_mother")
	private String StudentMother;  //母亲姓名
	
	@Column(name="student_houseowner")
	private String StudentHouseOwner; //业主姓名
	
	@Column(name="student_ownerid")
	private String StudentOwnerId; //业主身份证
	
	@Column(name="student_relation")
	private String StudentRelation; //与业主关系
	
	@Column(name="student_housecard")
	private String StudentHouseCard; //房产证号

	@Column(name="student_houseprecard")
	private String StudentHousePreCard; //房屋预告登记号
	
	@Column(name="student_budingid")
	private String  StudentBuildingId; //所属楼盘
	
	@Column(name="student_housenum")
	private String StudentHouseNumber; //楼号
	
	@Column(name="student_valid")
	private int StudentValid; //是否有效
	
	@Column(name="student_school")
	private String StudentSchool; //所属学校
	
	@Column(name="student_tele")
	private String StudentPhone; //学生联系电话
	
	@Column(name="student_preschool")
	private String StudentPreSchool; //毕业小学
	
	@Column(name="student_creattime")
	private String StudentCreatTime;  //创建日期
	
	@Column(name="student_roomid")
	private String StudentRoomId; //房号
	
	@Column(name="student_usetime")
	private String StudentUseTime; //房号
	

	public int getStudentId() {
		return StudentId;
	}

	public void setStudentId(int studentId) {
		StudentId = studentId;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public String getStudentIdCard() {
		return StudentIdCard;
	}

	public void setStudentIdCard(String studentIdCard) {
		StudentIdCard = studentIdCard;
	}

	public String getStudentFather() {
		return StudentFather;
	}

	public void setStudentFather(String studentFather) {
		StudentFather = studentFather;
	}

	public String getStudentMother() {
		return StudentMother;
	}

	public void setStudentMother(String studentMother) {
		StudentMother = studentMother;
	}

	public String getStudentHouseOwner() {
		return StudentHouseOwner;
	}

	public void setStudentHouseOwner(String studentHouseOwner) {
		StudentHouseOwner = studentHouseOwner;
	}

	public String getStudentOwnerId() {
		return StudentOwnerId;
	}

	public void setStudentOwnerId(String studentOwnerId) {
		StudentOwnerId = studentOwnerId;
	}

	public String getStudentRelation() {
		return StudentRelation;
	}

	public void setStudentRelation(String studentRelation) {
		StudentRelation = studentRelation;
	}

	public String getStudentHouseCard() {
		return StudentHouseCard;
	}

	public void setStudentHouseCard(String studentHouseCard) {
		StudentHouseCard = studentHouseCard;
	}

	public String getStudentHousePreCard() {
		return StudentHousePreCard;
	}

	public void setStudentHousePreCard(String studentHousePreCard) {
		StudentHousePreCard = studentHousePreCard;
	}

	public String getStudentBuildingId() {
		return StudentBuildingId;
	}

	public void setStudentBuildingId(String studentBuildingId) {
		StudentBuildingId = studentBuildingId;
	}

	public String getStudentHouseNumber() {
		return StudentHouseNumber;
	}

	public void setStudentHouseNumber(String studentHouseNumber) {
		StudentHouseNumber = studentHouseNumber;
	}

	public int getStudentValid() {
		return StudentValid;
	}

	public void setStudentValid(int studentValid) {
		StudentValid = studentValid;
	}

	public String getStudentSchool() {
		return StudentSchool;
	}

	public void setStudentSchool(String studentSchool) {
		StudentSchool = studentSchool;
	}

	public String getStudentPhone() {
		return StudentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		StudentPhone = studentPhone;
	}

	public String getStudentPreSchool() {
		return StudentPreSchool;
	}

	public void setStudentPreSchool(String studentPreSchool) {
		StudentPreSchool = studentPreSchool;
	}

	public String getStudentCreatTime() {
		return StudentCreatTime;
	}

	public void setStudentCreatTime(String studentCreatTime) {
		StudentCreatTime = studentCreatTime;
	}

	public String getStudentRoomId() {
		return StudentRoomId;
	}

	public void setStudentRoomId(String studentRoomId) {
		StudentRoomId = studentRoomId;
	}

	
	public String getStudentUseTime() {
		return StudentUseTime;
	}

	public void setStudentUseTime(String studentUseTime) {
		StudentUseTime = studentUseTime;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("StudentId", this.StudentId);
		jo.put("StudentName", this.StudentName);
		jo.put("StudentIdCard", this.StudentIdCard);
		jo.put("StudentFather", this.StudentFather);
		jo.put("StudentMother", this.StudentMother);
		jo.put("StudentHouseOwner", this.StudentHouseOwner);
		jo.put("StudentOwnerId", this.StudentOwnerId);
		jo.put("StudentRelation", this.StudentRelation);
		jo.put("StudentHouseCard", this.StudentHouseCard);
		jo.put("StudentHousePreCard", this.StudentHousePreCard);
		jo.put("StudentBuildingId", this.StudentBuildingId);
		jo.put("StudentHouseNumber", this.StudentHouseNumber);
		jo.put("StudentValid", this.StudentValid);
		jo.put("StudentSchool", this.StudentSchool);
		jo.put("StudentPhone", this.StudentPhone);
		jo.put("StudentPreSchool", this.StudentPreSchool);
		jo.put("StudentCreatTime", this.StudentCreatTime);
		jo.put("StudentRoomId", this.StudentRoomId);
		jo.put("StudentUseTime", this.StudentUseTime);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
