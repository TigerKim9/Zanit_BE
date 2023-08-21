package io.cloudtype.Demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.Cocktail;

@Repository
public interface BarRepository extends JpaRepository<Bar, Long> {

	//바 이름으로 검색
	List<Bar> findByBarNameContaining(String barName);
	
	//바 갯수
	int countByBarUid(Long id);
	
	//바 분위기로 검색
	List<Bar> findByBarMood(String barMood);
}
