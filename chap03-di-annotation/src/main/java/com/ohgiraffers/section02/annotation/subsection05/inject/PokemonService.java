package com.ohgiraffers.section02.annotation.subsection05.inject;

import com.ohgiraffers.section02.common.Pokemon;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.stereotype.Service;

@Service("pokemonServiceInject")
public class PokemonService {

	/* 설명. Inject 라이브러리를 활용한 필드 주입 */
	/* 필기. 필드 주입 */
	@Inject
	@Named("pikachu")
	private Pokemon pokemon;

	/* 필기. 생성자 주입 */
//	@Inject
//	// @Named의 경우 메소드 레벨, 파라미터 레벨에서 사용 가능
//	public PokemonService(@Named("pikachu") Pokemon pokemon) {
//		this.pokemon = pokemon;
//	}

	/* 필기. setter 주입 */
//	@Inject
//	public void setPokemon(@Named("pikachu") Pokemon pokemon) {
//		this.pokemon = pokemon;
//	}

	public void pokemonAttack() {
		pokemon.attack();
	}


}
