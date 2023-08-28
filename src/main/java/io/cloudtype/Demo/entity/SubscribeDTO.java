package io.cloudtype.Demo.entity;

import java.time.LocalDateTime;

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

//바 Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscribeDTO {

	//구독 고유번호
	private Long subscribeUid;
	
	//유저
	private Long userUid;
	
	//구독 이름
	private String subscribeName;
	
	
	//구독 시작일
	private LocalDateTime StartDate;
	
	//구독 만료일
	private LocalDateTime endDate;
	
	//구독 활성화 여부
	private boolean activated;
	
	public Subscribe toEntity() {
		return Subscribe.builder()
				.subscribeUid(subscribeUid)
				.subscribeName(subscribeName)
				.StartDate(StartDate)
				.endDate(endDate)
				.activated(activated)
				.build();
	}
}
