package com.ohgiraffers.section03.annotationconfig.subsection01.java;


import com.ohgiraffers.common.MemberDAO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration("configurationSection03")
/* 설명. #1 basePackages를 사용해 간단히 범위를 지정해서 @Component 계열의 어노테이션을 스캔해 모두 bean으로 관리. */
//@ComponentScan(basePackages="com.ohgiraffers")
/* 설명. #2 범위 및 필터를 적용해 beand을 관리하는 경우(exludeFilters) */
//@ComponentScan(basePackages ="com.ohgiraffers",
//				excludeFilters =
//                    @ComponentScan.Filter(
//                        /* 설명. 2-1. 타입으로 설정 */
////						type= FilterType.ASSIGNABLE_TYPE,
////						classes= {MemberDAO.class}
//
//						/* 설명. 2-2. 어노테이션 종류로 설정 */
////						type=FilterType.ANNOTATION,
////						classes= {org.springframework.stereotype.Repository.class}
//
//                        /* 설명. 2-3. 표현식으로 설정 */
//                        /* Note. 표현식은 Spring 버전에 따라 안 될 수도 있음. */
////                        type=FilterType.REGEX,
////						pattern= {"com.ohgiraffers.section03.annotationconfig.*"}
//                    ))
/* 설명. #3 범위 및 필터를 적용해 beand을 관리하는 경우(includeFilters) */
//@ComponentScan(basePackages="com.ohgiraffers",
//		useDefaultFilters=false,
//		includeFilters= {
//				@ComponentScan.Filter(
//						/* 설명. excludeFilters에서 설정하는 방식과 동일하다 */
//                      /* Note. 표현식은 Spring 버전에 따라 안 될 수도 있음. */
//						type=FilterType.ASSIGNABLE_TYPE,
//						classes= {MemberDAO.class}
//				)
//		})
public class ContextConfiguration {
}
