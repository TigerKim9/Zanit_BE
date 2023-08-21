package io.cloudtype.Demo.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.cloudtype.Demo.dto.CocktailDTO;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.repository.CocktailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CocktailService {
	
	private final CocktailRepository cocktailRepository;
	
	

	
	
	
	
	//칵테일 전체 조회
	public List<CocktailDTO> cocktailList() {
		List<Cocktail> cocktailAllList = cocktailRepository.findAll();
		List<CocktailDTO> cocktailList = new ArrayList<>();
		
//		if(cocktailAllList.isEmpty()) return null;
		
		for(Cocktail cocktails : cocktailAllList) {
			cocktailList.add(cocktails.toDto());
		}
		
		return cocktailList;
	}
	
	// 칵테일 검색
	public List<CocktailDTO> searchCocktails(String cocktailName) {
		List<Cocktail> cocktailSearchList = cocktailRepository.findByCocktailNameContaining(cocktailName);
		List<CocktailDTO> cocktailList = new ArrayList<>();
		
		for(Cocktail cocktails : cocktailSearchList) {
			cocktailList.add(cocktails.toDto());
		}
		
		return cocktailList;
	}
	
	//바에 있는 칵테일 목록
	public List<CocktailDTO> cocktailsByBar(Long barUid) {
		List<Cocktail> cocktailbyBarList = cocktailRepository.findByBarUid(barUid);
		List<CocktailDTO> cocktailList = new ArrayList<>();
		for(Cocktail cocktails : cocktailbyBarList) {
			cocktailList.add(cocktails.toDto());
		}
		return cocktailList;
	}
	
	public Long registCocktail(CocktailDTO cocktailDTO) {
		
		Cocktail cocktail = cocktailDTO.toEntity();
		//TODO 세이브 후 바로 반영이 되었나 확인하기
		Long result = cocktailRepository.save(cocktail).getCocktailUid();
		return result;
	}
}








