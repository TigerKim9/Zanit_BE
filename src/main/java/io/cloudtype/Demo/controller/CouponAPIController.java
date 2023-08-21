package io.cloudtype.Demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.Demo.dto.CouponDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.Cocktail;
import io.cloudtype.Demo.security.CustomUserDetails;
import io.cloudtype.Demo.service.CouponService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coupon")
@RequiredArgsConstructor
public class CouponAPIController {

	private final CouponService couponservice;
	
	
	@PostMapping("/couponUse")
	public int couponUse(@RequestBody CouponDTO couponDTO,
			@AuthenticationPrincipal CustomUserDetails user) {
		Long userUid = user.getUser().getUserUid();
		Bar barUid = couponDTO.getUsedBar();
		Cocktail cocktailUid = couponDTO.getUsedCocktail();
		Long couponUid = couponDTO.getCouponUid();
		if(couponservice.useCoupon(userUid, barUid, cocktailUid, couponUid)){
			return 1;
		}else {
			//사용실패시
			return 0;
		}
		
	}
	
}