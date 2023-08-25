package io.cloudtype.Demo.dto;

import java.time.LocalDateTime;

import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.BaseTimeEntity;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.entity.Coupon;
import io.cloudtype.Demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//쿠폰 DTO
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class CouponDTO extends BaseTimeEntity{

	//쿠폰 고유번호
	private Long couponUid;
	
	//쿠폰을 가지고 있는 유저
	private User userUid;
	
	//쿠폰이름
	private String couponName;
	
	//사용된 바
	private Bar usedBar;

	//사용된 칵테일
	private Cocktail usedCocktail;
	
	//커버차지 비용
	private int coverCharge;
	
	
	//쿠폰 유효기간
	private LocalDateTime expDate;
	
	//사용 여부
	private boolean used;//	0:미사용	1:사용
	
	public Coupon toEntity() {
		return Coupon.builder()
				.couponUid(couponUid)
				.userUid(userUid)
				.usedBar(usedBar)
				.usedCocktail(usedCocktail)
				.coverCharge(coverCharge)
				.expDate(expDate)
				.used(used)
				.build();
	}
	
	
}
