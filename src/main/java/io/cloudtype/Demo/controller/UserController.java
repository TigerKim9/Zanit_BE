package io.cloudtype.Demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/mycoupon")
	public String myCoupon(Model model) {
		
		return "mycoupon";
		
	}
	
}