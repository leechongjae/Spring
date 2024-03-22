package com.ohgiraffers.section02.annotation.subsection04.resource;

import java.util.List;

import com.ohgiraffers.section02.common.Pokemon;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("pokemonServiceResource")
public class PokemonService {

	/* 설명. @Resource 어노테이션은 자바에서 제공하는 기본 어노테이션으로,
	 *  @Autowired와 같은 스프링 어노테이션과 다르게 name 속성 값으로 의존성 주입을 할 수 있다.
	 *  @Qualifier와 마찬가지로 @Primary보다 우선순위 높게 원하는 빈 이름(id)으로 하나의 빈을 주입받을 수 있다.
	 * */
	
//	@Resource(name = "pikachu")
//	private Pokemon pokemon;
//
//	public void pokemonAttack() {
//		pokemon.attack();
//	}

	/* 설명. List<Pokemon> 타입으로 변경한 뒤 name 속성을 따로 기재하지 않고 동작시킬 수 있다.
	 *  기본적으로는 name 속성을 통해 주입하지만 name 속성이 없을 경우 Type을 통해 의존성 주입한다.
	 * */
	@Resource
	private List<Pokemon> pokemonList;

	public void pokemonAttack() {
		pokemonList.forEach(Pokemon::attack);
	}

}
