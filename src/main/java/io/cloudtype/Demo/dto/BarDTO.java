package io.cloudtype.Demo.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//바 DTO
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BarDTO {

	//바 고유번호
	private Long barUid;
	
	//바 이름
	private String barName;
	
	//바 위치
	private String barLocation;
	
	//바 사진 직접적인 파일..
	private List<MultipartFile> barPics;
	
	//바 분위기
	private String barMood;
	//바 설명(공간 설명)
	private String barDetail;
	
	//바에 있는 칵테일
	private List<CocktailDTO> barsCocktail;
	
	//바 주인 (유저)
	private Long barOwner;
	
	//바 전화번호
	private String barPhone;
	
	private String coverCharge;
	
	public Bar toEntity() {
		return Bar.builder()
				.barUid(barUid)
				.barName(barLocation)
				.barMood(barMood)
				.barDetail(barDetail)
				.barPhone(barPhone)
				.coverCharge(coverCharge)
				.build();
	}
}
