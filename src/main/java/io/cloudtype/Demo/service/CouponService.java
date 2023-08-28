package io.cloudtype.Demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.cloudtype.Demo.dto.CouponDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.entity.Coupon;
import io.cloudtype.Demo.entity.User;
import io.cloudtype.Demo.repository.CouponRepository;
import io.cloudtype.Demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponService {

	private final CouponRepository couponRepository;

	private final UserRepository userRepository;

	public boolean checkSubscribe(Long userUid) {
		Optional<User> user = userRepository.findById(userUid);

		boolean result = user.get().isSubscribe();
		return result;

	}
	
	//매일 12시 00분 일주일 넘은 쿠폰 유효기간이 만료
	@Scheduled(cron = "0 0 12 * * *")
	public void checkExpiredCoupon() {
		try {
			LocalDateTime sevenDays = LocalDateTime.of(0, 0, 7, 0, 0, 0);
			List<Coupon> expOver = couponRepository.findByExpDateAfter(sevenDays);
			
			for(Coupon exp : expOver) {
				CouponDTO couponDto = exp.toDto();
				couponDto.setUsed(true);
				exp = couponDto.toEntity();
				couponRepository.saveAndFlush(exp);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("검색된 쿠폰 없음.");
		}
	}

	// 유저의 쿠폰 전체 조회
	public List<CouponDTO> couponList(Long userUid) {

		if (checkSubscribe(userUid)) {

			List<Coupon> CouponAllList = couponRepository.findByUserUid(userUid);
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
			List<Coupon> CouponSearchList = couponRepository.findByUserUidAndUsedTrue(userUid);
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
	@Transactional
	public boolean useCoupon(Long userUid, Bar barUid, Cocktail cocktailUid, Long couponUid) {

		// TODO 향후 시간 로직에 따른 쿠폰 변경 필요할 듯함.
		// TODO 쿠폰이 없을 때 혹은 구독을 안했을 때 로직 구상 필요
		if (checkSubscribe(userUid)) {
			Coupon coupon = couponRepository.findByUserUidAndUsedFalse(userUid);
			coupon.useBar(barUid);
			coupon.useCocktail(cocktailUid);
			coupon.changeUsed(true);
			return true;
		} else {
			return false;
		}
	}
}
