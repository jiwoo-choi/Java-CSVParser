package com.csvparser.visitor.test;

import com.csvparser.visitor.Parsable;

public class MyParser implements Parsable {

	@Override
	public void startRow() {
		// TODO Auto-generated method stub
		System.out.println("row");
	}

	@Override
	public void endRow() {
		// TODO Auto-generated method stub
		System.out.println("end");
	}
	
	@Override
	public void getValue(int position, String data) {
		System.out.println(position + " " + data);
	}
		
	public void getResult(){
		System.out.println("result");
	}
 
}
