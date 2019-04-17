package com.tongansz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_area")
public class Area {
	
	@Id
	@Column(name="area_id")
	private int AreaId;  //主键
	
	@Column(name="area_creattime")
	private String AreaCreatTime;  //学区创建时间
	
	@Column(name="area_name")
	private String AreaName;  //学区名称
	
	
	
	public int getAreaId() {
		return AreaId;
	}

	public void setAreaId(int areaId) {
		AreaId = areaId;
	}
	
	public String getAreaCreatTime() {
		return AreaCreatTime;
	}

	public void setAreaCreatTime(String areaCreatTime) {
		AreaCreatTime = areaCreatTime;
	}

	public String getAreaName() {
		return AreaName;
	}

	public void setAreaName(String areaName) {
		AreaName = areaName;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("AreaId", this.AreaId);
		jo.put("AreaCreatTime", this.AreaCreatTime);
		jo.put("AreaName", this.AreaName);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
