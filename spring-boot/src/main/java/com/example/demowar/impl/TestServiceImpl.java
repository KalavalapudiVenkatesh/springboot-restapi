package com.example.demowar.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demowar.controller.bean.PagedResponse;
import com.example.demowar.model.Users;
import com.example.demowar.service.TestService;
import com.google.gson.Gson;

@Service
public class TestServiceImpl implements TestService {

	@Qualifier("secondarysession")
	@Autowired
	private SessionFactory secondaryFactory;

	@Autowired
	private SessionFactory primaryFactory;

	private Session session = null;
	private String query = null;

	@Override
	public PagedResponse<Users> getListdb1() {

		String jsonString = "";
		
		
		PagedResponse<Users> pus=new PagedResponse<>();
		try {
			session = secondaryFactory.openSession();
			query = "FROM Users";
			System.out.println("SQL Query : " + query);
			List<Users> result = session.createQuery("from Users", Users.class).getResultList();

			Gson gson = new Gson();
			
			String usersData = gson.toJson(result);
			System.out.println("=======seonday factory  : " + usersData);
			
			pus.setContent(result);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return pus;
	}

	@Override
	public PagedResponse<Users> getListdb2() {
		PagedResponse<Users> pus=new PagedResponse<>();
		try {
			

			session = primaryFactory.getCurrentSession();
			query = "FROM Users";
			System.out.println("SQL Query : " + query);
			List<Users> result = session.createQuery("from Users", Users.class).getResultList();

			Gson gson = new Gson();

			String usersData = gson.toJson(result);
			System.out.println("========primary factory   : " + usersData);
			
			pus.setContent(result);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return pus;
	}

}
