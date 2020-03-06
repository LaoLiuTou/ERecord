package com.erecord.service.checkpoint;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.erecord.dao.checkpoint.ICheckpointMapper;
import com.erecord.model.checkpoint.Checkpoint;
public class CheckpointServiceImpl  implements ICheckpointService {

	@Autowired
	private ICheckpointMapper iCheckpointMapper;
	/**
	* 通过id选取
	* @return
	*/
	public Checkpoint selectCheckpointById(String id){
		return iCheckpointMapper.selectcheckpointById(id);
	}

	/**
	* 通过查询参数获取信息
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public List<Checkpoint> selectCheckpointByParam(Map paramMap){ 
		return iCheckpointMapper.selectcheckpointByParam(paramMap);
	}

	/**
	* 通过查询参数获取总条数
	* @return
	*/ 
	@SuppressWarnings("rawtypes")
	public int selectCountCheckpointByParam(Map paramMap){ 
		return iCheckpointMapper.selectCountcheckpointByParam(paramMap);
	}

	/**
	* 更新 
	* @return 
	*/ 
	@Transactional
	public  int updateCheckpoint(Checkpoint checkpoint){
		return iCheckpointMapper.updatecheckpoint(checkpoint);
	}

	/**
	* 添加 
	* @return
	*/ 
	@Transactional
	public  int addCheckpoint(Checkpoint checkpoint){
		return iCheckpointMapper.addcheckpoint(checkpoint);
	}

	/**
	* 批量添加 
	* @return
	*/ 
	@Transactional
	public  int muladdCheckpoint(List<Checkpoint> list){
		return iCheckpointMapper.muladdcheckpoint(list);
	}

	/**
	* 删除 
	* @return 
	*/ 
	@Transactional
	public  int deleteCheckpoint(String id){
		return iCheckpointMapper.deletecheckpoint(id);
	}

}

