package com.erecord.service.unit;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.erecord.dao.unit.IUnitMapper;
import com.erecord.model.unit.Unit;
public class UnitServiceImpl  implements IUnitService {

	@Autowired
	private IUnitMapper iUnitMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Unit selectUnitById(String id){
		return iUnitMapper.selectunitById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Unit> selectUnitByParam(Map paramMap){ 
		return iUnitMapper.selectunitByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountUnitByParam(Map paramMap){ 
		return iUnitMapper.selectCountunitByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateUnit(Unit unit){
		return iUnitMapper.updateunit(unit);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addUnit(Unit unit){
		return iUnitMapper.addunit(unit);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdUnit(List<Unit> list){
		return iUnitMapper.muladdunit(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteUnit(String id){
		return iUnitMapper.deleteunit(id);
	}

}

