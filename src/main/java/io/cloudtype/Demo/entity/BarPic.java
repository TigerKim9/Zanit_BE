package io.cloudtype.Demo.entity;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

//바사진 Entity
@Getter
@Entity
@ToString
@AllArgsConstructor
@Builder
public class BarPic {

	//바사진 고유번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long barPicUid;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Bar barUid;
	
	//바 사진 경로
	private String barPicture;
	
	public BarPicDTO toDto() {
		
		return BarPicDTO.builder()
				.barPicUid(barPicUid)
				.barUid(barUid)
				.barPicture(barPicture)
				.build();
	}
}
