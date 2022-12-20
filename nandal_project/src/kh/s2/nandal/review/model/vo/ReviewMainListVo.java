package kh.s2.nandal.review.model.vo;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class ReviewMainListVo {
//	REVIEW_CODE       NOT NULL NUMBER          
//	REVIEW_TIME       NOT NULL TIMESTAMP(6)   
//	REVIEW_CONT       NOT NULL VARCHAR2(1000) 
//	REVIEW_GRADE      NOT NULL NUMBER(2,1)    
//	REVIEW_KIND       NOT NULL NUMBER(1)      
//	REVIEW_COMPONENT  NOT NULL NUMBER(1)      
//	REVIEW_FACILITY NOT NULL NUMBER(1)      
//	REVIEW_LEVEL      NOT NULL NUMBER(1)      
//	REVIEW_GROUP      NOT NULL NUMBER(1)    
	private int reviewCode;
	private String reviewCont;
	private int classCode;
	private String rpRoute;
	
	@Override
	public String toString() {
		return "ReviewMainListVo [reviewCode=" + reviewCode + ", reviewCont=" + reviewCont + ", classCode=" + classCode
				+ ", rpRoute=" + rpRoute + "]";
	}
	public int getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}
	public String getReviewCont() {
		return reviewCont;
	}
	public void setReviewCont(String reviewCont) {
		this.reviewCont = reviewCont;
	}
	public int getClassCode() {
		return classCode;
	}
	public void setClassCode(int classCode) {
		this.classCode = classCode;
	}
	public String getRpRoute() {
		return rpRoute;
	}
	public void setRpRoute(String rpRoute) {
		this.rpRoute = rpRoute;
	}
	


	
	
}
