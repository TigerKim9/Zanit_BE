package io.cloudtype.Demo.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.Demo.dto.BarDTO;
import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.repository.BarRepository;
import io.cloudtype.Demo.service.BarService;

@RestController
@RequestMapping("/bar")
public class BarAPIController {

	BarRepository barRepository;
	
	BarService barService;
	
	//바 하나 클릭시 보여줄 바 정보
	@GetMapping("/barInfo")
	public BarDTO barInfo(Long barId) {
		BarDTO barDTO = barService.barDetail(barId);
		return barDTO;
	}
	
}