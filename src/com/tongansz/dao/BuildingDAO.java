package com.tongansz.dao;

import java.util.List;

import com.tongansz.model.Building;

public interface BuildingDAO {

	public boolean add(Building building);
	
	public boolean update(Building building);
	
	public List<Building> getAllBuilding();
	
	public boolean delete(Building building);
	
	public Building query(int id);
	
	public List<Building> getAllBuildingByPageSize(int i,int PageSize);
	
	public List<Building> getBuildingBySchoolId(int SchoolId);
	
	public List<Building> getBuildingByAreaId(int area_id);
	
	public List<Building> getBuildingBySchoolByName(String building_name);
}
