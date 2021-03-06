package com.example.controller.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@RequestMapping(value = "/trang-chu",method = RequestMethod.GET)
	public ModelAndView homePage(){
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
	}

	@RequestMapping(value = "/dang-nhap",method = RequestMethod.GET)
	public ModelAndView loginPage(){
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null){
			new SecurityContextLogoutHandler().logout(request,response,authentication);
		}
		return "redirect:/trang-chu";
	}

	@RequestMapping(value = "/access-denied",method = RequestMethod.GET)
	public String accessDenied(){
		return "redirect:/dang-nhap?accessDenied";
	}

}
