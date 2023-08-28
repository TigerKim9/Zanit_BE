package io.cloudtype.Demo.entity;

import java.time.LocalDateTime;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Subscribe {

	//구독 고유번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscribeUid;
	
	//유저
	@ManyToOne
	@JoinColumn(name="user_uid",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private User userUid;
	
	//구독 이름
	private String subscribeName;
	
	
	//구독 시작일
	private LocalDateTime StartDate;
	
	//구독 만료일
	private LocalDateTime endDate;
	
	//구독 활성화 여부
	private boolean activated;
	
	public SubscribeDTO toDto() {
		return SubscribeDTO.builder()
				.subscribeUid(subscribeUid)
				.userUid(userUid.getUserUid())
				.subscribeName(subscribeName)
				.StartDate(StartDate)
				.endDate(endDate)
				.activated(activated)
				.build();
	}
}
