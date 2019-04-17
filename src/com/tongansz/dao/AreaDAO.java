package com.tongansz.dao;

import java.util.List;

import com.tongansz.model.Area;

public interface AreaDAO {
	
	public boolean add(Area area);
	
	public boolean update(Area area);
	
	public List<Area> getAllArea();
	
	public boolean delete(Area area);
	
	public Area query(int id);
	
	public List<Area> GetAllByPageSize(int i,int PageSize);
	
	public List<Area> GetByName(String AreaName);
}
