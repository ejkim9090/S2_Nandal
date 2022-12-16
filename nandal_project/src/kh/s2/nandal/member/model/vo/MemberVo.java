package kh.s2.nandal.member.model.vo;

public class MemberVo {
//	MEMBER_ID    NOT NULL VARCHAR2(50)  
//	MEMBER_PWD   NOT NULL VARCHAR2(20)  
//	MEMBER_NAME  NOT NULL VARCHAR2(30)  
//	MEMBER_PHONE NOT NULL VARCHAR2(13)  
//	MEMBER_IMG   NOT NULL VARCHAR2(100) 
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberPhone;
	private String memberImg;
	
	@Override
	public String toString() {
		return "MemberVo [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName=" + memberName
				+ ", memberPhone=" + memberPhone + ", memberImg=" + memberImg + "]";
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberImg() {
		return memberImg;
	}
	public void setMemberImg(String memberImg) {
		this.memberImg = memberImg;
	}
}
