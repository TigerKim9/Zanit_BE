package io.cloudtype.Demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cloudtype.Demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	List<User> findFirst2ByUserNameLikeOrderByUserIdDesc(String name);
	User findByUserId(String id);
	
	int countByUserId(String userId);
	
	int countByEmail(String email);
	
	User findByEmail(String email);
	
	// 특정 id 의 권한(들) 정보 가져오기
	List<String> findAuthoritiesByUserUid(Long userUid);
}
