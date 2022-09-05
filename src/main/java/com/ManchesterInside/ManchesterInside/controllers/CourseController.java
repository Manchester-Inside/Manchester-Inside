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
import com.ManchesterInside.ManchesterInside.entities.User;
import com.ManchesterInside.ManchesterInside.exceptions.CourseNotFoundException;
import com.ManchesterInside.ManchesterInside.services.CourseService;
import com.ManchesterInside.ManchesterInside.services.SchoolService;


@Controller
@RequestMapping(value = "/courses", produces = MediaType.TEXT_HTML_VALUE)
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private SchoolService schoolService;
	
	//TODO: Add not_found error
	@ExceptionHandler(CourseNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String CourseNotFoundHandler(CourseNotFoundException ex, Model model) {
		model.addAttribute("not_found_id", ex.getId());

		return "courses/not_found";
	}
	
	/* returns all posts as a list, under attribute "posts" of model */
	@GetMapping
	public String getPosts(Model model) {

		model.addAttribute("courses", courseService.findAll());

		return "courses/index";
	}
	
	/* returns course with given courseID, under attribute post. */
	@GetMapping("/{id}")
	public String getCourse(@PathVariable("id") long id,
			@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

		Course course = courseService.findById(id).orElseThrow(() -> new CourseNotFoundException(id));

		model.addAttribute("course", course);

		return "courses/viewcourse";
	}
	
	//TODO: Add /new, /update and /delete accordingly
	// For adding new event
	@GetMapping("/new")
	public String newPost(Model model) {
		// if model doesn't have course, initialize a new course
		if (!model.containsAttribute("course")) {
			model.addAttribute("course", new Course());
			model.addAttribute("school", schoolService.findAll());
		}
		// TODO: Implement categories

		return "courses/new";
		
	}
	
	// ADD NEW POST VIA FORM
	@PostMapping(value = "/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createCourse(@RequestBody @Valid @ModelAttribute Course course, BindingResult errors,
			Model model, RedirectAttributes redirectAttrs) {

		if (errors.hasErrors()) {
			model.addAttribute("course", course);
			model.addAttribute("school", schoolService.findAll());
			return "/courses/new";
		}
		// set author info and time info here
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = null;
		if (principal instanceof CustomUserDetails) {
			user = ((CustomUserDetails)principal).getUser();
		} 
		//TODO: Handle if there is no user logged in.

		
		// save post after automatically adding relevant meta info
		courseService.save(course);
		redirectAttrs.addFlashAttribute("ok_message", "New course added.");

		return "redirect:/courses";
	}	
	
	@DeleteMapping("/{id}")
	public String deleteCourse(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
		if (!courseService.existsById(id)) {
			throw new CourseNotFoundException(id);
		}

		courseService.deleteById(id);
		redirectAttrs.addFlashAttribute("ok_message", "course deleted.");

		return "redirect:/courses";
	}
	
	@DeleteMapping
	public String deleteAllcourses(RedirectAttributes redirectAttrs) {
		courseService.deleteAll();
		redirectAttrs.addFlashAttribute("ok_message", "All courses deleted.");

		return "redirect:/courses";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editEvent(@PathVariable("id") long id, Model model) {
		
		Optional<Course> course = courseService.findById(id);
		model.addAttribute("course", course);
		model.addAttribute("school", schoolService.findAll());

		return "courses/edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String updateEvent(@RequestBody @Valid @ModelAttribute Course course,
			BindingResult errors, Model model, RedirectAttributes redirectAttrs, String name, String time, String date, String venue) {
		
		if (errors.hasErrors()) {
			model.addAttribute("course", course);
			model.addAttribute("school", schoolService.findAll());
			return "/courses/new";
		}
		courseService.save(course);
		redirectAttrs.addFlashAttribute("ok_message", "course updated.");

		return "redirect:/courses";

	}


	
	
}
