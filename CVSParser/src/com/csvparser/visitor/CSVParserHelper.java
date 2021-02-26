package com.csvparser.visitor;

import java.util.ArrayList;
import java.util.List;

import com.csvparser.interfaces.FileRedable;

public class CSVParserHelper {

	
	public CSVParserHelper() {
	}

	public void parse(String path, String delimiter, Parsable p) {

		List<List<String>> list = FileRedable.parseToArray(path, delimiter);
		List<String> columnNames = null;
		int rowNum = 0;
		for (List<String> row : list) {
			if (rowNum == 0) {
				columnNames = row;
				rowNum++;
				continue;
			}
			p.startRow();
			int colNum = 0;
			for (String col : row) {
				p.getValue(colNum, col);
				p.getValue(columnNames.get(colNum), colNum,col);
				colNum++;
			}
			p.endRow();	
		}
	}
}
