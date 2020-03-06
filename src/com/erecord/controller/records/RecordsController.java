package com.erecord.controller.records;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erecord.model.records.Records;
import com.erecord.service.records.IRecordsService;
import com.erecord.utils.ExcelUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class RecordsController {
	@Autowired
	private IRecordsService iRecordsService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Logger logger = Logger.getLogger("ERecordLogger");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addRecords")
	@ResponseBody
	public Map add(Records records){
		Map resultMap=new HashMap();
		try {
			iRecordsService.addRecords(records);
			resultMap.put("status", "0");
			resultMap.put("msg", records.getId());
			logger.info("新建成功，主键："+records.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/muladdRecords")
	@ResponseBody
	public Map muladd(HttpServletRequest request,Records records){
		Map resultMap=new HashMap();
		try {
			String data=request.getParameter("data");
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Records.class);
			List<Records> list = (List<Records>)objectMapper.readValue(data, javaType);
			iRecordsService.muladdRecords(list);
			resultMap.put("status", "0");
			resultMap.put("msg", "新建成功");
			logger.info("新建成功，主键："+records.getId());
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "新建失败！");
			logger.info("新建失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/deleteRecords")
	@ResponseBody
	public Map delete(Records records){
		Map resultMap=new HashMap();
		try {
			if(records.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultDelete=iRecordsService.deleteRecords(records.getId()+"");
				resultMap.put("status", "0");
				resultMap.put("msg", "删除成功！");
				logger.info("删除成功，主键："+records.getId());
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
	@RequestMapping("/selectRecords")
	@ResponseBody
	public Map select(Records records){
		Map resultMap=new HashMap();
		try {
			if(records.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				Records resultSelect=iRecordsService.selectRecordsById(records.getId()+"");
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
	@RequestMapping("/updateRecords")
	@ResponseBody
	public Map update(Records records){
		Map resultMap=new HashMap();
		try {
			if(records.getId()==null){
				resultMap.put("status", "-1");
				resultMap.put("msg", "参数不能为空！");
			}
			else{
				int resultUpdate=iRecordsService.updateRecords(records);
				resultMap.put("status", "0");
				resultMap.put("msg", "更新成功！");
				logger.info("更新成功，主键："+records.getId());
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
	@RequestMapping("/listRecords")
	@ResponseBody
	public Map list(HttpServletRequest request, HttpServletResponse response,Records records)
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
				paramMap.put("id",records.getId());
				paramMap.put("name",records.getName());
				paramMap.put("idnumber",records.getIdnumber());
				paramMap.put("img",records.getImg());
				paramMap.put("phone",records.getPhone());
				paramMap.put("inorout",records.getInorout());
				paramMap.put("cp_id",records.getCp_id());
				paramMap.put("unit_id",records.getUnit_id());
				String dtFrom=request.getParameter("dtFrom");
				String dtTo=request.getParameter("dtTo");
				if(dtFrom!=null&&!dtFrom.equals(""))
				paramMap.put("dtFrom", sdf.parse(dtFrom));
				if(dtTo!=null&&!dtTo.equals(""))
				paramMap.put("dtTo", sdf.parse(dtTo));
				paramMap.put("c_id",records.getC_id());
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
				paramMap.put("state",records.getState());
				String searchText=request.getParameter("searchText");
				if(searchText!=null&&!searchText.equals(""))
				paramMap.put("searchText",searchText);
				List<Records> list=iRecordsService.selectRecordsByParam(paramMap);
				int totalnumber=iRecordsService.selectCountRecordsByParam(paramMap);
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
	@RequestMapping("/exportRecords")
	@ResponseBody
	public void exportRecords(HttpServletRequest request, HttpServletResponse response,Records records)
		throws ServletException, IOException {
		Map resultMap=new HashMap();
		try {
			 
			Map paramMap=new HashMap();
			
			paramMap.put("orderBy","a.ID DESC"); 
			paramMap.put("id",records.getId());
			paramMap.put("name",records.getName());
			paramMap.put("idnumber",records.getIdnumber());
			paramMap.put("img",records.getImg());
			paramMap.put("phone",records.getPhone());
			paramMap.put("inorout",records.getInorout());
			paramMap.put("cp_id",records.getCp_id());
			paramMap.put("unit_id",records.getUnit_id());
			String dtFrom=request.getParameter("dtFrom");
			String dtTo=request.getParameter("dtTo");
			if(dtFrom!=null&&!dtFrom.equals(""))
			paramMap.put("dtFrom", sdf.parse(dtFrom));
			if(dtTo!=null&&!dtTo.equals(""))
			paramMap.put("dtTo", sdf.parse(dtTo));
			paramMap.put("c_id",records.getC_id());
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
			paramMap.put("state",records.getState());
			String searchText=request.getParameter("searchText");
			if(searchText!=null&&!searchText.equals(""))
			paramMap.put("searchText",searchText);
			int totalnumber=iRecordsService.selectCountRecordsByParam(paramMap);
			paramMap.put("fromPage",0);
			paramMap.put("toPage",totalnumber); 
			List<Records> list=iRecordsService.selectRecordsByParam(paramMap);
			  
			List<String[]> exportList = new ArrayList<String[]>();
			 //姓名	 身份证号码	 电话	 出入	 公司	 卡点	 检查员	 创建日期	 备注
			 
			for(int index=0;index<list.size();index++){
				Records temp = list.get(index); 
				String[] strings = {(index+1)+"", temp.getName(), temp.getIdnumber(), temp.getPhone(),
						temp.getInorout(),temp.getUnit_name(), temp.getCp_name(),
						temp.getC_name()+"("+temp.getC_username()+")",
						sdf.format(temp.getDt()),temp.getComment() };
				exportList.add(strings);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			ServletOutputStream out=response.getOutputStream();
			String fileName = "居民出入数据"+sdf1.format(new Date());
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
			String[] titles = { "序号","姓名","身份证号码","电话","出入", "公司", "卡点", "检查员", "创建日期", "备注"}; 
		 
			
			 
			ExcelUtil.export(titles, out, exportList);
				
				
			 
		} catch (Exception e) {
			resultMap.put("status", "-1");
			resultMap.put("msg", "查询失败！");
			logger.info("查询失败！"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		 
	}
	
}
