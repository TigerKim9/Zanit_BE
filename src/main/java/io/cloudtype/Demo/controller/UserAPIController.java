package io.cloudtype.Demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.Demo.dto.UserDTO;
import io.cloudtype.Demo.entity.User;
import io.cloudtype.Demo.repository.UserRepository;
import io.cloudtype.Demo.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserAPIController {
	

	
	UserRepository userRepository;
	
	UserService userService;
	
	@PostMapping("/signup")
	public int signup(@RequestBody UserDTO userDTO) {
		int idCheck = userService.idCheck(userDTO.getUserId());
		if(idCheck > 0) return -1;
		userService.addMember(userDTO);
		int result = userRepository.countByUserId(userDTO.getUserId());
		return result;	//0 : 회원가입 실패	1 : 회원가입 성공
		
	}
	
	@GetMapping("/testapi")
	public List<User> testapi(String email) {
		System.out.println("들어옴");
		List<User> user = userRepository.findByEmail(email);
		log.info("[FindSome]: " + user);
		return user;
	}
	
	@GetMapping("/testapi2")
	public String testapi2(String email) {
		log.info("들어옴");
		return "api 연결";
	}
}