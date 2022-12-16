package kh.s2.nandal.apply.model.vo;

import java.sql.Timestamp;

public class ApplyCancelVo {
//	AC_CODE NOT NULL NUMBER       
//	AC_TIME NOT NULL TIMESTAMP(6) 
	private int acCode;
	private Timestamp acTime;
	
	@Override
	public String toString() {
		return "ApplyCancelVo [acCode=" + acCode + ", acTime=" + acTime + "]";
	}
	
	public int getAcCode() {
		return acCode;
	}
	public void setAcCode(int acCode) {
		this.acCode = acCode;
	}
	public Timestamp getAcTime() {
		return acTime;
	}
	public void setAcTime(Timestamp acTime) {
		this.acTime = acTime;
	}
}
