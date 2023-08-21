package io.cloudtype.Demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.cloudtype.Demo.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//유저 Entity
@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseTimeEntity{

	//회원 고유번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userUid;
	
	//회원 비밀번호
	private String userPassword;
	
	//회원 이름
	private String userName;
	
	//회원 전화번호
	@Column(unique = true)
	private String userPhone;
	
	//회원 성별
	private boolean userGender;
	
	//본인인증 여부
	private boolean kyc;
	
	//마케팅 동의 여부
	private boolean marketing;
	
	//구독여부
	private boolean subscribe;
	
	//계정 권한
	private String role;
	
	public UserDTO toDto() {
		return UserDTO.builder()
				.userUid(userUid)
				.userPassword(userPassword)
				.userName(userName)
				.userPhone(userPhone)
				.kyc(kyc)
				.marketing(marketing)
				.subscribe(subscribe)
				.build();
	}
}
