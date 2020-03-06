package com.erecord.dao.records;
import java.util.List;
import java.util.Map;
import com.erecord.model.records.Records;
	public interface IRecordsMapper {
	/**
 	* 通过id选取
 	* @return
 	*/
	public Records selectrecordsById(String id);
	/**
 	* 通过查询参数获取信息
 	* @return
 */ 
 @SuppressWarnings("rawtypes")
	public List<Records> selectrecordsByParam(Map paramMap); 
	/**
		* 通过查询参数获取总条数
	    * @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountrecordsByParam(Map paramMap); 
	/**
 	* 更新 
 	* @return 
 */ 
	public  int updaterecords(Records records);
	/**
 	* 添加 
 	* @return
 */ 
	public  int addrecords(Records records);
	/**
 	* 批量添加 
 	* @return
 */ 
	public  int muladdrecords(List<Records> list);
	/**
 	* 删除 
 	* @return 
 */ 
public  int deleterecords(String id);

}

