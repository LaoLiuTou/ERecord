package com.erecord.utils;

 
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
 
/**
 * @author lt
 * @version 1.0 
 */
@Component 
public class TokenUtils {
    public static Map<String,TokenBean> map=new ConcurrentHashMap<String, TokenBean>();
    public static void add(String token,TokenBean tokenBean){
    	//非单点登录不需要清除相同用户id的token
        map.put(token,tokenBean);
    }
    public static TokenBean get(String token){
       return map.get(token);
    }
 
    public static void remove(String token){
    	 map.remove(token);
    }
    
    
    @SuppressWarnings("rawtypes")
	public static void main(String[] s){
    	/*System.out.println(get("b6f71e0e-6634-4529-aee5-324cecdb1fbe"));
    	for (Map.Entry entry:map.entrySet()){
            
            System.out.println(entry.getValue()+":"+entry.getKey());
        }*/
    	Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY)==18&&calendar.get(Calendar.MINUTE)==30);
    	
    }

    
    public class TokenBean { 
    	private String timesamp;
    	private String username;
    	private String userid;
		public String getTimesamp() {
			return timesamp;
		}
		public void setTimesamp(String timesamp) {
			this.timesamp = timesamp;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
    	
       
    } 
    
    private static long removeTime = 24*60*60*1000;//失效时间
    Logger logger = Logger.getLogger("WheelshopLogger");
    @SuppressWarnings("rawtypes")
	//@Scheduled(cron="0 0 0/1 * * ? ")  
    public void cleanToken(){  
    	try {
			String message ="清理过期token,起始token数量："+map.size();
			for (Map.Entry entry:map.entrySet()){
				TokenBean tb=(TokenBean) entry.getValue();
				String tokenTime = tb.getTimesamp();
				long timesampLong=Long.parseLong(tokenTime);
				long tokenTimeNow=System.currentTimeMillis();
				System.out.println(tokenTimeNow-timesampLong);
				if((tokenTimeNow-timesampLong)>removeTime){
					map.remove(entry.getKey());
				}
			    
			}
			message+="，清理后token数量："+map.size();
			logger.info(message);
		} catch (Exception e) {
			logger.info("清理过期token出错！");
			e.printStackTrace();
		}
    	 
    } 

     
    
     
}
