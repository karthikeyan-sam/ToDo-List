package com.project.todo.list;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
		public TodoController(TodoService todoservice) {
			super();
			this.todoservice = todoservice;
		}
	private TodoService todoservice;

	@RequestMapping("list-todo")
	public String listalltodo(ModelMap model) {
		String username = (String)model.get("name");
		List<Todo> todos = todoservice.findbyusername(username);
		model.put("todos",todos);
		return "listtodo";
	}
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String shownewtodopage(ModelMap model) {
		String username = (String)model.get("name");
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addnewtodopage(ModelMap model, @Valid Todo todo, BindingResult res) {
		if(res.hasErrors()) {
			return "todo";
		}
		String username = (String)model.get("name");
		todoservice.addtodo(username, todo.getDescription(), todo.getLocaldate(), false);
		return "redirect:list-todo";
	}
	@RequestMapping("delete-todo")
	public String deletetodo(@RequestParam int id) {
		todoservice.deletebyid(id);
		return "redirect:list-todo";
	}
	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showupdatetodopage(@RequestParam int id, ModelMap model) {
		Todo todo = todoservice.findbyid(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String updatetodopage(ModelMap model, @Valid Todo todo, BindingResult res) {
		if(res.hasErrors()) {
			return "todo";
		}
		String username = (String)model.get("name");
		todo.setUsername(username);
		todoservice.updatetodo(todo);
		return "redirect:list-todo";
	}
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();

	}
	
}
