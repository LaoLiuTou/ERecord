package com.erecord.model.user;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class User {

	/** 用户  */
	private  Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**  */
	private  String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	/**  */
	private  String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/** 昵称 */
	private  String nickname;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/** 电话 */
	private  String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/** 备注 */
	private  String comment;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	/** 角色  0：管理 1：内容维护 2：单位 */
	private  Long role;
	public Long getRole() {
		return role;
	}
	public void setRole(Long role) {
		this.role = role;
	}
	/** 新建审核 0：未 1：审核通过 2：驳回 */
	private  Long unit_id;
	
	private  String cp_id;
	 
	public Long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	public String getCp_id() {
		return cp_id;
	}
	public void setCp_id(String cp_id) {
		this.cp_id = cp_id;
	}
	/** 创建人id */
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

	private String unit_name;
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	


}
