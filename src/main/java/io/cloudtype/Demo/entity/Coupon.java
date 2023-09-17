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
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;

import io.cloudtype.Demo.dto.CouponDTO;
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
@DynamicUpdate
public class Coupon extends BaseTimeEntity{

	//쿠폰 고유번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long couponUid;
	
	//쿠폰을 가지고 있는 유저
	@ManyToOne
	@JoinColumn(name="coupon_user",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private User userUid;
	
	//쿠폰이름
	private String couponName;
	
	//사용된 바
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Bar usedBar;

	//사용된 칵테일
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private Cocktail usedCocktail;
	
	private LocalDateTime usedTime;
	
	//커버차지 비용
	private int coverCharge;
	
	//쿠폰 유효기간
	private LocalDateTime expDate;
	
	//사용 여부
	private boolean used; //	0:미사용 1:사용
	
	
	public void useBar(Bar barUid) {
        this.usedBar = barUid;
    }
	
	public void useCocktail(Cocktail usedCocktail) {
        this.usedCocktail = usedCocktail;
    }
	
	public void useTime(LocalDateTime usedTime) {
		this.usedTime = usedTime;
	}
	
	public void changeUsed(boolean used) {
        this.used = used;
    }
	
	public CouponDTO toDto() {
		return CouponDTO.builder()
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
