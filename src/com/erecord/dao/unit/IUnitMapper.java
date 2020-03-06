package com.erecord.dao.unit;
import java.util.List;
import java.util.Map;
import com.erecord.model.unit.Unit;
	public interface IUnitMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Unit selectunitById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Unit> selectunitByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountunitByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updateunit(Unit unit);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addunit(Unit unit);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdunit(List<Unit> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleteunit(String id);

}

