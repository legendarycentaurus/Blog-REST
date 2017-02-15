package com.nanda.controller;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nanda.exception.ServiceException;
import com.nanda.model.Article;
import com.nanda.model.Rating;
import com.nanda.model.User;
import com.nanda.service.RatingService;



@RestController
@RequestMapping("/ratings")
public class RatingController {
	@GetMapping
	public List<Rating> list(@RequestParam("userId") int userId,ModelMap modelMap){
		RatingService ratingService=new RatingService();
		List<Rating> r=null;
		try {
			r = ratingService.list(userId);
		} catch (ServiceException e) {
			modelMap.addAttribute("ratingList",e.toString());
		}
		return r;
		
	}
	
	@PostMapping("/insertRatings")
	public String save(@RequestBody Rating r) {
		RatingService ratingService=new RatingService();
		Article a=new Article();
		a.setId(r.getArticleId().getId());
		r.setArticleId(a);
		User u=new User();
		u.setId(r.getUserId().getId());
		r.setUserId(u);
		r.setRating(r.getRating());
		try {
			ratingService.save(r);
		} catch (ServiceException e) {
			return "../comments.jsp";
		}
		return "Successfully inserted";
	}


	@GetMapping("/updateRatings")
	public String update(@RequestParam("ratings") int ratings,@RequestParam("id") int id)
	{
		Rating rating=new Rating();
		RatingService ratingService=new RatingService();
		rating.setId(id);
		rating.setRating(ratings);
		try {
			ratingService.update(rating);
		} catch (ServiceException e) {
			return "../ratings";
		}
		return "Successfully updated";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id)
	{
		Rating rating=new Rating();
		RatingService ratingService=new RatingService();
		rating.setId(id);
		try {
			ratingService.delete(rating);
		} catch (ServiceException e) {
			return "../ratings";
		}
		return "DELETED";
	}
}

