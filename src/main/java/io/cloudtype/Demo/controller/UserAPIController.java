package io.cloudtype.Demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.Demo.dto.UserDTO;
import io.cloudtype.Demo.entity.User;
import io.cloudtype.Demo.repository.UserRepository;
import io.cloudtype.Demo.service.UserService;
import kr.co.bootpay.Bootpay;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserAPIController {
	

	
	private final UserRepository userRepository;
	
	private final UserService userService;
	
	
	
	
	@PostMapping("/signup")
	public int signup(@RequestBody UserDTO userDTO) {
		int idCheck = userService.idCheck(userDTO.getEmail());
		if(idCheck > 0) return -1;
		userService.addMember(userDTO);
		int result = userRepository.countByEmail(userDTO.getEmail());
		return result;	//0 : 회원가입 실패	1 : 회원가입 성공
		
	}
	
	@GetMapping("/testapi")
	public User testapi(String email) {
		System.out.println("들어옴");
		User user = userRepository.findByEmail(email);
		log.info("[FindSome]: " + user);
		return user;
	}
	
	@GetMapping("/testapi2")
	public String testapi2(String email) {
		log.info("들어옴");
		return "api 연결";
	}
	
	@PostMapping("/subscribePay")
	public HashMap<String, Object> BootPay() {
		
		//https://api.bootpay.co.kr/v2
		try {
		    Bootpay bootpay = new Bootpay("646f378a3049c8001df8befc", "hjIFSp0BFQNhOljWUDKYa8tefBwOXgew2tU8Ke1fi/4=");
		    HashMap<String, Object> res = bootpay.getAccessToken();
		    if(res.get("error_code") == null) { //success
		        System.out.println("goGetToken success: " + res);
		    } else {
		        System.out.println("goGetToken false: " + res);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
		
	}
}