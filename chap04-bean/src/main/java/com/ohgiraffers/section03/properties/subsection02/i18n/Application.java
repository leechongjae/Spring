package com.ohgiraffers.section03.properties.subsection02.i18n;

import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {
		
		/* 필기. i18n 소프트웨어의 국제화:
		 *  (Internationalization에서 첫글자인 I와 마지막글자인 N 사이 알파벳 갯수가 18개)
		 *  (국제화 말고 현지화도 있음(Localization : 현지화))
		 *  어떤 프레임워크를 지칭하는 것이 아닌 i와 n 사이에 18개의 알파벳이라는 뜻이다.
		 *  소프트웨어를 국제화 하기 위해서는 처리해야 할 작업이 많이 있다.
		 *  1. 언어, 지역별 번역
		 *  2. OS/플랫폼 인코딩
		 *  3. 문자열 치환 방법 : 서버쪽, 클라이언트쪽 치환 중 서버쪽에서 하는게 좋다(경우에 따라 다르지만). 사용자 디바이스에 리소스 및 렌더링 성능 이슈 발생
		 *  4. 국제화 UI (문자열 크기 변화, 폰트, 아이콘 등)
		 *  5. 쓰기 방향의 차이
		 *  6. 숫자, 공백, 화폐, 날짜, 주소, 측정 단위 등
		 *  7. 타임존, 섬머타임 등 시각
		 *  8. 문자열 정렬 방법
		 *  그 중 1번에 해당하는 다국어 메세지를 처리하는 방법을 볼 것이다.
		 *  */

		ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

		/* 중요. getMessage()
		 *  getMessage()는 관리중인 빈들의 타입 중 ReloadableResourceBundleMessageSource 타입을 찾는다.
		 *  그리고 찾은 빈의 속성 중 setBasename을 참조해 자원의 위치를 알 수 있다.
		 *  파일이 여러개가 있다면 전달받은 파라미터로 메시지 파일을 구분한다.
		 * */
		String error404MessageKR = context.getMessage("error.404", null, Locale.KOREA);
		String error500MessageKR = context.getMessage("error.500", new Object[] {"여러분", new Date()}, Locale.KOREA);

		System.out.println("I18N error.404 메세지 : " + error404MessageKR);
		System.out.println("I18N error.500 메세지 : " + error500MessageKR);

		String error404MessageUS = context.getMessage("error.404", null, Locale.US);
		String error500MessageUS = context.getMessage("error.500", new Object[] {"you", new Date()}, Locale.US);
		
		System.out.println("The I18N message for error.404 : " + error404MessageUS);
		System.out.println("The I18N message for error.500 : " + error500MessageUS);

	}

}
