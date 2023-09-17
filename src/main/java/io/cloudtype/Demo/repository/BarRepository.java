package io.cloudtype.Demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.cloudtype.Demo.entity.Bar;

@Repository
public interface BarRepository extends JpaRepository<Bar, Long>,JpaSpecificationExecutor<Bar>{

	//바 이름으로 검색
	List<Bar> findByBarNameContaining(String barName);
	
	//바 갯수
	int countByBarUid(Long id);
	
	//바 분위기로 검색
	List<Bar> findByBarMood(String barMood);
	
	//관리자 단 바 정보
	Optional<Bar> findByBarOwner(Long userId);
	
	@Query(value = "SELECT * FROM bar order by RAND()",nativeQuery = true)
	List<Bar> randomBars();
}
