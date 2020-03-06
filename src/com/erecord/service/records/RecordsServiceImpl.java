package com.erecord.service.records;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.erecord.dao.records.IRecordsMapper;
import com.erecord.model.records.Records;
public class RecordsServiceImpl  implements IRecordsService {

	@Autowired
	private IRecordsMapper iRecordsMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Records selectRecordsById(String id){
		return iRecordsMapper.selectrecordsById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Records> selectRecordsByParam(Map paramMap){ 
		return iRecordsMapper.selectrecordsByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountRecordsByParam(Map paramMap){ 
		return iRecordsMapper.selectCountrecordsByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateRecords(Records records){
		return iRecordsMapper.updaterecords(records);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addRecords(Records records){
		return iRecordsMapper.addrecords(records);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdRecords(List<Records> list){
		return iRecordsMapper.muladdrecords(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteRecords(String id){
		return iRecordsMapper.deleterecords(id);
	}

}

