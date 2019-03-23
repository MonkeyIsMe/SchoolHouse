package com.csu.dao;

import java.util.List;

import com.csu.model.House;

public interface HouseDAO {
	
	public boolean add(House house);
	
	public House query(int id);
	
	public boolean delete(House house);
	
	public boolean update(House house);
	
	public List<House> getAllHouse();
	
	public List<House> getAllHouseByPageSize(int i,int PageSize);
}
