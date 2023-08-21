package io.cloudtype.Demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//바 Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class AdminBarRegistDTO {

	private UserDTO user;
	
	//칵테일이 있는 바
	private BarDTO bar;
}
