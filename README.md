# Java-CSVParser
여러가지 방법으로 만드는 CSV파서

## Annotation 방식
Annotation을 통해 쉽게 컬럼별로 읽습니다.


### Usage
```
List<T> list = AnnotationCSVParser.parse(path, delimiter, object);
```

### Parameters
|  name | type  |  description |
|:-:|:-:|:-:|
|  path |  string | csv 파일 path string  |
|  delimiter |  string | csv 파일 구분자  |
|  object |  Class\<T> | 만들 객체의 타입  |

### Example

**Main.java**
```
// 파싱하기.
List<TestDTO> dtos = AnnotationCSVParser.parse("res/enviroment_jongro.csv",",", TestDTO.class);

// 리스트출력
dtos.stream().forEach(System.out::println);
```

**TestDTO.java**

```

public class TestDTO {

	@Column(value="\"업체(시설)명\"")
	private String businessName;

	@Column(value="\"인허가번호\"")
	private String code;

	@Column("\"점검사항\"")
	private String checkList;
	
	public String getCheckList() {
		return checkList;
	}

	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Override
	public String toString() {
		return "TestDTO [businessName=" + businessName + ", code=" + code + ", checkList=" + checkList + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
```
- `@Column`에 원하는 각 필드에 해당하는 컬럼명을 입력하면, 컬럼에 해당하는 값을 DTO 객체에 주입시켜줍니다.
- getter와 setter가 네이밍 컨벤션에 맞게 구현이 되어있어야 합니다. 



## visitor패턴 방식 (SAXParser)

## Thread & Parallel 방식

