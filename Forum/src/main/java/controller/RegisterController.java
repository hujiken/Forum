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

import dto.Register;
import service.AccountService;
import util.constant.DatabaseKey;

@Controller
public class RegisterController {
	
//	@Autowired
//	private AccountService accountService;
//	
//	@Autowired
//	private MessageSource messageSource;
//	
//	@RequestMapping(value = "/reg", method = RequestMethod.GET)
//	public String newAccount(ModelMap model) {
//		Register register = new Register();
//		model.addAttribute("register", register);
//		return "reg";
//	}
//	
//	@RequestMapping(value = "/reg", method = RequestMethod.POST)
//	public String saveAcount(@Valid Register register, BindingResult result, ModelMap model) {
//		System.out.println(register.toString());
//		if (result.hasErrors()) {
//			System.out.println(result.toString());
//			return "reg";
//		}
//		
//		if (accountService.isAccountUserNameExists(register.getUserName())) {
//			FieldError userNameError = new FieldError(DatabaseKey.ACCOUNT, DatabaseKey.Account.USER_NAME, 
//					messageSource.getMessage("exist.register.userName", new String[] {register.getUserName()}, Locale.getDefault()));
//			result.addError(userNameError);
//			return "reg";
//		}
//		
//		if (accountService.isAccountEmailExists(register.getEmail())) {
//			FieldError emailError = new FieldError(DatabaseKey.ACCOUNT, DatabaseKey.Account.EMAIL, 
//					messageSource.getMessage("exist.register.email", new String[] {register.getEmail()}, Locale.getDefault()));
//			result.addError(emailError);
//			return "reg";
//		}
//		
//		accountService.saveAccount(register.convertToAccount());
//		model.addAttribute(DatabaseKey.Account.USER_NAME, register.getUserName());
//		model.addAttribute(DatabaseKey.Account.EMAIL, register.getEmail());
//		return "success";
//	}
}
