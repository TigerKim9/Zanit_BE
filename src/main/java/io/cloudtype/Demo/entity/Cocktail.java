package io.cloudtype.Demo.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import io.cloudtype.Demo.dto.CocktailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//바 Entity
@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cocktail {

	//칵테일 고유번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cocktailUid;
	
	//칵테일이 있는 바
	@ManyToOne
	@JoinColumn(name="cocktail_bar",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Bar barUid;

	//칵테일 이름
	private String cocktailName;
	
	//칵테일 설명
	private String cocktailDetail;
	
	//칵테일 사진 경로
	private String cocktailPic;
	
	//사용자 추천 유형
	private int recoUser;	//	0: 입문자	1: 캐쥬얼드링커 2: 헤비드링커
	
	//칵테일 가격
	private int cocktailPrice;
	
	@Column
	@ColumnDefault("false")
	private boolean activated;
	
	public CocktailDTO toDto() {
		return CocktailDTO.builder()
				.barUid(barUid.getBarUid())
				.cocktailName(cocktailName)
				.cocktailDetail(cocktailDetail)
				.recoUser(recoUser)
				.cocktailPrice(cocktailPrice)
				.activated(activated)
				.build();
	}
}
