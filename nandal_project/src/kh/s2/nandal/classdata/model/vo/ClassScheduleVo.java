package kh.s2.nandal.classdata.model.vo;

import java.sql.Date;

public class ClassScheduleVo {
//	CS_CODE    NOT NULL NUMBER       
//	CLASS_CODE NOT NULL NUMBER       
//	CS_DAY     NOT NULL NUMBER(5)    
//	CS_STIME            VARCHAR2(20) 
//	CS_FTIME            VARCHAR2(20) 
//	CS_SDATE   NOT NULL DATE         
//	CS_FDATE   NOT NULL DATE   
	private int csCode;
	private int classCode;
	private int csDay;
	private String csStime;
	private String csFtime;
	private Date csSdate;
	private Date csFdate;
	
	@Override
	public String toString() {
		return "ClassScheduleDao [csCode=" + csCode + ", classCode=" + classCode + ", csDay=" + csDay + ", csStime="
				+ csStime + ", csFtime=" + csFtime + ", csSdate=" + csSdate + ", csFdate=" + csFdate + "]";
	}
	
	public int getCsCode() {
		return csCode;
	}
	public void setCsCode(int csCode) {
		this.csCode = csCode;
	}
	public int getClassCode() {
		return classCode;
	}
	public void setClassCode(int classCode) {
		this.classCode = classCode;
	}
	public int getCsDay() {
		return csDay;
	}
	public void setCsDay(int csDay) {
		this.csDay = csDay;
	}
	public String getCsStime() {
		return csStime;
	}
	public void setCsStime(String csStime) {
		this.csStime = csStime;
	}
	public String getCsFtime() {
		return csFtime;
	}
	public void setCsFtime(String csFtime) {
		this.csFtime = csFtime;
	}
	public Date getCsSdate() {
		return csSdate;
	}
	public void setCsSdate(Date csSdate) {
		this.csSdate = csSdate;
	}
	public Date getCsFdate() {
		return csFdate;
	}
	public void setCsFdate(Date csFdate) {
		this.csFdate = csFdate;
	}
	
	
}
