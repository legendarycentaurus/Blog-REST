package com.nanda.controller;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nanda.exception.ServiceException;
import com.nanda.model.Article;
import com.nanda.model.Comment;
import com.nanda.model.User;
import com.nanda.service.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentController {
	Comment c=new Comment();
	CommentService cs=new CommentService();
	@GetMapping("/listComments")
	public String index(ModelMap modelMap, @RequestParam("id") int id){
		List<Comment> list = cs.list(id);
		modelMap.addAttribute("Comments", list);
		return "../comments.jsp";
	}
	
	@GetMapping("/deleteComments")
	public String delete(@RequestParam("id") int id,ModelMap modelMap) {
		c.setId(id);
		
		try {
			cs.delete(c);
		} catch (ServiceException e) {
			modelMap.addAttribute("delete", "Unable to delete");
			return "../comments.jsp";
		}
		modelMap.addAttribute("delete", "successfully deleted");
		return "../comments.jsp";
	}
	@GetMapping("/insertComments")
	public String delete(@RequestParam("articleId") int articleId,@RequestParam("userId") int userId,@RequestParam("comments") String comments,ModelMap modelMap) {
		Article a=new Article();
		a.setId(articleId);
		c.setArticleId(a);
		User u=new User();
		u.setId(userId);
		c.setUserId(u);
		c.setComments(comments);
		try {
			cs.save(c);
		} catch (ServiceException e) {
			modelMap.addAttribute("delete", "Unable to Insert");
			return "../comments.jsp";
		}
		catch (EmailException e) {
			modelMap.addAttribute("delete", "Cannot send email ");
			return "../comments.jsp";
		}
		modelMap.addAttribute("delete", "successfully Inserted");
		return "../comments.jsp";
	}

}
