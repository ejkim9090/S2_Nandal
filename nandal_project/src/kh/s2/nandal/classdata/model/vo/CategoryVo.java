package kh.s2.nandal.classdata.model.vo;

public class CategoryVo {
//	CATEGORY_CODE NOT NULL NUMBER(2)    
//	CATEGORY_NAME NOT NULL VARCHAR2(50)
	private int categoryCode;
	private String categoryName;
	
	@Override
	public String toString() {
		return "CategoryVo [categoryCode=" + categoryCode + ", categoryName=" + categoryName + "]";
	}
	
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
