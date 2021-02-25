package com.csvparser.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.csvparser.interfaces.FileRedable;

/**
 * annotation을 활용한 CSV 파서입니다.
 * @author jiwoochoi
 *
 */
public class AnnotationCSVParser implements FileRedable {


	public static <T> List<T> parse(String path, String delimiter, Class<T> object) {

		HashMap<String, String> columToField = new HashMap<>();
		HashMap<String, Integer> map = new HashMap<>();

		for (Field f : object.getDeclaredFields()) {
			Column column = f.getAnnotation(Column.class);
			if (column != null) {
				/// Column이 몇번쨰 인덱스에 있는지 파악하기 위함.
				map.put(column.value(), 0);

				/// Column이 실제 필드명으로 뭔지 파악하기 위함.
				columToField.put(column.value(), f.getName());
			}
		}

		List<List<String>> list = FileRedable.parseToArray(path, delimiter);

		Set<String> keyFound = map.keySet();

		// 칼럼을 읽습니다.
		int size = list.get(0).size();
		for(int i = 0 ; i < size; i++) {
			for (String key : keyFound) {
				if (key.equals(list.get(0).get(i))) {
					map.put(key, i); // 인덱스정보 저장.
				}
			}
		}

		//Integer, String으로 역방향 map을 만들어줍니다.
		String[] reverseMap = new String[size];
		for (String key : keyFound) {
			String fieldName = columToField.get(key);
			Integer index = map.get(key);
			reverseMap[index] = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
		}

		List<T> returnList = new ArrayList<>();


		for(int i = 1 ; i < size; i++) {
			try {
				Object instance = object.newInstance();

				for (int j = 0 ; j < size; j++) {
					if (reverseMap[j] != null) {
						Method setter = object.getDeclaredMethod("set" + reverseMap[j], String.class);
						setter.invoke(instance, list.get(i).get(j));
						//field의 값을 변경하면 될까?
						//근데 어떤 field일지모름.. 그냥 이방법이 제일 빠를듯?
						// (!) class의 모든 field리스트를 살펴서 직접 넣어주는 방법.
						// (@) 그냥 네이밍 규칙을 찾아 set을 바로 불러주는 방법.
						//						Method setter = instance.getClass().getDeclaredMethod("set" + reverseMap[j]);
						//						setter.invoke(instance, list.get(i).get(j));

					}
				}
				returnList.add((T) instance);
			}  catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}

		return returnList;
	}


}
