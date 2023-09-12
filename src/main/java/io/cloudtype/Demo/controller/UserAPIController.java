package io.cloudtype.Demo.controller;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.cloudtype.Demo.dto.CouponDTO;
import io.cloudtype.Demo.dto.UserDTO;
import io.cloudtype.Demo.entity.User;
import io.cloudtype.Demo.repository.UserRepository;
import io.cloudtype.Demo.repository.UserRepository.UserInfoMapping;
import io.cloudtype.Demo.security.CustomUserDetails;
import io.cloudtype.Demo.service.CouponService;
import io.cloudtype.Demo.service.UserService;
import kr.co.bootpay.Bootpay;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class UserAPIController {
	

	
	private final UserRepository userRepository;
	
	private final UserService userService;
	
	private final CouponService couponService;
	
	
	
	
	@PostMapping("/signup")
	public int signup(@RequestBody UserDTO userDTO) {
		int idCheck = userService.idCheck(userDTO.getUserPhone());
		if(idCheck > 0) return -1;
		userService.addMember(userDTO);
		int result = userRepository.countByUserPhone(userDTO.getUserPhone());
		return result;	//0 : 회원가입 실패	1 : 회원가입 성공
		
	}
	
	@GetMapping("/testapi")
	public User testapi(String email) {
		System.out.println("들어옴");
		User user = userRepository.findByUserPhone(email);
		User list = userRepository.findRoleByUserUid(11l);
		List<UserInfoMapping> column = userRepository.findByUserUid(2l);
		for(UserInfoMapping columns : column) {
			
			log.info("username ={}",columns.getUserName());
		}
		log.info("list ===={}",list);
		log.info("[FindSome]: " + user);
		return user;
	}
	
	@GetMapping("/session")
	public String testSession(HttpSession session,HttpServletRequest request,
			@AuthenticationPrincipal CustomUserDetails user) {
		log.info("User == {}",user.getUser());
		log.info("User AUTHORIZES == {}",user.getAuthorities().toString());
		String oid = session.getId();
		log.info("oid = {}",oid);
		System.out.println(session);
		String total = "세션값 : " + oid + "\n적나라한 로그인 된 유저정보 : " +user.getUser().toString();
		
		
		return total;
	}
	

	@PostMapping("/findPw")
	public int findPw(String phoneNumber) {
		int idCheck = userService.idCheck(phoneNumber);
		if(idCheck > 0) return -1;
		//TODO 카카오톡으로 어떻게 넘길건데....
		return 0;
	}
	
	@PostMapping("/resetPw")
	public int resetPw(@RequestBody Map<String,Object> pw) {
		int result = userService.resetPw(pw);
		return result;
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