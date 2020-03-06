package com.erecord.model.checkpoint;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author LT
 */
public class Checkpoint {

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
	/** 备注 */
	private  String comment;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	/** 单位id */
	private  Long unit_id;
	public Long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	/**  */
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
