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

import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONObject;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
	@Autowired
    private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	 @Test
	 public void shouldReturnAllCompanies() throws Exception {
	     this.mockMvc.perform(get("/companies/"))
	     			 .andDo(print())
	     			 .andExpect(status().isOk())
	     			 .andExpect(
	     					content().string(containsString("中原银行")));
	 }
	  
	 @Test
	 public void shouldReturnCompany() throws Exception{
		 Employee[] employees = {new Employee(1,"小红",20,"female",20),new Employee(2,"小红",20,"male",20)};
		 Company company = new Company(1,"中原银行",2000,employees);
		 this.mockMvc.perform(get("/companies/1"))
		 					 .andDo(print())
		 					 .andExpect(
		 	                		MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(1))
		 							)
		 					 .andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.companyName", CoreMatchers.is("中原银行"))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[0].id", CoreMatchers.is(1))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[0].name", CoreMatchers.is("小红"))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[0].age", CoreMatchers.is(20))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[0].gender", CoreMatchers.is("female"))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[0].salary", CoreMatchers.is(20))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[1].id", CoreMatchers.is(2))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[1].name", CoreMatchers.is("小明"))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[1].age", CoreMatchers.is(20))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[1].gender", CoreMatchers.is("male"))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$.employees[1].salary", CoreMatchers.is(20))
		 							 );
	 }
	 
	 @Test
	 public void shouldReturnEmlpoyeeOfOneCompany() throws Exception{
		 Employee[] employees = {new Employee(1,"小红",20,"female",20),new Employee(2,"小红",20,"male",20)};
		 Company company = new Company(1,"中原银行",2000,employees);
		 this.mockMvc.perform(get("/companies/1/employees"))
		 					 .andDo(print())
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(1))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is("小红"))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[0].age", CoreMatchers.is(20))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[0].gender", CoreMatchers.is("female"))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[0].salary", CoreMatchers.is(20))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[1].id", CoreMatchers.is(2))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[1].name", CoreMatchers.is("小明"))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[1].age", CoreMatchers.is(20))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[1].gender", CoreMatchers.is("male"))
		 							 )
		 					.andExpect(
			 	                	MockMvcResultMatchers.jsonPath("$[1].salary", CoreMatchers.is(20))
		 							 );
	 }

@Test
public void shouldReturnCompaniesByPage() throws Exception{
		this.mockMvc.perform(get("/companies?page=1&limit=5"))
							.andDo(print())
							.andExpect(status().isOk())
							.andExpect(content().string(containsString("中原银行")));	     					

	}

//@Test
//public void shouldReturnStatusOfInsertCompany() throws Exception{
//	this.mockMvc.perform(get("/companies/insert"))
//				.andDo(print())
//				.andExpect(status().isCreated());
//				
//}
}

