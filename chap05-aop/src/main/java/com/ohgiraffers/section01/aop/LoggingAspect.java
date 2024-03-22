package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/* 설명. @Aspect:
 *  ponitcut과 advice를 하나의 클래스 단위로 정의하기 위한 어노테이션이다.
 * */
@Aspect
@Component
public class LoggingAspect {

    /* 설명. joinpoint:
     *  타겟 클래스의 메소드에서 advice를 적용할 수 있는 지점들을 조인포인트(joinpoint)라고 한다.
     *  포인트컷(pointcut)은 여러 조인포인트들에 advice(advice)를 적용할 곳을 지정한 것이다.
     *  해당 조인포인트에서 advice가 동작한다.
     * 
     * 설명. 포인트컷 표현식
     *  execution([수식어] 리턴타입 [클래스이름].이름(파라미터)
     *  1. 수식어: public, private 등 수식어를 명시(생략 가능)
     *  2. 리턴 타입: 리턴 타입을 명시
     *  3. 클래스명(패키지명 포함) 및 메소드 이름: 클래스명과 메소드명을 명시
     *  4. 파라미터(매개변수): 메소드의 파라미터를 명시
     *  5. "*": 1개 이면서 모든 값이 올 수 있음
     *  6. "..": 0개 이상의 모든 값이 올 수 있음
     *  ex1) execution(public Integer com.ohgiraffers.section01.advice.*.*(*))
     *   : com.ohgiraffers.section01.advice 패키지에 속해 있는 바로 다음 하위 클레스에
     *     파라미터가 1개인 모든 메소드이자 접근제어자가 public이고 반환형이 Integer인 경우
     *  ex2) execution(* com.ohgiraffers.section01.advice.annotation..stu*(..))
     *   : com.ohgiraffers.section01.advice 패키지 및 하위 패키지에 속해 있고
     *     이름이 stu로 시작하는 파라미터가 0개 이상인 모든 메소드이며
     *     접근제어자와 반환형은 상관 없음
     * */
	@Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
	public void logPointcut() {}


	/* 설명. 1. Before Advice */
//	@Before("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
	@Before("LoggingAspect.logPointcut()")
	public void logBefore(JoinPoint joinPoint) {

		/* 필기. 타겟 오브젝트 출력 */
		System.out.println("Before joinPoint.getTarget(): " + joinPoint.getTarget());

		/* 필기. joinpoint의 메서드 시그니처 출력 */
		System.out.println("Before joinPoint.getSignature(): " + joinPoint.getSignature());

		/* 필기. joinpoint의 매개변수 춣력(단, 타겟 메서드의 매개변수가 하나 이상일 때)  */
		if(joinPoint.getArgs().length > 0){
			System.out.println("Before joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0]);
		}
	}

	/* 설명. 2. After Advice
	 *   pointcut을 동일한 클래스 내에서 사용하는 것이라면 클래스명은 생략할 수 있다.
	 *  (단, 패키지가 다르다면 패키지를 포함한 클래스명을 모두 기술해야 한다.
	 * */
	@After("logPointcut()")
	public void logAfter(JoinPoint joinPoint) {

		System.out.println("After joinPoint.getTarget(): " + joinPoint.getTarget());
		System.out.println("After joinPoint.getSignature(): " + joinPoint.getSignature());

		if(joinPoint.getArgs().length > 0){
			System.out.println("After joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0]);
		}
	}

	/* returning 속성은 리턴값으로 받아올 오브젝트의 매개변수 이름과 동일해야 한다.
	 * 또한 joinPoint는 반드시 첫 번째 매개변수로 선언해야 한다.
	 * */
	/* 설명. 3. AfterReturning Advice
	 *  returning 속성에 사용된 이름(returning="result")과
	 *  메서드의 반환형으로 넘어오는 매개변수 명(Object result)이 일치해야 한다.
	 *  또한 joinPoint는 반드시 첫 번째 매개변수로 선언해야 한다.
	 * */
	@AfterReturning(pointcut="logPointcut()", returning="result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {

		System.out.println("After Returning result: " + result);

		/* 설명. AfterReturning Advice를 활용한 반환값을 가공도 가능하다. */
		if(result != null && result instanceof Map) {
			((Map<Long, MemberDTO>) result).put(100L, new MemberDTO(222L, "반환 값 가공"));
		}
	}

	/* 설명. 4. AfterThrowing Advice
	 *  throwing 속성의 이름과 매개변수의 이름이 동일해야 한다.
	 * */
	@AfterThrowing(pointcut="logPointcut()", throwing="exception")
	public void logAfterThrowing(Throwable exception) {

		System.out.println("After Throwing exception: " + exception);
	}

	/* 설명. 5 Around Advice
	 *  Around Advice는 가장 강력한 advice이다.
	 *  이 advice는 joinpoint를 완전히 장악하기 때문에
	 *  앞에 살펴 본 advice 모두 Around advice로 조합할 수 있다.
	 *  심지어 원본 joinpoint를 언제 실행할지, 실행 자체를 안할지, 계속 실행할지 여부까지도 제어한다.
	 *  AroundAdvice의 joinpoint 매개변수는 ProceedingJoinPoint로 고정되어 있다.
	 *  JoinPoint의 하위 인터페이스로 원본 joinpoint의 진행 시점을 제어할 수 있다.
	 *  joinpoint 진행하는 호출을 잊는 경우가 자주 발생하기 때문에 주의해야 하며
	 *  최소한의 요건을 충족하면서도 가장 기능이 약한 advice를 쓰는게 바람직하다.
	 * */
	@Around("logPointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		System.out.println("Around Before: " + joinPoint.getSignature().getName());

		/* 필기. 타겟 메서드 동작 */
		Object result = joinPoint.proceed();

		System.out.println("Around After: " + joinPoint.getSignature().getName());

		/* 설명. 실행된 타켓 메서드 반환(다른 어드바이스가 다시 실행할 수 있도록 반환) */
		return result;
	}

}
