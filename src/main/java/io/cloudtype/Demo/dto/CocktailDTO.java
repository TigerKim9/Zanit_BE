package io.cloudtype.Demo.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.Cocktail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//바 Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class CocktailDTO {

	
	//칵테일이 있는 바
	private Bar barUid;

	//칵테일 이름
	private String cocktailName;
	
	//칵테일 설명
	private String cocktailDetail;
	
	//사용자 추천 유형
	private int recoUser;
	
	//칵테일 가격
	private int cocktailPrice;
	
	//칵테일 사진
	private MultipartFile cocktailPic;
	
	private boolean activated;
	
	public Cocktail toEntity() {
		return Cocktail.builder()
				.barUid(barUid)
				.cocktailName(cocktailName)
				.cocktailDetail(cocktailDetail)
				.recoUser(recoUser)
				.cocktailPrice(cocktailPrice)
				.activated(activated)
				.build();
	}
}
