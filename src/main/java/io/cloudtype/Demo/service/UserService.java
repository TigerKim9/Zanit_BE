package io.cloudtype.Demo.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.cloudtype.Demo.dto.UserDTO;
import io.cloudtype.Demo.entity.User;
import io.cloudtype.Demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;

	
	
	
	
	//중복아이디 체크
	public int idCheck(String email) {
		int cnt = userRepository.countByEmail(email);
		log.info("idCheck = {}",cnt);
		//검색기록 TODO
//		userRepository.searchLog(userId);
		return cnt;
	}
//	
	// 회원가입
	// ROLE_MEMBER 권한 부여
	
	@Transactional
	public void addMember(UserDTO userDTO) {
		userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
		User user = userDTO.toEntity();
		
		userRepository.save(user);
//		userRepository.addAuth(user.getUserId(), "ROLE_MEMBER");
		
	}
	
	//나의 구독여부 조회
//	
//	// 회원삭제
//	@Transactional
//	public int deleteMember(User user) {
//		userRepository.delete(user);
//		return cnt;
//	}
//	
//	// 특정 id(username) 의 정보 가져오기
//	public User findById(String id) {
//		//검색기록
//		userRepository.searchLog(id);
//		return userRepository.findById(id);
//	}
//	
//	// 특정 id 의 권한(들) 정보 가져오기
//	public List<String> selectAuthoritiesById(Long userId){
//		return userRepository.selectAuthoritiesById(userId);
//	}
}
//
//
//
//
//
//
//
//
//
//
//
//








