package com.example.springkadaiform.controller;


import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {
	
	@GetMapping("/form")
	public String contactForm(Model model) {
		
		if(!model.containsAttribute("contactForm")) {
		model.addAttribute("contactForm",new ContactForm());
		}
	return "contactFormView";

 }
	@PostMapping("/confirm")
	public String confirm(
			@Validated ContactForm form, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {
		
		if(result.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("contactForm", form);
			
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
					+ Conventions.getVariableName(form),result);
			
			
			return "redirect:/form";
		}
		
			model.addAttribute("contactForm", form);
			return "confirmView";	
		
	}		
}