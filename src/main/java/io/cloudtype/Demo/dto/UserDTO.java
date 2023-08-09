package io.cloudtype.Demo.dto;

import io.cloudtype.Demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//유저 Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class UserDTO{

	UserDTO(){}
	
	//회원 고유번호
	private Long userUid;
	
	//회원 비밀번호
	private String userPassword;
	
	//회원 이름
	private String userName;
	
	//회원 이메일
	private String email;
	
	//회원 전화번호
	private String userPhone;
	
	//회원 성별
	private boolean userGender;	//0 : 여		1: 남
	
	//본인인증 여부
	private boolean kyc;
	
	//마케팅 동의 여부
	private boolean marketing;
	
	//구독여부
	private boolean subscribe;
	
	public User toEntity() {
		return User.builder()
				.userUid(userUid)
				.userPassword(userPassword)
				.userName(userName)
				.email(email)
				.userPhone(userPhone)
				.kyc(kyc)
				.marketing(marketing)
				.subscribe(subscribe)
				.build();
	}

	
}
