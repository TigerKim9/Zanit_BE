package io.cloudtype.Demo.repository;

import org.springframework.data.jpa.domain.Specification;

import io.cloudtype.Demo.entity.Bar;

public class BarSpecification {
	

	    public static Specification<Bar> equalBarName(String barName) {
	        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("barName"), barName);
	    }

	    public static Specification<Bar> equalBarMood(String barMood) {
	    	return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("barMood"), barMood);
	    }

	    public static Specification<Bar> equalBarLocation(String barLocation) {
	        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("barLocation"), barLocation);
	    }

}
