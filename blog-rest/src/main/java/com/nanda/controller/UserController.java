package com.nanda.controller;

import java.util.List;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nanda.exception.ServiceException;
import com.nanda.model.User;
import com.nanda.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService = new UserService();
	private User user=new User();
	@GetMapping
	public List<User> index(){
		System.out.println("UserController->index");
		List<User> list = userService.list();
		return list;
	}
	
	@PostMapping("/save")
	public void signup(@RequestBody User u) {
		
		try {
			userService.signup(u);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		catch (DuplicateKeyException e){
			e.printStackTrace();
		}
	}

	@PostMapping("/login")
	public User login(@RequestBody User u) {
		try {
			Integer value=userService.login(u);
			System.out.println(value);
			if(value!=0){
				return userService.listParticularUser(u.getName());
			}
			else{
				return u;
			}
		} catch (ServiceException e) {
			return u;
		}
	
	}
	
	
	@PostMapping("/update")
	public User update(@RequestBody User u) {
		
		
		try {
			System.out.println(userService.update(u));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		User list=userService.listParticularUser(u.getName());
		System.out.println(list);
		return list;
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("userId") int id) {
		try {
			user.setId(id);
			userService.delete(user);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return "Deleted";
	}
	

}
