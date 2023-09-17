package io.cloudtype.Demo.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.Demo.dto.BarDTO;
import io.cloudtype.Demo.dto.CouponDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.repository.BarRepository;
import io.cloudtype.Demo.security.CustomUserDetails;
import io.cloudtype.Demo.service.BarService;
import io.cloudtype.Demo.service.CouponService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
	
	private final BarService barService;
	
	private final CouponService couponService;
	
	private final BarRepository barRepository;

	// 바 하나 클릭시 보여줄 바 정보
	@GetMapping("/adminsBar")
	public BarDTO barInfo(
			// @RequestBody
			Long userId) {
		log.info("Admins userId= {}", userId);
		BarDTO barDTO = barService.adminsBar(userId);
		return barDTO;
	}
	
	/*------------------------------------------------------admin----------------------------------*/

	@PostMapping("/registBar")
	public int registBar(@RequestBody BarDTO barDTO,
			@AuthenticationPrincipal CustomUserDetails user) {
		if(user == null) return -2;
		barDTO.setBarOwner(user.getUser().getUserUid());
		int result = 0;
		Long bar = barService.registBar(barDTO);
		result = barRepository.countByBarUid(bar);

		return result;
	}

	
	@GetMapping("/orderList")
	public List<CouponDTO> orderList(@AuthenticationPrincipal CustomUserDetails user){
		Long userUid = user.getUser().getUserUid();
		
		Long barUid = barRepository.findByBarOwner(userUid).get().getBarUid();
		
		return couponService.orderList(userUid, barUid);
		
	}
}