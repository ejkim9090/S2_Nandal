package kh.s2.nandal.apply.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class MyApplyVo {
	private int caCode;
	private String className;
	private int caTotal;
	private Date caDate;
	private Timestamp caTime;
	private String coName;
	private String csStime;
	private String csFtime;
	
	@Override
	public String toString() {
		return "MyApplyVo [caCode=" + caCode + ", className=" + className + ", caTotal=" + caTotal + ", caDate="
				+ caDate + ", caTime=" + caTime + ", coName=" + coName + ", csStime=" + csStime + ", csFtime=" + csFtime
				+ "]";
	}
	public int getCaCode() {
		return caCode;
	}
	public void setCaCode(int caCode) {
		this.caCode = caCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getCaTotal() {
		return caTotal;
	}
	public void setCaTotal(int caTotal) {
		this.caTotal = caTotal;
	}
	public Date getCaDate() {
		return caDate;
	}
	public void setCaDate(Date caDate) {
		this.caDate = caDate;
	}
	public Timestamp getCaTime() {
		return caTime;
	}
	public void setCaTime(Timestamp caTime) {
		this.caTime = caTime;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
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
	
	
}
