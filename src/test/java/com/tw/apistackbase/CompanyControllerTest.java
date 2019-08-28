package com.tw.apistackbase;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	 @Test
	 public void shouldReturnAllCompanies() throws Exception {
	     this.mockMvc.perform(get("/"))
	     			 .andDo(print())
	     			 .andExpect(status().isOk())
	     			 .andExpect(
	                 		MockMvcResultMatchers.jsonPath("$.companyName", CoreMatchers.is("网易"))

	     					 );
	 }
	    

}
