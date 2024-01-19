package com.project.todo.list;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
	public Todo() {
	}
	public Todo(int id, String username, String description, LocalDate localdate, boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.localdate = localdate;
		this.done = done;
	}
	@Id
	@GeneratedValue
	private int id;
	private String username;
	@Size(min=10, message="enter atleast 10 character")
	private String description;
	private LocalDate localdate;
	private boolean done;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getLocaldate() {
		return localdate;
	}

	public void setLocaldate(LocalDate localdate) {
		this.localdate = localdate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", localdate=" + localdate
				+ ", done=" + done + "]";
	}



}
