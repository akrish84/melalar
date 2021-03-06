package app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return ("<h1> Hello! Please login as a user </h1>");
	}
	
	@GetMapping("/user")
	public String user() {
		return ("<h1> Hello! Welcome</h1>");
	}
	
	@GetMapping("/admin")
	public String admin() {
		return ("<h1> Master! Welcome </h1>");
	}
}
