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

import entity.Account;
import service.AccountService;
import util.constant.DatabaseKey;

@Controller
public class RegisterController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String newAccount(ModelMap model) {
		Account account = new Account();
		model.addAttribute(DatabaseKey.ACCOUNT, account);
		return "reg";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public String saveAcount(@Valid Account account, BindingResult result, ModelMap model) {
		System.out.println(account.toString());
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "reg";
		}
		
		if (accountService.isAccountUserNameExists(account.getUsername())) {
			FieldError userNameError = new FieldError(DatabaseKey.ACCOUNT, DatabaseKey.Account.USER_NAME, 
					messageSource.getMessage("exist.account.username", new String[] {account.getUsername()}, Locale.getDefault()));
			result.addError(userNameError);
			return "reg";
		}
		
		if (accountService.isAccountEmailExists(account.getEmail())) {
			FieldError emailError = new FieldError(DatabaseKey.ACCOUNT, DatabaseKey.Account.EMAIL, 
					messageSource.getMessage("exist.account.email", new String[] {account.getEmail()}, Locale.getDefault()));
			result.addError(emailError);
			return "reg";
		}
		
		accountService.saveAccount(account);
		model.addAttribute(DatabaseKey.Account.USER_NAME, account.getUsername());
		model.addAttribute(DatabaseKey.Account.EMAIL, account.getEmail());
		return "success";
	}
}
