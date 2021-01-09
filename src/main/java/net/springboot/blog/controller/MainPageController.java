package net.springboot.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainPageController {

	//returns  main page of a website
	@GetMapping(URLS.homePage)
	public String getStartPage() {
		return "views/start-page";
	}

}