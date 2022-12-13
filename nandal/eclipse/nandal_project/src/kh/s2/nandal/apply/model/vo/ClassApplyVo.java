package kh.s2.nandal.apply.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class ClassApplyVo {
//	CA_CODE    NOT NULL NUMBER       
//	MEMBER_ID  NOT NULL VARCHAR2(50) 
//	CLASS_CODE NOT NULL NUMBER       
//	CA_TOTAL   NOT NULL NUMBER(2)    
//	CA_DATE    NOT NULL DATE         
//	CA_TIME    NOT NULL TIMESTAMP(6) 
//	CA_CANCEL  NOT NULL CHAR(1)      
//	CO_CODE    NOT NULL NUMBER(2)    
//	CS_CODE    NOT NULL NUMBER    
	private int caCode;
	private String memberId;
	private int classCode;
	private int caTotal;
	private Date caDate;
	private Timestamp caTime;
	private String caCancel;
	private int coCode;
	private int csCode;
	@Override
	public String toString() {
		return "ClassApplyVo [caCode=" + caCode + ", memberId=" + memberId + ", classCode=" + classCode + ", caTotal="
				+ caTotal + ", caDate=" + caDate + ", caTime=" + caTime + ", caCancel=" + caCancel + ", coCode="
				+ coCode + ", csCode=" + csCode + "]";
	}
	public int getCaCode() {
		return caCode;
	}
	public void setCaCode(int caCode) {
		this.caCode = caCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getClassCode() {
		return classCode;
	}
	public void setClassCode(int classCode) {
		this.classCode = classCode;
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
	public String getCaCancel() {
		return caCancel;
	}
	public void setCaCancel(String caCancel) {
		this.caCancel = caCancel;
	}
	public int getCoCode() {
		return coCode;
	}
	public void setCoCode(int coCode) {
		this.coCode = coCode;
	}
	public int getCsCode() {
		return csCode;
	}
	public void setCsCode(int csCode) {
		this.csCode = csCode;
	}
}
