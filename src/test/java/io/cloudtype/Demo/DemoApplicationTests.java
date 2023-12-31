package io.cloudtype.Demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.cloudtype.Demo.dto.UserDTO;
import io.cloudtype.Demo.entity.Coupon;
import io.cloudtype.Demo.entity.User;
import io.cloudtype.Demo.repository.CouponRepository;
import io.cloudtype.Demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
//@DataJpaTest
@Slf4j
class DemoApplicationTests {
	
    @Autowired
    UserRepository userRepository;
    @Autowired
    CouponRepository couponRepository;
    
//    @AfterEach
//    public void cleanup() {
//        userRepository.deleteAll();
//    }

//    @Test
    public void BaseTimeEntity_등록 () {
        //given
        LocalDateTime now = LocalDateTime.now();
        userRepository.save(User.builder()
                .userName("김자닛")
                .userPhone("01048398349")
        		.build());
        //when
        List<User> userList = userRepository.findAll();

        //then
        User users = userList.get(0);
        assertTrue(users.getCreatedDate().isAfter(now));
        assertTrue(users.getModifiedDate().isAfter(now));
    }
    
//    @Test
    public void 엔티티로_바꾼다() {
    	UserDTO dto = UserDTO.builder()
        .userName("김자닛")
        .userPhone("01048398349")
		.build();
    	User user = dto.toEntity();
    	log.info("{}",user);
    	log.info("{}",user.toString());
    	
    }
    
    @Test
    public void 개별_컬럼의_출력() {
    	
    	int userCount = userRepository.countByUserUid(1L);
    	
//    	String uid = couponRepository.findCouponNameByCouponUid(1L);
    	List<Coupon> couponList = couponRepository.findByUserUid(1L);
    	
    	
    	System.out.println(userCount);
//    	System.out.println(uid);
    	log.info("User uid = {}",userCount);
//    	log.info("String uid = {}",uid.toString());
    	log.info("couponList = {}",couponList.toString());
    }
}

