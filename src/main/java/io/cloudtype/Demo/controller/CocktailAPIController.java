//package io.cloudtype.Demo.controller;
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import io.cloudtype.Demo.dto.CocktailDTO;
//import io.cloudtype.Demo.entity.Cocktail;
//import io.cloudtype.Demo.repository.CocktailRepository;
//import io.cloudtype.Demo.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@Slf4j
//public class CocktailAPIController {
//	
//
//	
//	CocktailRepository cocktailRepository;
//	
//	UserService userService;
//	
//	@PostMapping("/registCocktail")
//	public int registCocktail(@RequestBody Cocktail cocktail) {
//		cocktailRepository.save(cocktail);
//		int result = cocktailRepository.countById(cocktail.getBarUid());
//		return result;	//	0 : 등록 실패		1 : 등록 성공
//		
//	}
//	
//	@GetMapping("/getCocktailList")
//	public List<CocktailDTO> getCocktailList() {
//		List<CocktailDTO> cocktailList = cocktailRepository.findAll();
//	}
//	
//	@GetMapping("/testapi2")
//	public String testapi2(String email) {
//		log.info("들어옴");
//		return "api 연결";
//	}
//}