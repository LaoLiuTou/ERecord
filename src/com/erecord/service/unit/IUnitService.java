package com.erecord.service.unit;
import java.util.List;
import java.util.Map;
import com.erecord.model.unit.Unit;
public interface IUnitService {
	/**
	* 通过id选取
	* @return
	*/
	public Unit selectUnitById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Unit> selectUnitByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountUnitByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateUnit(Unit unit);

	/**
	* 添加 
	* @return
	*/ 
	public int addUnit(Unit unit);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdUnit(List<Unit> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteUnit(String id);

}

