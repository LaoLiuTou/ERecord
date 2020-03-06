package com.erecord.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class ReadIdCardUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		readIdCard("D:/test.jpg");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map readIdCard(String imagePath) {
		Map resultMap= new HashMap();
		//参数
   	 	Properties properties = new Properties();
		String base = ReadIdCardUtil.class.getResource("/")
				.getPath();
		try {
			properties.load(new FileInputStream(base
					+ "baidu/baidu.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		 
		String app_id=properties.getProperty("APP_ID").trim();
		String api_key=properties.getProperty("API_KEY").trim();
		String secret_key=properties.getProperty("SECRET_KEY").trim();
		
		AipOcr client = new AipOcr(app_id, api_key, secret_key);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        System.setProperty("aip.log4j.conf", "log4j.properties");
        
	    // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("detect_direction", "true");
	    options.put("detect_risk", "false");
	    
	    String idCardSide = "front";
	    
	    // 参数为本地图片路径
	    JSONObject res = client.idcard(imagePath, idCardSide, options);
	    if(res.has("error_code")){
	    	resultMap.put("status", "-1");
			resultMap.put("msg", res.getString("error_msg"));
	    }
	    else if(res.has("words_result")){
	    	JSONObject info=res.getJSONObject("words_result");
	    	Map tempMap= new HashMap();
	    	tempMap.put("name", info.getJSONObject("姓名").getString("words"));
	    	tempMap.put("idnumber", info.getJSONObject("公民身份号码").getString("words"));
	    	resultMap.put("status", "0");
			resultMap.put("msg", tempMap);
	    }
	    else {
	    	resultMap.put("status", "-1");
			resultMap.put("msg", "未知错误!");
	    }

	    // 参数为本地图片二进制数组
	    //byte[] file = readImageFile(image);
	    //res = client.idcard(file, idCardSide, options);
	    //System.out.println(res.toString(2));
 
	    return resultMap;
	}

}
