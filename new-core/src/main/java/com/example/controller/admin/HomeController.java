package com.example.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value ="homeOfAdmin")
public class HomeController {

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView mav = new ModelAndView("admin/home");
		return mav;
	}
}
