package com.tongansz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

@Entity
@Table(name="tab_house")
public class House {
	
	@Id
	@Column(name="house_id")
	private int HouseId; //主键
	
	@Column(name="buding_id")
	private int BuildingId; //楼盘号
	
	@Column(name="house_name")
	private String HouseName; //地址
	
	@Column(name="house_room")
	private String HouseRoom; //房号
	
	@Column(name="house_creattime")
	private String HouseCreatTime; //创建时间
	
	@Column(name="house_usetime")
	private String HouseUseTime; //房子使用时间
	
	@Column(name="building_name")
	private String BuildingName; //创建时间
	
	
	public String getHouseUseTime() {
		return HouseUseTime;
	}

	public void setHouseUseTime(String houseUseTime) {
		HouseUseTime = houseUseTime;
	}

	public int getHouseId() {
		return HouseId;
	}

	public void setHouseId(int houseId) {
		HouseId = houseId;
	}

	public int getBuildingId() {
		return BuildingId;
	}

	public void setBuildingId(int buildingId) {
		BuildingId = buildingId;
	}

	public String getHouseName() {
		return HouseName;
	}

	public void setHouseName(String houseName) {
		HouseName = houseName;
	}


	public String getHouseRoom() {
		return HouseRoom;
	}

	public void setHouseRoom(String houseRoom) {
		HouseRoom = houseRoom;
	}

	public String getHouseCreatTime() {
		return HouseCreatTime;
	}

	public void setHouseCreatTime(String houseCreatTime) {
		HouseCreatTime = houseCreatTime;
	}

	
	public String getBuildingName() {
		return BuildingName;
	}

	public void setBuildingName(String buildingName) {
		BuildingName = buildingName;
	}


	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		jo.put("HouseId", this.HouseId);
		jo.put("HouseCreatTime", this.HouseCreatTime);
		jo.put("HouseRoom", this.HouseRoom);
		jo.put("HouseName", this.HouseName);
		jo.put("HouseRoom", this.HouseRoom);
		jo.put("HouseUseTime", this.HouseUseTime);
		jo.put("BuildingName", this.BuildingName);
		return jo;
	}
	
	public String toString() {
		return this.toJSON().toString(); 
	} 
}
