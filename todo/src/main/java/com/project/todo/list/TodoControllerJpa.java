package com.project.todo.list;

import java.util.Map;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}

	private TodoRepository todoRepository;
	@RequestMapping("list-todo")
	public String listalltodo(ModelMap model) {
		String username = (String)model.get("name");
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos",todos);
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
		todo.setUsername(username);
		todoRepository.save(todo);
		return "redirect:list-todo";
	}
	@RequestMapping("delete-todo")
	public String deletetodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todo";
	}
	@RequestMapping("done-todo")
	public String donetodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todo";
	}
	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showupdatetodopage(@RequestParam int id, ModelMap model) {
		Todo todo = todoRepository.findById(id).get();
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
		todoRepository.save(todo);
		return "redirect:list-todo";
	}
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();

	}
	
}
