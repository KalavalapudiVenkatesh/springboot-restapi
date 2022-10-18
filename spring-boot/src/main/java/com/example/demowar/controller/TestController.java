package com.example.demowar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demowar.controller.bean.PagedResponse;
import com.example.demowar.model.Users;
import com.example.demowar.service.TestService;

@RestController
@RequestMapping("/users")
public class TestController {
	
	
	@Autowired
	private Environment env;
	


	@Autowired
	private TestService testService;
	
	
	@RequestMapping(value = "/v1/testdb1", method = { RequestMethod.GET })
	public PagedResponse<Users> getListdb1() {

		PagedResponse<Users> response = null;

		try {
			response = testService.getListdb1();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		return response;

	}
	
	
	@RequestMapping(value = "/v1/testdb2", method = { RequestMethod.GET })
	public PagedResponse<Users> getListdb2() {

		PagedResponse<Users> response = null;

		try {
			response = testService.getListdb2();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		return response;

	}
	
	

}

