package io.cloudtype.Demo.dto;

import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.Cocktail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//바 Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class CocktailDTO {

	//칵테일 고유번호
	private Long cocktailUid;
	
	//칵테일이 있는 바
	private Bar barUid;

	//칵테일 이름
	private String cocktailName;
	
	//칵테일 설명
	private String cocktailDetail;
	
	//칵테일 가격
	private int cocktailPrice;
	
	public Cocktail toEntity() {
		return Cocktail.builder()
				.cocktailUid(cocktailUid)
				.barUid(barUid)
				.cocktailName(cocktailName)
				.cocktailDetail(cocktailDetail)
				.cocktailPrice(cocktailPrice)
				.build();
	}
}
