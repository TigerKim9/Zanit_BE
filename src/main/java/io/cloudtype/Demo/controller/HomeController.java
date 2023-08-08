package io.cloudtype.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
public class HomeController {
	
	//첫 메인화
	@GetMapping("/home")
	public String home(Model model) {
		
		return "/home";
		
	}
	
	//로그인
	@GetMapping("/login")
	public String login(Model model) {
		
		return "login";
		
	}
	
	//회원가입
	@GetMapping("/signup")
	public String signUp(Model model) {
		
		return "signup";
		
	}
	
	//바 검색 페이지
	@GetMapping("/findingBar")
	public String findingBar(Model model) {
		
		return "findingBar";
		
	}
	
	//TODO 결과페이지를 따로 연결하지 말고 비동기를 사용하여 SPA 방식으로 화면단만 바꿔주는 것이 어떨까.
	@GetMapping("/subscribe")
	public String subscribe(Model model) {
		
		return "subscribe";
		
	}
	

}
