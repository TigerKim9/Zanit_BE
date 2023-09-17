package io.cloudtype.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cloudtype.Demo.entity.BarPic;

@Repository
public interface BarPicRepository extends JpaRepository<BarPic, Long> {

}
