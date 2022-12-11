package kh.s2.nandal.review.model.vo;

public class ReviewPhotoVo {
//	REVIEW_CODE NOT NULL NUMBER        
//	RP_ROUTE    NOT NULL VARCHAR2(100) 
	private int reviewCode;
	private String rpRoute;
	
	@Override
	public String toString() {
		return "ReviewPhotoVo [reviewCode=" + reviewCode + ", rpRoute=" + rpRoute + "]";
	}
	
	public int getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}
	public String getRpRoute() {
		return rpRoute;
	}
	public void setRpRoute(String rpRoute) {
		this.rpRoute = rpRoute;
	}
}
