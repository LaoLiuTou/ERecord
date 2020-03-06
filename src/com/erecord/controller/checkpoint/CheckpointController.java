package com.erecord.controller.checkpoint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.erecord.service.checkpoint.ICheckpointService;
import com.erecord.model.checkpoint.Checkpoint;
import com.erecord.model.unit.Unit;
@Controller
public class CheckpointController {
	@Autowired
	private ICheckpointService iCheckpointService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("ERecordLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addCheckpoint")
	@ResponseBody
	public Map add(Checkpoint checkpoint){
		Map resultMap=new HashMap();
		try {
			iCheckpointService.addCheckpoint(checkpoint);
			resultMap.put("status", "0");
			resultMap.put("msg", checkpoint.getId());
			logger.info("新建成功，主键："+checkpoint.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdCheckpoint")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Checkpoint checkpoint){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Checkpoint.class);
			List<Checkpoint> list = (List<Checkpoint>)objectMapper.readValue(data, javaType);
			iCheckpointService.muladdCheckpoint(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+checkpoint.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteCheckpoint")
	@ResponseBody
	public Map delete(Checkpoint checkpoint){
		Map resultMap=new HashMap();
		try {
			if(checkpoint.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iCheckpointService.deleteCheckpoint(checkpoint.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+checkpoint.getId());
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "删除失败！");
			logger.info("删除失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/selectCheckpoint")
	@ResponseBody
	public Map select(Checkpoint checkpoint){
		Map resultMap=new HashMap();
		try {
			if(checkpoint.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Checkpoint resultSelect=iCheckpointService.selectCheckpointById(checkpoint.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", resultSelect);
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/updateCheckpoint")
	@ResponseBody
	public Map update(Checkpoint checkpoint){
		Map resultMap=new HashMap();
		try {
			if(checkpoint.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iCheckpointService.updateCheckpoint(checkpoint);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+checkpoint.getId());
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "更新失败！");
			logger.info("更新失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/listCheckpoint")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Checkpoint checkpoint)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String page=request.getParameter("page");
			String size=request.getParameter("size");
			if(page!=null&&size!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",(Integer.parseInt(page)-1)*Integer.parseInt(size));
				paramMap.put("toPage",Integer.parseInt(size)); 
				paramMap.put("orderBy","a.ID DESC"); 
				paramMap.put("id",checkpoint.getId());
				paramMap.put("name",checkpoint.getName());
				paramMap.put("comment",checkpoint.getComment());
				paramMap.put("unit_id",checkpoint.getUnit_id());
				paramMap.put("c_id",checkpoint.getC_id());
				String c_dtFrom=request.getParameter("c_dtFrom");
				String c_dtTo=request.getParameter("c_dtTo");
				if(c_dtFrom!=null&&!c_dtFrom.equals(""))
				paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
				if(c_dtTo!=null&&!c_dtTo.equals(""))
				paramMap.put("c_dtTo", sdf.parse(c_dtTo));
				String u_dtFrom=request.getParameter("u_dtFrom");
				String u_dtTo=request.getParameter("u_dtTo");
				if(u_dtFrom!=null&&!u_dtFrom.equals(""))
				paramMap.put("u_dtFrom", sdf.parse(u_dtFrom));
				if(u_dtTo!=null&&!u_dtTo.equals(""))
				paramMap.put("u_dtTo", sdf.parse(u_dtTo));
				paramMap.put("state",checkpoint.getState());
				String searchText=request.getParameter("searchText");
				if(searchText!=null&&!searchText.equals(""))
				paramMap.put("searchText",searchText);
				List<Checkpoint> list=iCheckpointService.selectCheckpointByParam(paramMap);
				int totalnumber=iCheckpointService.selectCountCheckpointByParam(paramMap);
				Map tempMap=new HashMap();
				resultMap.put("status", "0");
				tempMap.put("num", totalnumber);
				tempMap.put("data", list);
				resultMap.put("msg", tempMap);
			}
			else{
				resultMap.put("status", "-1");
				resultMap.put("msg", "分页参数不能为空！");
			}
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/allCheckpoint")
	@ResponseBody
	public Map all(HttpServletRequest request, HttpServletResponse response,Checkpoint checkpoint)
			throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			 
			Map paramMap=new HashMap();
			paramMap.put("orderBy","a.ID DESC"); 
			paramMap.put("id",checkpoint.getId());
			paramMap.put("name",checkpoint.getName());
			paramMap.put("comment",checkpoint.getComment());
			paramMap.put("unit_id",checkpoint.getUnit_id());
			paramMap.put("c_id",checkpoint.getC_id());
			String c_dtFrom=request.getParameter("c_dtFrom");
			String c_dtTo=request.getParameter("c_dtTo");
			if(c_dtFrom!=null&&!c_dtFrom.equals(""))
				paramMap.put("c_dtFrom", sdf.parse(c_dtFrom));
			if(c_dtTo!=null&&!c_dtTo.equals(""))
				paramMap.put("c_dtTo", sdf.parse(c_dtTo));
			String u_dtFrom=request.getParameter("u_dtFrom");
			String u_dtTo=request.getParameter("u_dtTo");
			if(u_dtFrom!=null&&!u_dtFrom.equals(""))
				paramMap.put("u_dtFrom", sdf.parse(u_dtFrom));
			if(u_dtTo!=null&&!u_dtTo.equals(""))
				paramMap.put("u_dtTo", sdf.parse(u_dtTo));
			paramMap.put("state",checkpoint.getState());
			String searchText=request.getParameter("searchText");
			if(searchText!=null&&!searchText.equals(""))
				paramMap.put("searchText",searchText);
			int totalnumber=iCheckpointService.selectCountCheckpointByParam(paramMap); 
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			List<Checkpoint> list=iCheckpointService.selectCheckpointByParam(paramMap);
			
			List<Map> tempList= new ArrayList<Map>();
			for(Checkpoint cp:list){
				Map tempMap=new HashMap();
				tempMap.put("id", cp.getId());
				tempMap.put("name", cp.getName());
				tempList.add(tempMap);
			}
			resultMap.put("status", "0"); 
			resultMap.put("msg", tempList);
			 
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
}
