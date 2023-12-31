package io.cloudtype.Demo.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.cloudtype.Demo.dto.CocktailDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//칵테일 Entity
@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CocktailPic {

	// 칵테일사진 고유번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cocktailPicUid;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Cocktail cocktailUid;

	// 칵테일 사진 경로
	private String cocktailPicture;
	//칵테일 사진 원본이름
	private String cocktailOriginalName;
}
