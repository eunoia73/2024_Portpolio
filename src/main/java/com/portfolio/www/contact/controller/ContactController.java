package com.portfolio.www.contact.controller;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.www.contact.dto.ContactDto;
import com.portfolio.www.contact.service.ContactService;
import com.portfolio.www.message.MessageEnum;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/contact/contactPage.do")
	public ModelAndView contactPage(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		mv.setViewName("contact/contact");

		return mv;
	}
	
	//이메일 보내기 
	@RequestMapping("/contact/contact.do")
	public ModelAndView contact(@ModelAttribute ContactDto contactDto, Model model,RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", Calendar.getInstance().getTimeInMillis());
		
		System.out.println(contactDto.getContactName());
		System.out.println(contactDto.getContactEmail());
		
		int result = contactService.contact(contactDto);
		
		if(result == 1) {

			mv.addObject("resultCode",MessageEnum.SUCCESS.getCode());
			mv.addObject("resultMsg",MessageEnum.SUCCESS.getDescription());
		}else {
			mv.addObject("resultCode",MessageEnum.FAIL.getCode());
			mv.addObject("resultMsg",MessageEnum.FAIL.getDescription());

		}
		
		mv.setViewName("contact/contact");

		return mv;
	}
	
	//name 체크 
	@ResponseBody
	@GetMapping("/contact/nameCheck.do")
	public String nameCheck(@RequestParam("name") String name) {
				
		String result = contactService.nameCheck(name);
		
		return result;
	}
	
	//email 체크 
	@ResponseBody
	@GetMapping("/contact/emailCheck.do")
	public String emailCheck(@RequestParam("email") String email) {
				
		String result = contactService.emailCheck(email);
		
		return result;
	}
}
