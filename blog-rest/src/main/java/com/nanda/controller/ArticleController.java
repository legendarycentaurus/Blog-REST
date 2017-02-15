package com.nanda.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nanda.exception.ServiceException;
import com.nanda.model.Article;
import com.nanda.service.ArticleService;

@Controller
@RequestMapping("/Article")
public class ArticleController {

	@GetMapping
	public String index(ModelMap modelMap){
		ArticleService articleService = new ArticleService();
		Article article=new Article();
		List<Article> list = articleService.list();
		modelMap.addAttribute("Article_LIST", list);
		return "articleList.jsp";
	}
	
	@GetMapping("/myArticles")
	public String myArticles(ModelMap modelMap,@RequestParam("id") int id) {
		ArticleService articleService = new ArticleService();
		Article article=new Article();
		List<Article> list=null;
		try {
			list = articleService.listMyArticle(id);
			System.out.println("nanda");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("Article_LIST", list);
		return "../myArticle.jsp";
	}
}
