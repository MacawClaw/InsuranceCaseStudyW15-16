package com.genspark.insuranceapp;

import com.genspark.insuranceapp.Service.UserInfoService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.genspark.insuranceapp.Entity.*;
import com.genspark.insuranceapp.Dao.*;

import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InsuranceAppApplicationTests {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private EntityManager entityManager;

	@Test
	void correctFindRoleId1() {
		Role role = roleDao.findRoleByName("ROLE_ADJUSTER");
		assertTrue(role.getName().equals("ROLE_ADJUSTER"));
		assertEquals(role.getId(), 1);
	}

	@Test
	void correctFindRoleId2() {
		Role role = roleDao.findRoleByName("ROLE_CLIENT");
		assertTrue(role.getName().equals("ROLE_CLIENT"));
		assertEquals(role.getId(), 2);
	}


	@Test
	void incorrectFindRole(){
		Role role = roleDao.findRoleByName("ROLE_ADMIN");
		assertNull(role);
	}

	@Test
	void correctFindUsername(){
		User user = userInfoService.findByUserName("adjuster44");
		assertTrue(user.getUsername().equals("adjuster44"));
		assertTrue(user.getPassword().equals("$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2"));
		assertEquals(user.getRoles().size(),2);
	}


	@Test
	void incorrectFindUsername(){
		User user = userInfoService.findByUserName("unknown");
		assertNull(user.getUsername());
	}
	@Test
	void saveAndDelete(){
		User user = new User();
		//user only expected to provide username and password
		user.setUsername("cscudworth");
		user.setPassword("butlertron");
		user.setEnabled(true);
		user.setEmail("me@test.com");
		user.setFirstName("Cinnamon");
		user.setLastName("Scudworth");
		user.setPhone("202-918-2132");
		user.setAddress("1 Hello World Rd. Exclamation,KS 60000");
		User userReturned = userInfoService.saveUser(user);
		assertNotNull(userReturned.getUsername());


		userInfoService.deleteByUsername("cscudworth");
		userReturned = userInfoService.findByUserName(user.getUsername());
		assertNull(userReturned.getUsername());

	}

	//test DOES NOT delete user created if an error happens halfway, have to delete it manually
	@Test
	void saveRetrieveAndDeleteCorrectUser(){
		User user = new User();
		//user only expected to provide username and password
		Random random = new Random();
		user.setUsername("csampson");
		user.setPassword("lizard");
		user.setEnabled(true);
		user.setEmail("me@test.com");
		user.setFirstName("Candide");
		user.setLastName("Sampson");
		user.setPhone("202-918-2132");
		user.setAddress("1 Hello World Rd. Exclamation,KS 60000");
		User userReturned = userInfoService.saveUser(user);
		assertNotNull(userReturned);

		userReturned = userInfoService.findByUserName(user.getUsername());
		assertTrue(userReturned.isEnabled());
		assertTrue(userReturned.getUsername().equals(user.getUsername()));
		assertTrue(userReturned.getPassword().equals(user.getPassword()));
		Iterator<Role> roleIterator = userReturned.getRoles().iterator();
		for(int i = 0; i < userReturned.getRoles().size(); i++){
			Role role = roleIterator.next();
			if(i == 0){
				assertEquals(role.getId(), 2);
				assertTrue(role.getName().equals("ROLE_CLIENT"));
			}
		}

		userInfoService.deleteByUsername(user.getUsername());
		userReturned = userInfoService.findByUserName(user.getUsername());
		assertNull(userReturned);

	}

	//test DOES NOT delete user created if an error happens halfway, have to delete it manually

	@Test
	void saveRetrieveAndDeleteCorrectAdjuster(){
		User user = new User();
		//user only expected to provide username and password
		Random random = new Random();
		user.setUsername("adjuster" + random.nextInt(100));
		user.setPassword("insurance");
		user.setEnabled(true);
		user.setEmail("me@test.com");
		user.setFirstName("Insurance");
		user.setLastName("Adjuster");
		user.setPhone("202-918-2132");
		user.setAddress("1 Hello World Rd. Exclamation,KS 60000");
		User userReturned = userInfoService.saveAdjuster(user);
		assertNotNull(userReturned);

		userReturned = userInfoService.findByUserName(user.getUsername());
		assertTrue(userReturned.isEnabled());
		assertTrue(userReturned.getUsername().equals(user.getUsername()));
		assertTrue(userReturned.getPassword().equals(user.getPassword()));
		Iterator<Role> roleIterator = userReturned.getRoles().iterator();
		for(int i = 0; i < userReturned.getRoles().size(); i++){
			Role role = roleIterator.next();
			if(i == 0){
				assertEquals(role.getId(), 1);
				assertTrue(role.getName().equals("ROLE_ADJUSTER"));
			} else if(i == 1){
				assertEquals(role.getId(), 2);
				assertTrue(role.getName().equals("ROLE_CLIENT"));
			}
		}

		userInfoService.deleteByUsername(user.getUsername());
		userReturned = userInfoService.findByUserName(user.getUsername());
		assertNull(userReturned.getUsername());

	}


}
