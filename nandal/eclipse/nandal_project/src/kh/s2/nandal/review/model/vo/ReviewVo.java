package kh.s2.nandal.review.model.vo;

import java.sql.Timestamp;

public class ReviewVo {
//	REVIEW_CODE       NOT NULL NUMBER         
//	CA_CODE           NOT NULL NUMBER         
//	REVIEW_TIME       NOT NULL TIMESTAMP(6)   
//	REVIEW_CONT       NOT NULL VARCHAR2(1000) 
//	REVIEW_GRADE      NOT NULL NUMBER(2,1)    
//	REVIEW_KIND       NOT NULL NUMBER(1)      
//	REVIEW_COMPONENT  NOT NULL NUMBER(1)      
//	REVIEW_FACILITY NOT NULL NUMBER(1)      
//	REVIEW_LEVEL      NOT NULL NUMBER(1)      
//	REVIEW_GROUP      NOT NULL NUMBER(1)    
	private int reviewCode;
	private int caCode;
	private Timestamp reviewTime;
	private String reviewCont;
	private int reviewGrade;
	private int reviewKind;
	private int reviewComponent;
	private int reviewFacility;
	private int reviewLevel;
	private int reviewGroup;
	
	@Override
	public String toString() {
		return "ReviewVo [reviewCode=" + reviewCode + ", caCode=" + caCode + ", reviewTime=" + reviewTime
				+ ", reviewCont=" + reviewCont + ", reviewGrade=" + reviewGrade + ", reviewKind=" + reviewKind
				+ ", reviewComponent=" + reviewComponent + ", reviewFacility=" + reviewFacility + ", reviewLevel="
				+ reviewLevel + ", reviewGroup=" + reviewGroup + "]";
	}
	
	public int getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}
	public int getCaCode() {
		return caCode;
	}
	public void setCaCode(int caCode) {
		this.caCode = caCode;
	}
	public Timestamp getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Timestamp reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getReviewCont() {
		return reviewCont;
	}
	public void setReviewCont(String reviewCont) {
		this.reviewCont = reviewCont;
	}
	public int getReviewGrade() {
		return reviewGrade;
	}
	public void setReviewGrade(int reviewGrade) {
		this.reviewGrade = reviewGrade;
	}
	public int getReviewKind() {
		return reviewKind;
	}
	public void setReviewKind(int reviewKind) {
		this.reviewKind = reviewKind;
	}
	public int getReviewComponent() {
		return reviewComponent;
	}
	public void setReviewComponent(int reviewComponent) {
		this.reviewComponent = reviewComponent;
	}
	public int getReviewFacility() {
		return reviewFacility;
	}
	public void setReviewFacility(int reviewFacility) {
		this.reviewFacility = reviewFacility;
	}
	public int getReviewLevel() {
		return reviewLevel;
	}
	public void setReviewLevel(int reviewLevel) {
		this.reviewLevel = reviewLevel;
	}
	public int getReviewGroup() {
		return reviewGroup;
	}
	public void setReviewGroup(int reviewGroup) {
		this.reviewGroup = reviewGroup;
	}
	
}
