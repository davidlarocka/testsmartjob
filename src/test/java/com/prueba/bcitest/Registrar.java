package com.prueba.bcitest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;




@SpringBootTest
@AutoConfigureMockMvc
public class Registrar {
	
	@Autowired
	MockMvc mockmvc; 

	@Test
	@Order(1) 
	public void test1_create_simple_user_successfull_with_correctData() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.post("/registro")
				.content("{ \"nombre\" : \"david garcia\", \"email\" : \"davidlarocka@gmail.com\", \"password\" : \"david123\", \"phones\": [ { \"number\": \"1234567\", \"citycode\": \"1\", \"contrycode\": \"57\" } ] }")
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk());	
	}
	
	@Test
	@Order(2) 
	public void test2_no_create_Simple_user_with_email_is_used_by_otheruser() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.post("/registro")
				.content("{ \"nombre\" : \"david garcia\", \"email\" : \"davidlarocka@gmail.com\", \"password\" : \"david123\", \"phones\": [ { \"number\": \"1234567\", \"citycode\": \"1\", \"contrycode\": \"57\" } ] }")
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isNotAcceptable());	
	}
	
	@Test
	@Order(3) 
	public void test3_no_create_simple_user_fail_with_incorrectdata_email_no_format() throws Exception {
		mockmvc.perform(MockMvcRequestBuilders.post("/registro")
				//CORREO MALO
				.content("{ \"nombre\" : \"david garcia\", \"email\" : \"davidlarockagmail2.com\", \"password\" : \"david123\", \"phones\": [ { \"number\": \"1234567\", \"citycode\": \"1\", \"contrycode\": \"57\" } ] }")
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isNotAcceptable());	
	}
	
}
