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


	private static java.util.function.Function<String, Object> typeToMapper(Class<?> type) {
		
		if (type.isAssignableFrom(Integer.class)) {
			return Integer::parseInt;
		} else if (type.isAssignableFrom(int.class)) {
			return Integer::parseInt;
		} else if (type.isAssignableFrom(String.class)) {
			return (String s) -> s;
		} else if (type.isAssignableFrom(Long.class)) {
			return Long::parseLong;
		} else if (type.isAssignableFrom(long.class)) {
			return Long::parseLong;
		} else if (type.isAssignableFrom(Double.class)) {
			return Double::parseDouble;
		} else if (type.isAssignableFrom(double.class)) {
			return Double::parseDouble;
		} else if (type.isAssignableFrom(Float.class)) {
			return Float::parseFloat;
		} else if (type.isAssignableFrom(float.class)) {
			return Float::parseFloat;
		} else if (type.isAssignableFrom(Character.class)) {
			return (String s) -> s.charAt(0);
		} else if (type.isAssignableFrom(char.class)) {
			return (String s) -> s.charAt(0);
		} else {
			// error or string.
			return (String s) -> s;
		}		
	}
	
	public static <T> List<T> parse(String path, String delimiter, Class<T> object) {

		
		// TODO : 바꿔주기. (하나의 function으로 바꿔주기)  ENum??
		

		/// (dto) column name과 field객체의 1:1 맵핑.
		HashMap<String, Field> columnNameToField = new HashMap<>();
		/// (dto) column name과 실제 csv 데이터의 position mapping
		HashMap<String, Integer> columNameToPosition = new HashMap<>();
		/// (dto) column name들..
		List<String> columnNames = new ArrayList<String>();
		
		/// 주어진 객체를 읽으며 dto에서 정의한 column네임을 모두 수집합니다.
		for (Field f : object.getDeclaredFields()) {
			Column column = f.getAnnotation(Column.class);
			if (column != null) {
				columnNames.add(column.value());
				columnNameToField.put(column.value(), f);
//				Class<?> type = f.getType();
			}
		}

				
		/// csv 파일을 읽습니다.
		List<List<String>> list = FileRedable.parseToArray(path, delimiter);


		/// 실제 데이터의 column 사이즈.
		int size = list.get(0).size();
		
		/// TODO : 실제 데이터와 DTO가 다르면 에러를 내야한다.
		
		for(int i = 0 ; i < size; i++) {
			for (String name : columnNames) {
				if (name.equals(list.get(0).get(i))) {
					columNameToPosition.put(name, i); // 인덱스정보 저장.
				}
			}
		}
		
		String[] positionToColumnName = new String[size];

		for (String columnName : columNameToPosition.keySet()) {
			int idx = columNameToPosition.get(columnName);
			positionToColumnName[idx] = columnName;
		}


		List<T> returnList = new ArrayList<>();


		for(int i = 1 ; i < list.size(); i++) {
			try {
				Object instance = object.newInstance();

				for (int j = 0 ; j < size; j++) {
					if (positionToColumnName[j] != null) {
						
						Field fieldInfo = columnNameToField.get(positionToColumnName[j]);

						Class<?> type = fieldInfo.getType();
						fieldInfo.setAccessible(true);
						fieldInfo.set(instance, typeToMapper(type).apply(list.get(i).get(j)));
						
//						String variableName = fieldInfo.getName();
//						String setterName =  "set" + variableName.substring(0,1).toUpperCase()+variableName.substring(1);
//						Method setter = object.getDeclaredMethod(setterName,type);
//						setter.invoke(instance,);
						//field의 값에다가 직접 set해주기 가능.
						//field // 필드정보있어서바로가능.
						// setter사용해주기.
						//근데 어떤 field일지모름.. 그냥 이방법이 제일 빠를듯?
						// (!) class의 모든 field리스트를 살펴서 직접 넣어주는 방법.
						// (@) 그냥 네이밍 규칙을 찾아 set을 바로 불러주는 방법.
						//						Method setter = instance.getClass().getDeclaredMethod("set" + reverseMap[j]);
						//						setter.invoke(instance, list.get(i).get(j));
						//						returnList.add((T) instance);
//						return returnList;
					}
				}
				returnList.add((T) instance);
			}  catch (InstantiationException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} 

		}

		return returnList;
	}


}
