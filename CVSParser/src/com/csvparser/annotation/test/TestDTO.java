package com.csvparser.annotation.test;

import com.csvparser.annotation.Column;

public class TestDTO {


	@Column("상가업소번호")
	private long code;


	@Override
	public String toString() {
		return "TestDTO [code=" + code + "]";
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

}
