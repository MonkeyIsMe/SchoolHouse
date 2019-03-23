package com.csu.dao;

import java.util.List;

import com.csu.model.Building;

public interface BuildingDAO {

	public boolean add(Building building);
	
	public boolean update(Building building);
	
	public List<Building> getAllBuilding();
	
	public boolean delete(Building building);
	
	public Building query(int id);
	
	public List<Building> getAllBuildingByPageSize(int i,int PageSize);
}
