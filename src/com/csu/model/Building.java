package com.csu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_building")
public class Building {
	
	@Id
	@Column(name="building_id")
	private int BuildingId;  //主键
	
	@Column(name="building_name")
	private String BuildingName;  //楼盘名称
	
	@Column(name="building_address")
	private String BuildingAddress;  //楼盘地址
	
	@Column(name="building_street")
	private String BuildingStreet; //楼盘所属街道
	
	@Column(name="building_time")
	private String BuildingCreatTime; //创建时间
	
	@Column(name="area_id")
	private int AreaId; //所属学区
	
	
	
	public int getBuildingId() {
		return BuildingId;
	}

	public void setBuildingId(int buildingId) {
		BuildingId = buildingId;
	}

	public String getBuildingName() {
		return BuildingName;
	}

	public void setBuildingName(String buildingName) {
		BuildingName = buildingName;
	}

	public String getBuildingAddress() {
		return BuildingAddress;
	}

	public void setBuildingAddress(String buildingAddress) {
		BuildingAddress = buildingAddress;
	}

	public String getBuildingStreet() {
		return BuildingStreet;
	}

	public void setBuildingStreet(String buildingStreet) {
		BuildingStreet = buildingStreet;
	}

	public String getBuildingCreatTime() {
		return BuildingCreatTime;
	}

	public void setBuildingCreatTime(String buildingCreatTime) {
		BuildingCreatTime = buildingCreatTime;
	}

	public int getAreaId() {
		return AreaId;
	}

	public void setAreaId(int areaId) {
		AreaId = areaId;
	}

	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("BuildingId", this.BuildingId);
		jo.put("BuildingName", this.BuildingName);
		jo.put("BuildingAddress", this.BuildingAddress);
		jo.put("BuildingStreet", this.BuildingStreet);
		jo.put("BuildingTime", this.BuildingCreatTime);
		jo.put("AreaId", this.AreaId);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
