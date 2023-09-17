package io.cloudtype.Demo.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//칵테일 DTO
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CocktailPicDTO {

	// 칵테일사진 고유번호
	private Long cocktailPicUid;

	private Long cocktailUid;

	// 칵테일 사진 경로
	private String cocktailPicture;
	//칵테일 사진 원본이름
	private String cocktailOriginalName;
}
