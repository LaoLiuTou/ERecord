package com.erecord.service.checkpoint;
import java.util.List;
import java.util.Map;
import com.erecord.model.checkpoint.Checkpoint;
public interface ICheckpointService {
	/**
	* 通过id选取
	* @return
	*/
	public Checkpoint selectCheckpointById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Checkpoint> selectCheckpointByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCheckpointByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateCheckpoint(Checkpoint checkpoint);

	/**
	* 添加 
	* @return
	*/ 
	public int addCheckpoint(Checkpoint checkpoint);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdCheckpoint(List<Checkpoint> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteCheckpoint(String id);

}

