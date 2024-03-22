package com.ohgiraffers.section02.annotation.subsection01.primary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {
		
		ApplicationContext context 
			= new AnnotationConfigApplicationContext("com.ohgiraffers.section02");

		/* 설명. 현재 관리중인 bean들을 출력 */
		String[] beanNames = context.getBeanDefinitionNames();
		for(String beanName : beanNames) {
			System.out.println("beanName: " + beanName);
		}
		
		PokemonService pokemonService = context.getBean("pokemonServicePrimary", PokemonService.class);
		
		pokemonService.pokemonAttack();
		
	}

}







