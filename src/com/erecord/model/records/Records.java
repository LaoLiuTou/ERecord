package com.erecord.model.records;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Records {

	/**  */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/** 名字 */
	private  String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/** 身份证号 */
	private  String idnumber;
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	/** 身份证图片 */
	private  String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	/** 手机号 */
	private  String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/** 出/入 */
	private  String inorout;
	public String getInorout() {
		return inorout;
	}
	public void setInorout(String inorout) {
		this.inorout = inorout;
	}
	/** 卡点id */
	private  Long cp_id;
	public Long getCp_id() {
		return cp_id;
	}
	public void setCp_id(Long cp_id) {
		this.cp_id = cp_id;
	}
	/** 单位id */
	private  Long unit_id;
	public Long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	/** 出入时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date dt;
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	/** 录入人id */
	private  Long c_id;
	public Long getC_id() {
		return c_id;
	}
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	/**  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date c_dt;
	public Date getC_dt() {
		return c_dt;
	}
	public void setC_dt(Date c_dt) {
		this.c_dt = c_dt;
	}
	/**  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private  Date u_dt;
	public Date getU_dt() {
		return u_dt;
	}
	public void setU_dt(Date u_dt) {
		this.u_dt = u_dt;
	}
	/**  */
	private  Long state;
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}

	private String location;

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	private String cp_name;
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	private String unit_name;
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	private String c_username;
	public String getC_username() {
		return c_username;
	}
	public void setC_username(String c_username) {
		this.c_username = c_username;
	}
	private String c_name;
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	private String comment;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	

}
