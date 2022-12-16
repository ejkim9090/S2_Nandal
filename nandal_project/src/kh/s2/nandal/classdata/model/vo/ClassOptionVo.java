package kh.s2.nandal.classdata.model.vo;

public class ClassOptionVo {
//	CO_CODE    NOT NULL NUMBER(2)     
//	CLASS_CODE NOT NULL NUMBER        
//	CO_NAME    NOT NULL VARCHAR2(100) 
//	CO_PRICE   NOT NULL NUMBER(10)    
	private int coCode;
	private int classCode;
	private String coName;
	private int coPrice;
	
	@Override
	public String toString() {
		return "ClassOptionVo [coCode=" + coCode + ", classCode=" + classCode + ", coName=" + coName + ", coPrice="
				+ coPrice + "]";
	}
	
	public int getCoCode() {
		return coCode;
	}
	public void setCoCode(int coCode) {
		this.coCode = coCode;
	}
	public int getClassCode() {
		return classCode;
	}
	public void setClassCode(int classCode) {
		this.classCode = classCode;
	}
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	public int getCoPrice() {
		return coPrice;
	}
	public void setCoPrice(int coPrice) {
		this.coPrice = coPrice;
	}
	
	
}
