package com.project.todo.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	@ResponseBody
	@RequestMapping("say-hello")
	public String sayhello() {
		return "hi! hello";
	}
	@RequestMapping("say-hello-jsp")
	public String sayHelloJSP() {
		return "sayHello";
	}
}
