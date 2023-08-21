package io.cloudtype.Demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//바사진 Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class BarPicDTO {

	//바사진 고유번호
	private Long barPicUid;

	private Bar barUid;
	private Long barUids;
	
	//바 사진 경로
	private String barPicture;
	
	public BarPic toEntity() {
		
		return BarPic.builder()
				.barPicUid(barPicUid)
				.barUid(barUid)
				.barPicture(barPicture)
				.build();
	}
}
