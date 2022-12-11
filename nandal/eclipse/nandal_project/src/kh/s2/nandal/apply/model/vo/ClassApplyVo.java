package kh.s2.nandal.apply.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class ClassApplyVo {
//	CA_CODE    NOT NULL NUMBER       
//	MEMBER_ID  NOT NULL VARCHAR2(50) 
//	CA_TOTAL   NOT NULL NUMBER(2)    
//	CA_DATE	   NOT NULL
//	CA_TIME    NOT NULL TIMESTAMP(6) 
//	CO_CODE             NUMBER(2)    
//	CS_CODE    NOT NULL NUMBER       
//	CLASS_CODE NOT NULL NUMBER       
//	CA_CANCEL  NOT NULL CHAR(1)  
	private int caCode;
	private String memberid;
	private int catotal;
	private Date caDate;
	private Timestamp caTime;
	private int coCode;
	private int csCode;
	private int classCode;
	private String caCancel;
	@Override
	public String toString() {
		return "ClassApplyVo [caCode=" + caCode + ", memberid=" + memberid + ", catotal=" + catotal + ", caDate="
				+ caDate + ", caTime=" + caTime + ", coCode=" + coCode + ", csCode=" + csCode + ", classCode="
				+ classCode + ", caCancel=" + caCancel + "]";
	}
	public int getCaCode() {
		return caCode;
	}
	public void setCaCode(int caCode) {
		this.caCode = caCode;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public int getCatotal() {
		return catotal;
	}
	public void setCatotal(int catotal) {
		this.catotal = catotal;
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
	public int getClassCode() {
		return classCode;
	}
	public void setClassCode(int classCode) {
		this.classCode = classCode;
	}
	public String getCaCancel() {
		return caCancel;
	}
	public void setCaCancel(String caCancel) {
		this.caCancel = caCancel;
	}
}
