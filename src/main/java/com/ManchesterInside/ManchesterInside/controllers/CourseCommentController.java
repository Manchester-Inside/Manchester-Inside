package com.ManchesterInside.ManchesterInside.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ManchesterInside.ManchesterInside.config.userdetails.CustomUserDetails;
import com.ManchesterInside.ManchesterInside.entities.Course;
import com.ManchesterInside.ManchesterInside.entities.CourseComment;
import com.ManchesterInside.ManchesterInside.entities.User;
import com.ManchesterInside.ManchesterInside.exceptions.CourseNotFoundException;
import com.ManchesterInside.ManchesterInside.services.CourseCommentService;
import com.ManchesterInside.ManchesterInside.services.CourseService;
import com.ManchesterInside.ManchesterInside.services.SchoolService;


@Controller
@RequestMapping(value = "/comments", produces = MediaType.TEXT_HTML_VALUE)
public class CourseCommentController {
	
	@Autowired
	private CourseService courseService;
	
	
	@Autowired
	private CourseCommentService courseCommentService;
	
	/* add new comment via contents of form (form should pass comment object) */
	@PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addComment(@RequestBody @Valid @ModelAttribute CourseComment ccomment, BindingResult errors,
			@PathVariable("id") long id, Model model, RedirectAttributes redirectAttrs) {

		// return to original url if error
		if (errors.hasErrors()) {
			model.addAttribute("comment", ccomment);
			return "/courses/"+id;
		}
		// set author info and time info here
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = null;
		if (principal instanceof CustomUserDetails) {
			user = ((CustomUserDetails)principal).getUser();
		} 
		// get post from id given in url
		Course course = courseService.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
		
		//TODO: Handle if there is no user logged in.		
		ccomment.setUser(user);
		ccomment.setTimeUploaded(LocalDateTime.now());
		ccomment.setLastEdited(LocalDateTime.now());
		ccomment.setCourse(course);
		
		// add comment to post and save
		courseCommentService.save(ccomment);
		redirectAttrs.addFlashAttribute("ok_message", "New comment added.");

		// return to original url upon saving
		return "redirect:/courses/"+id;
	}
	
	//delete one comment
	@DeleteMapping("/{id}")
	public String deleteComment(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
		courseCommentService.deleteById(id);
		redirectAttrs.addFlashAttribute("ok_message", "Comment deleted.");
		return "redirect:/courses";
	}
	


	
	
}
