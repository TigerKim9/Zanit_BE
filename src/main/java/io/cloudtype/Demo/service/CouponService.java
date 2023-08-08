package io.cloudtype.Demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.cloudtype.Demo.dto.CouponDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.entity.Coupon;
import io.cloudtype.Demo.entity.User;
import io.cloudtype.Demo.repository.CouponRepository;
import io.cloudtype.Demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {

	private final CouponRepository CouponRepository;

	private final UserRepository userRepository;

	public boolean checkSubscribe(Long userUid) {
		Optional<User> user = userRepository.findById(userUid);

		boolean result = user.get().isSubscribe();
		return result;

	}

	// 유저에 쿠폰 전체 조회
	public List<CouponDTO> CouponList(Long userUid) {

		if (checkSubscribe(userUid)) {

			List<Coupon> CouponAllList = CouponRepository.findByUserUid(userUid);
			List<CouponDTO> CouponList = new ArrayList<>();

			// TODO 쿠폰이 조회되지 않을 시... 처리할 로직
//		if(CouponAllList.isEmpty()) return null;

			for (Coupon Coupons : CouponAllList) {
				CouponList.add(Coupons.toDto());
			}
			return CouponList;
		} else {
			return null;
		}
	}

	// 유저에 따른 쿠폰 사용 이력 조회
	public List<CouponDTO> usedCoupons(Long userUid) {

		// TODO 구독을 하지 않았거나 쿠폰이 없을 때 리턴할 값
		if (checkSubscribe(userUid)) {
			List<Coupon> CouponSearchList = CouponRepository.findByUserUidAndUsedTrue(userUid);
			List<CouponDTO> CouponList = new ArrayList<>();

			for (Coupon Coupons : CouponSearchList) {
				CouponList.add(Coupons.toDto());
			}

			return CouponList;
		} else {
			return null;
		}
	}

	// 유저가 쿠폰 사용하기
	// 사용 성공여부 반환
	public boolean useCoupon(Long userUid, Bar barUid, Cocktail cocktailUid, Long couponUid) {

		// TODO 향후 시간 로직에 따른 쿠폰 변경 필요할 듯함.
		// TODO 쿠폰이 없을 때 혹은 구독을 안했을 때 로직 구상 필요
		if (checkSubscribe(userUid)) {
			Coupon coupon = CouponRepository.findByUserUidAndUsedFalse(userUid);
			coupon.useBar(barUid);
			coupon.useCocktail(cocktailUid);
			coupon.changeUsed(true);
			return true;
		} else {
			return false;
		}
	}
}
