package kh.s2.nandal.crawling.model.vo;

public class CrawlingClassPhotoDto {
	private int classCode;
	private String cpRoute;
	private int cpType;
	
	public CrawlingClassPhotoDto() {
		super();
	}

	public CrawlingClassPhotoDto(int classCode, String cpRoute, int cpType) {
		super();
		this.classCode = classCode;
		this.cpRoute = cpRoute;
		this.cpType = cpType;
	}

	@Override
	public String toString() {
		return "ClassPhotoDto [classCode=" + classCode + ", cpRoute=" + cpRoute + ", cpType=" + cpType + "]";
	}

	public int getClassCode() {
		return classCode;
	}

	public void setClassCode(int classCode) {
		this.classCode = classCode;
	}

	public String getCpRoute() {
		return cpRoute;
	}

	public void setCpRoute(String cpRoute) {
		this.cpRoute = cpRoute;
	}

	public int getCpType() {
		return cpType;
	}

	public void setCpType(int cpType) {
		this.cpType = cpType;
	}
}
