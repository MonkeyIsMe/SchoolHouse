package com.csu.dao;

import java.util.List;

import com.csu.model.Area;

public interface AreaDAO {
	
	public boolean add(Area area);
	
	public boolean update(Area area);
	
	public List<Area> getAllArea();
	
	public boolean delete(Area area);
	
	public Area query(int id);
	
	public List<Area> GetAllByPageSize(int i,int PageSize);
}
