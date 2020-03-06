package com.erecord.dao.checkpoint;
import java.util.List;
import java.util.Map;
import com.erecord.model.checkpoint.Checkpoint;
	public interface ICheckpointMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Checkpoint selectcheckpointById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Checkpoint> selectcheckpointByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountcheckpointByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updatecheckpoint(Checkpoint checkpoint);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addcheckpoint(Checkpoint checkpoint);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdcheckpoint(List<Checkpoint> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deletecheckpoint(String id);

}

