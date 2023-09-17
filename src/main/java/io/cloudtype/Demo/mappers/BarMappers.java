package io.cloudtype.Demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import io.cloudtype.Demo.dto.BarDTO;

@Mapper
public interface BarMappers {
	
	List<BarDTO> searchBarList();

}
