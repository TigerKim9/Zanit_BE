package io.cloudtype.Demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.cloudtype.Demo.dto.BarDTO;
import io.cloudtype.Demo.dto.CocktailDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.repository.BarRepository;
import io.cloudtype.Demo.repository.CocktailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarService {

	private final BarRepository barRepository;

	private final CocktailRepository cocktailRepository;

	// 바 전체 조회
	public List<BarDTO> barList() {
		List<Bar> BarAllList = barRepository.findAll();
		List<BarDTO> BarList = new ArrayList<>();

//		if(BarAllList.isEmpty()) return null;

		for (Bar Bars : BarAllList) {
			BarList.add(Bars.toDto());
		}

		return BarList;
	}

	// 바 검색
	public List<BarDTO> searchBars(String BarName) {
		List<Bar> BarSearchList = barRepository.findByBarNameContaining(BarName);
		List<BarDTO> BarList = new ArrayList<>();

		for (Bar Bars : BarSearchList) {
			BarList.add(Bars.toDto());
		}

		return BarList;
	}

	//
	// 칵테일 내용이 포함 된 특정 바
	public BarDTO barDetail(Long barUid) {
		Bar bar = barRepository.findById(barUid).get();
		List<Cocktail> cocktailbyBarList = cocktailRepository.findByBarUid(barUid);
		List<CocktailDTO> cocktailList = new ArrayList<>();
		for (Cocktail cocktails : cocktailbyBarList) {
			cocktailList.add(cocktails.toDto());
		}
		BarDTO barDTO = bar.toDto();
		barDTO.setBarsCocktail(cocktailList);
		return barDTO;
	}
}
