package io.cloudtype.Demo;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import kr.co.bootpay.Bootpay;

public class BootPayTest {

	@Test
	public void bootPayTest(String receiptId) {
		// 토큰 발급
		try {
			Bootpay bootpay = new Bootpay("646f378a3049c8001df8befc", "hjIFSp0BFQNhOljWUDKYa8tefBwOXgew2tU8Ke1fi/4=");
			HashMap<String, Object> token = bootpay.getAccessToken();
			if (token.get("error_code") == null) { // success
				System.out.println("goGetToken success: " + token);
			} else {
				System.out.println("goGetToken false: " + token);
			}

			HashMap<String, Object> res = bootpay.confirm(receiptId);

			if (res.get("error_code") == null) { // success
				System.out.println("confirm success: " + res);
			} else {
				System.out.println("confirm false: " + res);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
//
	}
}
