package com.csvparser.annotation.test;

import java.util.List;

import com.csvparser.annotation.AnnotationCSVParser;

public class AnnotationParserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<TestDTO> dto = AnnotationCSVParser.parse("res/enviroment_jongro.csv",",", TestDTO.class);
		dto.stream().forEach(System.out::println);
	}

}
