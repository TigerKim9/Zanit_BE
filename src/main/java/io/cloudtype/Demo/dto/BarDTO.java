package io.cloudtype.Demo.dto;

import java.util.List;

import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.User;
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
public class BarDTO {

	//바 고유번호
	private Long barUid;
	
	//바 이름
	private String barName;
	
	//바 위치
	private String barLocation;
	
	//바 분위기
	private String barMood;
	
	//바에 있는 칵테일
	private List<CocktailDTO> barsCocktail;
	
	//바 주인 (유저)
	private User barOwner;
	
	//바 전화번호
	private String barPhone;
	
	public Bar toEntity() {
		return Bar.builder()
				.barUid(barUid)
				.barName(barLocation)
				.barMood(barMood)
				.barOwner(barOwner)
				.barPhone(barPhone)
				.build();
	}
}
