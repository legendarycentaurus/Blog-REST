package com.nanda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nanda.exception.ServiceException;
import com.nanda.model.SeedCategory;
import com.nanda.model.User;
import com.nanda.service.SeedCategoryService;

@RestController
@RequestMapping("/category")
public class SeedCategoryController {


	@GetMapping
	public List<SeedCategory> index(){
		 SeedCategoryService scs=new SeedCategoryService();
		List<SeedCategory> list = scs.list();
		return list;
	}
	
	@GetMapping("/newCategory")
	public String newCategory(@RequestParam("categoryName") String categoryName, @RequestParam("userId") int userId
		) {
		 SeedCategoryService scs=new SeedCategoryService();
		 SeedCategory sc=new SeedCategory();
		sc.setCategory(categoryName);
		User user=new User();
		user.setId(userId);
		sc.setUserId(user);
		
		try {
			scs.save(sc);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "Inserted successfully";
	}
	@GetMapping("/update")
	public String update(@RequestParam("categoryName") String categoryName,@RequestParam("id") int id
		) {
		 SeedCategoryService scs=new SeedCategoryService();
		 SeedCategory sc=new SeedCategory();
		sc.setCategory(categoryName);
		sc.setId(id);
		
		try {
			scs.update(sc);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "update Success";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		 SeedCategoryService scs=new SeedCategoryService();
		 SeedCategory sc=new SeedCategory();
		sc.setId(id);
		try {
			scs.delete(sc);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "DELETED";
	}
	
}
