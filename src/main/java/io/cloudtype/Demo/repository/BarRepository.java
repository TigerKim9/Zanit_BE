package io.cloudtype.Demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cloudtype.Demo.entity.Bar;
import io.cloudtype.Demo.entity.Cocktail;

@Repository
public interface BarRepository extends JpaRepository<Bar, Long> {

	List<Bar> findByBarNameContaining(String barName);
}
