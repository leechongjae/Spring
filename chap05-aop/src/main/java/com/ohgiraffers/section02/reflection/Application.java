package com.ohgiraffers.section02.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Application {

	public static void main(String[] args) {
		
		/* 설명. 리플렉션(Reflection)이란?
		 *  컴파일된 자바 코드에서 역으로 클래스를 불러 메소드 및 필드 정보를 구해오는 방법으로 반사, 투영 이라는 의미를 가진다.
		 *  리플렉션은 JVM에서 실행되는 애플리케이션의 런타임 동작을 검사하거나 수정할 수 있는 기능이 필요한 경우 사용한다.
		 *  스프링프레임워크, 마이바티스, 하이버네이트, jackson 등의 라이브러리에서 사용한다.
		 *  Reflection을 사용해서 스프링에서는 런타임 시 에 개발자가 등록한 빈을 애플레케이션 내에서 사용할 수 있게 하는 것이다.
		 *  reflection이 무엇인지 예제를 통해 살펴보자 (참고 : https://docs.oracle.com/javase/tutorial/reflect/)
		 *
		 * 설명. 사용 시 주의사항
		 *  Refletion은 강력한 도구이지만 무분별하게 사용해서는 안된다.
		 *  1. 오버헤드 발생 : 성능 저하를 발생할 수 있기 때문에 성능에 민감한 애플리케이션에서는 사용하지 않는다.
		 *  2. 캡슐화 저해 : private로 설정한 member에 접근 가능하기 때문에 코드 기능이 저하되며 여러 가지 문제를 발생시킬 수 있다.
		 *
		 * 설명.
		 *  리플렉션이 사용되는 경우
		 *  1. IOC컨테이너
		 *  2. AOP
		 *  3. 마이바티스 매퍼
		 *  4. log4jdbc
		 * */

		/* Note. Account 클래스를 만들고 오자 */
		/* 설명. 1. Class 타입의 Class 메타 정보 추출*/
		Class class1 = Account.class;
		System.out.println("class1 : " + class1);
		
		/* 설명. Object 클래스의 getClass() 메소드를 이용하면 Class 타입으로 리턴받아 이용할 수 있다. */
		Class class2 = new Account().getClass();
		System.out.println("class2 : " + class2);

		/* 설명. Class.forName() 메소드를 이용하여 런타임시 로딩을 하고 그 클래스 메타정보를 Class 타입으로 반환받을 수 있다. */
		try {
			Class class3 = Class.forName("com.ohgiraffers.section02.reflection.Account");
			System.out.println("class3 : " + class3);
			
			/* 설명. Double자료형 배열을 로드할 수 있다. */
			Class class4 = Class.forName("[D");
			Class class5 = double[].class;
			
			System.out.println("class4 : " + class4);
			System.out.println("class5 : " + class5);
			
			/* 설명. String자료형 배열을 로드할 수 있다. */
			Class class6 = Class.forName("[Ljava.lang.String;");
			Class class7 = String[].class;
			System.out.println("class6 : " + class6);
			System.out.println("class7 : " + class7);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		/* Note. 시간 없으면 여기서부터 '2. 필드 정보 추출'까지 스킵 가능. */

		/* 설명. 원시 자료형을 사용하면 컴파일 에러 발생 */
//		double d = 1.0;
//		Class class8 = d.getClass();
		
		/* 설명. TYPE 필드를 이용하여 원시형 클래스를 반환받을 수 있다. */
		Class class8 = Double.TYPE;
		System.out.println("class8 : " + class8);
		
		Class class9 = Void.TYPE;
		System.out.println("class9 : " + class9);
		
		/* 설명. 클래스의 메타 정보를 이용하여 여러 가지 정보를 반환받는 메소드를 제공한다.
		 *  상속된 부모 클래스를 반환한다. */
		Class superClass = class1.getSuperclass();
		System.out.println("superClass : " + superClass);


		/* 설명. 2. 필드 정보 추출*/
		/* Note. 접근제어자 상관 없이 모두 뜯을 수 있음. */
		Field[] fields = Account.class.getDeclaredFields();

		for(Field field : fields) {

			System.out.println("modifiers : " + Modifier.toString(field.getModifiers()) + 
					", type : " + field.getType() + 
					", name : " + field.getName());
		}
		
		/* 설명. 3. 생성자 정보를 반환한다 */
		Constructor[] constructors = Account.class.getConstructors();

		for(Constructor con : constructors) {

			System.out.println("name : " + con.getName());
			
			Class[] params = con.getParameterTypes();
			for(Class param : params) {
				System.out.println("paramType : " + param.getTypeName());
			}
		}
		
		/* 설명. 생성자를 이용하여 인스턴스를 생성할 수 있다. */
		try {
			Account acc = (Account) constructors[0].newInstance("20", "110-223-123456", "1234", 10000);

			System.out.println(acc.getBalance());

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

		/* 설명. 4. 메소드 정보 추출 */
		Method[] methods = Account.class.getMethods();
		Method getBalanceMethod = null;

		for(Method method : methods) {

			System.out.println(Modifier.toString(method.getModifiers()) + " " + 
							method.getReturnType().getSimpleName() + " " + 
							method.getName());
			
			if("getBalance".equals(method.getName())) {
				getBalanceMethod = method;
			}
		}
		
		try {
			/* 설명. invoke 메소드로 메소드를 호출할 수 있다. */
			System.out.println(getBalanceMethod.invoke(((Account) constructors[2].newInstance())));

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

	}

}
