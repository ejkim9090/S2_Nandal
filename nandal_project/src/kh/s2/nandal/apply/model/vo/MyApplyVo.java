package kh.s2.nandal.apply.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class MyApplyVo {
	private int caCode;
	private int classcode;
	private String className;
	private int classPrice;
	private int caTotal;
	private String caDate;
	private String caTime;
	private int coCode;
	private String coName;
	private int coPrice;
	private String csStime;
	private String csFtime;
	private int reviewCheck; //해당 apply에 리뷰 있는지 여부 체크
	@Override
	public String toString() {
		return "MyApplyVo [caCode=" + caCode + ", classcode=" + classcode + ", className=" + className + ", classPrice="
				+ classPrice + ", caTotal=" + caTotal + ", caDate=" + caDate + ", caTime=" + caTime + ", coCode="
				+ coCode + ", coName=" + coName + ", coPrice=" + coPrice + ", csStime=" + csStime + ", csFtime="
				+ csFtime + ", reviewCheck=" + reviewCheck + "]";
	}
	public int getCaCode() {
		return caCode;
	}
	public void setCaCode(int caCode) {
		this.caCode = caCode;
	}
	public int getClasscode() {
		return classcode;
	}
	public void setClasscode(int classcode) {
		this.classcode = classcode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getClassPrice() {
		return classPrice;
	}
	public void setClassPrice(int classPrice) {
		this.classPrice = classPrice;
	}
	public int getCaTotal() {
		return caTotal;
	}
	public void setCaTotal(int caTotal) {
		this.caTotal = caTotal;
	}
	public String getCaDate() {
		return caDate;
	}
	public void setCaDate(String caDate) {
		this.caDate = caDate;
	}
	public String getCaTime() {
		return caTime;
	}
	public void setCaTime(String caTime) {
		this.caTime = caTime;
	}
	public int getCoCode() {
		return coCode;
	}
	public void setCoCode(int coCode) {
		this.coCode = coCode;
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
	public int getReviewCheck() {
		return reviewCheck;
	}
	public void setReviewCheck(int reviewCheck) {
		this.reviewCheck = reviewCheck;
	}
	
}
