package com.csvparser.visitor.test;

import com.csvparser.visitor.CSVParserHelper;

public class CSVParserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub



		CSVParserHelper cp = new CSVParserHelper();
		MyParser handler = new MyParser();
		cp.parse("res/sanga_seoul.csv", "\\|", handler);
		handler.getResult();
		
	}

}
