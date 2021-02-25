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
List<TestDTO> dtos = AnnotationCSVParser.parse("res/sanga_seoul.csv","\\|", TestDTO.class);

// 리스트출력
dtos.stream().forEach(System.out::println);

// result
TestDTO [code=17163092, name=도전최강달인왕만두, businessName=안녕하세, test=N]
TestDTO [code=17163093, name=도전최강달인왕만두, businessName=안녕하세, test=Y]
TestDTO [code=17163094, name=도전최강달인왕만두, businessName=안녕하세, test=N]
TestDTO [code=17163095, name=도전최강달인왕만두, businessName=안녕하세, test=N]
TestDTO [code=17163096, name=도전최강달인왕만두, businessName=안녕하세, test=N]
TestDTO [code=17163097, name=도전최강달인왕만두, businessName=안녕하세, test=N]
TestDTO [code=17163098, name=도전최강달인왕만두, businessName=안녕하세, test=N]
...
```

**TestDTO.java**

```
public class TestDTO {


	@Column("상가업소번호")
	private int code;

	@Column("상호명")
	private String name;
	
	@Column("지점명")
	private String businessName;

	@Column("Test")
	private char test;

	@Override
	public String toString() {
		return "TestDTO [code=" + code + ", name=" + name + ", businessName=" + businessName + ", test=" + test + "]";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public char getTest() {
		return test;
	}

	public void setTest(char test) {
		this.test = test;
	}
}
```
- `@Column`에 원하는 각 필드에 해당하는 컬럼명을 입력하면, 컬럼에 해당하는 값을 DTO 객체에 주입시켜줍니다.
- getter와 setter가 네이밍 컨벤션에 맞게 구현이 되어있어야 합니다. 



## visitor패턴 방식 (SAXParser)

## Thread & Parallel 방식

