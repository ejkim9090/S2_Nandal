package kh.s2.nandal.classdata.model.vo;

public class AreaVo {
//	AREA_CODE NOT NULL NUMBER(2)    
//	AREA_NAME NOT NULL VARCHAR2(20) 
	private int areaCode;
	private String areaName;
	
	@Override
	public String toString() {
		return "AreaVo [areaCode=" + areaCode + ", areaName=" + areaName + "]";
	}
	
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}
