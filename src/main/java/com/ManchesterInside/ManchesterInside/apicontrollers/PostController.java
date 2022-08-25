package com.ManchesterInside.ManchesterInside.apicontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ManchesterInside.ManchesterInside.entities.Post;
import com.ManchesterInside.ManchesterInside.exceptions.PostNotFoundException;
import com.ManchesterInside.ManchesterInside.services.PostService;

@Controller
@RequestMapping(value = "/posts", produces = MediaType.TEXT_HTML_VALUE)
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//TODO: Add not_found error
	@ExceptionHandler(PostNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String eventNotFoundHandler(PostNotFoundException ex, Model model) {
		model.addAttribute("not_found_id", ex.getId());

		return "posts/not_found";
	}
	
	/* returns all posts as a list, under attribute "posts" of model */
	@GetMapping
	public String list(Model model) {

		model.addAttribute("posts", postService.findAll());

		return "posts/index";
	}
	
	/* returns post with given postID, under attribute post. */
	@GetMapping("/{id}")
	public String greeting(@PathVariable("id") long id,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

		Post post = postService.findById(id).orElseThrow(() -> new PostNotFoundException(id));

		model.addAttribute("post", post);

		return "/viewpost";
	}
	
	//TODO: Add /new, /update and /delete accordingly
	
}
