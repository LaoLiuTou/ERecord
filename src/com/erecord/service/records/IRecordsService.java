package com.erecord.service.records;
import java.util.List;
import java.util.Map;
import com.erecord.model.records.Records;
public interface IRecordsService {
	/**
	* 通过id选取
	* @return
	*/
	public Records selectRecordsById(String id);

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Records> selectRecordsByParam(Map paramMap); 

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountRecordsByParam(Map paramMap); 

	/**
	* 更新 
	* @return 
	*/ 
	public int updateRecords(Records records);

	/**
	* 添加 
	* @return
	*/ 
	public int addRecords(Records records);

	/**
	* 批量添加 
	* @return
	*/ 
	public int muladdRecords(List<Records> list);

	/**
	* 删除 
	* @return 
	*/ 
	public int deleteRecords(String id);

}

