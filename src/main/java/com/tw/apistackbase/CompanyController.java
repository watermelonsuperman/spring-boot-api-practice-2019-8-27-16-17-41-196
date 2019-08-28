package com.tw.apistackbase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	List<Company> companies = new ArrayList();
	
	@GetMapping(path = "/")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<String> getAllCompanies(){
		Employee[] employees = {new Employee(1,"小红",20,"female",20),new Employee(2,"小明",20,"male",20)};
		companies.add(new Company(1,"中原银行",2000,employees));
		List<String> companyNames = new ArrayList();
		for(Company company : companies){
			companyNames.add(company.getCompanyName());
		}
		return companyNames;
	}
	
	@GetMapping(path = "/{id}")
	@ResponseBody
	public ResponseEntity<Company> getOneCompany(@PathVariable int id){
		for(Company company:companies){
			if(company.getId() == id){
				return ResponseEntity.ok(company);
			}
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/{id}/employees")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public  Employee[] getEmlpoyeeOfOneCompany(@PathVariable int id){
		Employee[] employees = new Employee[1];
		for(Company company:companies){
			if(company.getId() == id){
				employees = company.getEmployees();
			}
		}
		return employees;
	}
	
	@GetMapping(path = "")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public  List<String> getCompaniesByPage(@RequestParam(required = false,name = "page") int page,@RequestParam(required = false,name = "limit")int limit){
		List<String> companyNames = new ArrayList();
		List<String> resultNames = new ArrayList();
		for(Company company : companies){
			companyNames.add(company.getCompanyName());
		}
		if(companyNames.size()<limit){
			resultNames = companyNames;
		}else{
			for(int i = limit * (page-1);i < limit * page;i++){
				resultNames.add(companyNames.get(i));
			}
		}
		
		
		return resultNames;
	}
	
	@RequestMapping(path = "/insert")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Company> addCompany(@RequestBody Company company){
		companies.add(company);
		
		return companies;
		
	}
	@PutMapping(path = "/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Company> updateCompany(@RequestBody Company company,@PathVariable int id){
		for(int i = 0;i<companies.size();i++){
			if(companies.get(i).getId() == id){
				companies.set(i, company);
			}
		}
		return companies;
	}
	
	@DeleteMapping(path = "/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Company> deleteCompany(@PathVariable int id){
		for(Company company:companies){
			if(id == company.getId()){
				companies.remove(company);
			}
		}
		return companies;
	}
	

}
