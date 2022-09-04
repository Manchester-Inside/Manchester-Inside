package com.ManchesterInside.ManchesterInside.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ManchesterInside.ManchesterInside.config.userdetails.CustomUserDetails;
import com.ManchesterInside.ManchesterInside.entities.Post;
import com.ManchesterInside.ManchesterInside.entities.PostComment;
import com.ManchesterInside.ManchesterInside.entities.User;
import com.ManchesterInside.ManchesterInside.exceptions.PostNotFoundException;
import com.ManchesterInside.ManchesterInside.services.PostCommentService;
import com.ManchesterInside.ManchesterInside.services.PostService;

@Controller
@RequestMapping(value = "/comments", produces = MediaType.TEXT_HTML_VALUE)
public class PostCommentController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostCommentService postCommentService;
	
	/* add new comment via contents of form (form should pass pcomment object) */
	@PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String addComment(@RequestBody @Valid @ModelAttribute PostComment pcomment, BindingResult errors,
			@PathVariable("id") long id, Model model, RedirectAttributes redirectAttrs) {

		// return to original url if error
		if (errors.hasErrors()) {
			model.addAttribute("comment", pcomment);
			return "/posts/"+id;
		}
		// set author info and time info here
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = null;
		if (principal instanceof CustomUserDetails) {
			user = ((CustomUserDetails)principal).getUser();
		} 
		// get post from id given in url
		Post post = postService.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		
		//TODO: Handle if there is no user logged in.		
		pcomment.setUser(user);
		pcomment.setTimeUploaded(LocalDateTime.now());
		pcomment.setLastEdited(LocalDateTime.now());
		pcomment.setPost(post);
		
		// add comment to post and save
		//post.addPostComment(pcomment);
		postCommentService.save(pcomment);
//		postService.save(post);
		redirectAttrs.addFlashAttribute("ok_message", "New comment added.");

		// return to original url upon saving
		return "redirect:/posts/"+id;
	}
	
	//delete one comment
	@DeleteMapping("/{id}")
	public String deleteComment(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
		postCommentService.deleteById(id);
		redirectAttrs.addFlashAttribute("ok_message", "Comment deleted.");
		return "redirect:/posts";
	}
}
