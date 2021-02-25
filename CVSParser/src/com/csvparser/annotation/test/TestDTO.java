package com.csvparser.annotation.test;

import com.csvparser.annotation.Column;

public class TestDTO {


	@Column("상가업소번호")
	private int code;

	@Column("상호명")
	private String name;
	
	@Column("지점명")
	private String businessName;

	@Column("Test")
	private char test;

	@Override
	public String toString() {
		return "TestDTO [code=" + code + ", name=" + name + ", businessName=" + businessName + ", test=" + test + "]";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public char getTest() {
		return test;
	}

	public void setTest(char test) {
		this.test = test;
	}
	

}
