package io.cloudtype.Demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cloudtype.Demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//	List<User> findFirst2ByUserNameLikeOrderByUserIdDesc(String name);
	
	int countByUserUid(Long id);
	
	int countByUserPhone(String userPhone);
	
	User findByUserPhone(String userPhone);
	
	// 특정 id 의 권한(들) 정보 가져오기
	List<String> findAuthoritiesByUserUid(Long userUid);
}
