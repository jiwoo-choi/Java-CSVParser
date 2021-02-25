package com.csvparser.annotation.test;

import com.csvparser.annotation.Column;

public class TestDTO {

	@Column(value="\"업체(시설)명\"")
	private String businessName;

	@Column(value="\"인허가번호\"")
	private String code;

	@Column("\"점검사항\"")
	private String checkList;
	
	public String getCheckList() {
		return checkList;
	}

	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Override
	public String toString() {
		return "TestDTO [businessName=" + businessName + ", code=" + code + ", checkList=" + checkList + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
