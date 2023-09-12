package io.cloudtype.Demo.controller;


import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.Demo.dto.CouponDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.security.CustomUserDetails;
import io.cloudtype.Demo.service.CouponService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CouponAPIController {

	private final CouponService couponService;
	
	
	@PostMapping("/couponUse")
	public int couponUse(@RequestBody CouponDTO couponDTO,
			@AuthenticationPrincipal CustomUserDetails user) {
		Long userUid = user.getUser().getUserUid();
		Bar barUid = couponDTO.getUsedBar();
		Cocktail cocktailUid = couponDTO.getUsedCocktail();
		Long couponUid = couponDTO.getCouponUid();
		if(couponService.useCoupon(userUid, barUid, cocktailUid, couponUid)){
			return 1;
		}else {
			//사용실패시
			return 0;
		}
		
	}
	
	//유저 내 쿠폰 함
	@PostMapping("/couponList")
	public List<CouponDTO> couponList(String email,
			@AuthenticationPrincipal CustomUserDetails user) {
//		if(user == null) {
//			return null;
//		}
		log.info("들어옴");
		List<CouponDTO> couponList = couponService.couponList(user.getUser().getUserUid());
		return couponList;
	}
	
	//쿠폰 이용 내역
	@PostMapping("/usedCouponList")
	public List<CouponDTO> usedCouponList(String email,
			@AuthenticationPrincipal CustomUserDetails user) {
//		if(user == null) {
//			return null;
//		}
		log.info("들어옴");
		List<CouponDTO> couponList = couponService.usedCoupons(user.getUser().getUserUid());
		return couponList;
	}
	
	
	
	
}