package com.nanda.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nanda.exception.ServiceException;
import com.nanda.model.ArticleCategory;
import com.nanda.service.ArticleCategoryService;

@RestController
@RequestMapping("/articleCategory")
public class ArticleCategoryController {

	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		ArticleCategory articleCategory=new ArticleCategory();
		ArticleCategoryService acs=new ArticleCategoryService();
		articleCategory.setId(id);
		
		try {
			acs.delete(articleCategory);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return "DELETED";
	}
}
