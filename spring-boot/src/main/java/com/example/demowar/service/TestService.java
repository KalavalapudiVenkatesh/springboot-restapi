package com.example.demowar.service;

import com.example.demowar.controller.bean.PagedResponse;
import com.example.demowar.model.Users;


public interface TestService {


	PagedResponse<Users> getListdb1();

	PagedResponse<Users> getListdb2();

	
}
