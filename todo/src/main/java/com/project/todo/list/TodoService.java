package com.project.todo.list;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	static int tc=0;
	static {
		todos.add(new Todo(++tc, "in28minutes","aws",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(++tc, "in28minutes","spring",LocalDate.now().plusYears(2),false));
		todos.add(new Todo(++tc, "in28minutes","aws-2",LocalDate.now().plusYears(3),false));
	}
	public List<Todo> findbyusername(String Username){
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(Username);
		return todos.stream().filter(predicate).toList();
	}
	public void addtodo(String username, String description,LocalDate localdate,boolean done) {
		Todo todo = new Todo(++tc,username,description,localdate,done);
		todos.add(todo);
	}
	public void deletebyid(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}
	public Todo findbyid(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	public void updatetodo(@Valid Todo todo) {
		deletebyid(todo.getId());
		todos.add(todo);
	}
}
