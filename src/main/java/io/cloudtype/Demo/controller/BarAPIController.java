package io.cloudtype.Demo.controller;

import java.io.IOException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.Demo.dto.BarDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.repository.BarRepository;
import io.cloudtype.Demo.security.CustomUserDetails;
import io.cloudtype.Demo.service.BarService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api
@RestController
@Slf4j
@RequiredArgsConstructor
public class BarAPIController {

	private final BarRepository barRepository;

	private final BarService barService;

	// 바 하나 클릭시 보여줄 바 정보
	@GetMapping("/barInfo")
	public BarDTO barInfo(
			//@RequestBody 
			Long barId) {
		log.info("long barId= {}",barId);
		BarDTO barDTO = barService.barDetail(barId);
		return barDTO;
	}

	/*------------------------------------------------------admin----------------------------------*/

	@PostMapping("/registBar")
	public int registBar(@RequestBody BarDTO barDTO,
			@AuthenticationPrincipal CustomUserDetails user) {
		if(user == null) return -2;
		barDTO.setBarOwner(user.getUser());
		int result = 0;
		try {
			Bar bar = barService.barRegist(barDTO);
			result = barRepository.countByBarUid(bar.getBarUid());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}