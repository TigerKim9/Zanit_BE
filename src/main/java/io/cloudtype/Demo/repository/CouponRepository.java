package io.cloudtype.Demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cloudtype.Demo.entity.Coupon;
import io.cloudtype.Demo.entity.User;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

	//유저가 가진 모든 쿠폰
	List<Coupon> findByUserUid(Long userUid);
	
	//유저에 따른 쿠폰 사용이력
	List<Coupon> findByUserUidAndUsedTrue(Long userUid);
	
	Coupon findByUserUidAndUsedFalse(Long userUid);
	
	
	
}
