package io.cloudtype.Demo.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.Demo.dto.CocktailDTO;
import io.cloudtype.Demo.repository.CocktailRepository;
import io.cloudtype.Demo.service.CocktailService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CocktailAPIController {

	CocktailRepository cocktailRepository;
	CocktailService cocktailService;

	//칵테일 {하나?} 등록 (관리자)
	@PostMapping("/registCocktail")
	public int registCocktail(@RequestBody CocktailDTO cocktailDTO) {
		Long cocktailUid = cocktailService.registCocktail(cocktailDTO);
		int result = cocktailRepository.countByCocktailUid(cocktailUid);
		return result; // 0 : 등록 실패 1 : 등록 성공

	}

	//칵테일 전체 리스트
	@GetMapping("/getCocktailList")
	public List<CocktailDTO> getCocktailList() {
		List<CocktailDTO> cocktailList = cocktailService.cocktailList();
		return cocktailList;
	}
}