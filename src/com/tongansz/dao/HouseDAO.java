package com.tongansz.dao;

import java.util.List;

import com.tongansz.model.House;

public interface HouseDAO {
	
	public boolean add(House house);
	
	public House query(int id);
	
	public boolean delete(House house);
	
	public boolean update(House house);
	
	public List<House> getAllHouse();
	
	public List<House> getHouseByBuilding(int BuildingId);
	
	public List<House> getHouseByName(String buding_id,String house_name,String house_room);
	
	public List<House> getAllHouseByPageSize(int i,int PageSize);
}
