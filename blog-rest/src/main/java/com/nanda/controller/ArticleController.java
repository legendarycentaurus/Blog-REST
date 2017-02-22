package com.nanda.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nanda.exception.ServiceException;
import com.nanda.model.Article;
import com.nanda.service.ArticleService;

@RestController
@RequestMapping("/Article")
public class ArticleController {

	@GetMapping
	public List<Article> index(){
		ArticleService articleService = new ArticleService();
		List<Article> list = articleService.list();
		return list;
	}
	
	@GetMapping("/myArticles")
	public List<Article> myArticles(@RequestParam("id") int id) {
		ArticleService articleService = new ArticleService();
		List<Article> list=null;
		try {
			list = articleService.listMyArticle(id);
			System.out.println("nanda");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
