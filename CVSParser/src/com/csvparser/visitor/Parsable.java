package com.csvparser.visitor;

public interface Parsable {
	
	void startRow();
	void endRow();

	default void getValue(String columnName, int position, String data) {
	}
	
	default void getValue(int position, String data) {
	}
	
}
