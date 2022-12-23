package kh.s2.nandal.review.model.vo;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class ClassReviewVo {
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
	private String reviewTime;
	private String reviewCont;
	private double reviewGrade;
	private int reviewKind;
	private int reviewComponent;
	private int reviewFacility;
	private int reviewLevel;
	private int reviewGroup;
	private String memberName;
	private String className;
	
	private List<String> rpRoute;

	@Override
	public String toString() {
		return "ClassReviewVo [reviewCode=" + reviewCode + ", reviewTime=" + reviewTime + ", reviewCont=" + reviewCont
				+ ", reviewGrade=" + reviewGrade + ", reviewKind=" + reviewKind + ", reviewComponent=" + reviewComponent
				+ ", reviewFacility=" + reviewFacility + ", reviewLevel=" + reviewLevel + ", reviewGroup=" + reviewGroup
				+ ", memberName=" + memberName + ", className=" + className + ", rpRoute=" + rpRoute + "]";
	}

	public int getReviewCode() {
		return reviewCode;
	}

	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewCont() {
		return reviewCont;
	}

	public void setReviewCont(String reviewCont) {
		this.reviewCont = reviewCont;
	}

	public double getReviewGrade() {
		return reviewGrade;
	}

	public void setReviewGrade(double reviewGrade) {
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getRpRoute() {
		return rpRoute;
	}

	public void setRpRoute(List<String> rpRoute) {
		this.rpRoute = rpRoute;
	}

	

}
