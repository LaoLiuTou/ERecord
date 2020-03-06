package com.erecord.controller.unit;
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
import com.erecord.service.unit.IUnitService;
import com.erecord.model.checkpoint.Checkpoint;
import com.erecord.model.unit.Unit;
@Controller
public class UnitController {
	@Autowired
	private IUnitService iUnitService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("ERecordLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addUnit")
	@ResponseBody
	public Map add(Unit unit){
		Map resultMap=new HashMap();
		try {
			iUnitService.addUnit(unit);
			resultMap.put("status", "0");
			resultMap.put("msg", unit.getId());
			logger.info("新建成功，主键："+unit.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdUnit")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Unit unit){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Unit.class);
			List<Unit> list = (List<Unit>)objectMapper.readValue(data, javaType);
			iUnitService.muladdUnit(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+unit.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteUnit")
	@ResponseBody
	public Map delete(Unit unit){
		Map resultMap=new HashMap();
		try {
			if(unit.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iUnitService.deleteUnit(unit.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+unit.getId());
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
	@RequestMapping("/selectUnit")
	@ResponseBody
	public Map select(Unit unit){
		Map resultMap=new HashMap();
		try {
			if(unit.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Unit resultSelect=iUnitService.selectUnitById(unit.getId()+"");
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
	@RequestMapping("/updateUnit")
	@ResponseBody
	public Map update(Unit unit){
		Map resultMap=new HashMap();
		try {
			if(unit.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iUnitService.updateUnit(unit);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+unit.getId());
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
	@RequestMapping("/listUnit")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Unit unit)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			String page=request.getParameter("page");
			String size=request.getParameter("size");
			if(page!=null&&size!=null){
				Map paramMap=new HashMap();
				paramMap.put("fromPage",(Integer.parseInt(page)-1)*Integer.parseInt(size));
				paramMap.put("toPage",Integer.parseInt(size)); 
				paramMap.put("orderBy","ID DESC"); 
				paramMap.put("id",unit.getId());
				paramMap.put("name",unit.getName());
				paramMap.put("phone",unit.getPhone());
				paramMap.put("comment",unit.getComment());
				paramMap.put("count",unit.getCount());
				paramMap.put("c_id",unit.getC_id());
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
				paramMap.put("state",unit.getState());
				List<Unit> list=iUnitService.selectUnitByParam(paramMap);
				int totalnumber=iUnitService.selectCountUnitByParam(paramMap);
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
	@RequestMapping("/allUnit")
	@ResponseBody
	public Map all(HttpServletRequest request, HttpServletResponse response,Unit unit)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			Map paramMap=new HashMap();
			paramMap.put("orderBy","ID DESC"); 
			paramMap.put("id",unit.getId());
			paramMap.put("name",unit.getName());
			paramMap.put("phone",unit.getPhone());
			paramMap.put("comment",unit.getComment());
			paramMap.put("count",unit.getCount());
			paramMap.put("c_id",unit.getC_id());
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
			paramMap.put("state",unit.getState());
			int totalnumber=iUnitService.selectCountUnitByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			
			List<Unit> list=iUnitService.selectUnitByParam(paramMap);
			
			List<Map> tempList= new ArrayList<Map>();
			for(Unit u:list){
				Map tempMap=new HashMap();
				tempMap.put("id", u.getId());
				tempMap.put("name", u.getName());
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
