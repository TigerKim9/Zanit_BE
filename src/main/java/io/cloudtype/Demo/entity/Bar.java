package io.cloudtype.Demo.entity;

import java.util.List;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import io.cloudtype.Demo.dto.BarDTO;
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
public class Bar {

	//바 고유번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long barUid;
	
	//바 이름
	private String barName;
	
	
	//바 위치
	private String barLocation;
	
	//바 분위기
	private String barMood;
	
	//바 설명(공간 설명)
	private String barDetail;
	
	//바 주인 (유저)
	@ManyToOne
	@JoinColumn(name="bar_owner",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private User barOwner;
	
	//바 전화번호
	private String barPhone;
	
	private String coverCharge;
	
	public BarDTO toDto() {
		return BarDTO.builder()
				.barUid(barUid)
				.barName(barName)
				.barLocation(barLocation)
				.barMood(barMood)
//				.barOwner(barOwner.getUserUid())
				.barPhone(barPhone)
				.coverCharge(coverCharge)
				.build();
	}
}
