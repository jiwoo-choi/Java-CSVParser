package com.csvparser.interfaces;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface FileRedable {

	static List<List<String>> parseToArray(String path, String delimiter) {
 
		List<List<String>> list = new ArrayList<>();
		File csv = new File(path);

		try (
				BufferedReader br =  new BufferedReader(new FileReader(csv));
				) {
			Charset.forName("UTF-8");
			String line = "";
			while((line=br.readLine()) != null) {
				String[] token = line.split(delimiter);

				List<String> tempList = new ArrayList<String>(Arrays.asList(token));
				list.add(tempList);
			}
		} catch (FileNotFoundException e) {
			System.err.println("파일이 존재하지 않습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("에러가 존재합니다.");
			e.printStackTrace();
		}
		return list;
	}
}
