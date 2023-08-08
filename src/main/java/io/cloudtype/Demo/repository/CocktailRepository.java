package io.cloudtype.Demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cloudtype.Demo.entity.Cocktail;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {

	
	int countByCocktailUid(Long cocktailUid);
	
	List<Cocktail> findByCocktailNameContaining(String cocktailName);
	
	List<Cocktail> findByBarUid(Long barUid);

}
