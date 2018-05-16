package controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.Login;
import service.AccountService;
import util.constant.DatabaseKey;

@Controller
public class LoginController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		Login login = new Login();
		model.addAttribute("login", login);
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid Login login, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "login";
		}
		
		if (accountService.validateLogin(login)) {
			model.addAttribute("username", login.getUsername());
			return "success";
		} else {
			model.addAttribute("invalid", "Invalid information");
			return "login";
		}
	}
}
