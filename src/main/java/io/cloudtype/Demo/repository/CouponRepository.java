package io.cloudtype.Demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cloudtype.Demo.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	//유저가 가진 모든 쿠폰
	List<Coupon> findByUserUid(Long userUid);
	
	//유저에 따른 쿠폰 사용이력
	List<Coupon> findByUserUidAndUsedTrue(Long userUid);
	
	//유저에 따른 사용하지 않은 쿠폰
	Coupon findByUserUidAndUsedFalse(Long userUid);
	
	List<Coupon> findByExpDateAfter(LocalDateTime sevenDays);
	
	String findCouponNameByCouponUid(Long couponUid);
	
	
}
