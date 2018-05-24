package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dto.Register;
import entity.Account;
import service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping(value = "/list-all", consumes="application/json")
	public ResponseEntity<List<Account>>listAllAccounts() {
		List<Account> listAccounts = accountService.findAllAccount();
		if (listAccounts.isEmpty()) {
			return new ResponseEntity<List<Account>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Account>>(listAccounts, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/account/{id}", consumes="application/json")
	public ResponseEntity<Account>getAccount(@PathVariable("id") Integer id) {
		Account account = accountService.findById(id);
		if (account == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	@PostMapping(value = "/account", consumes="application/json")
	public ResponseEntity<Void>createAccount(@Valid @RequestBody Register register, UriComponentsBuilder ucBuilder, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		if (accountService.isAccountUserNameExists(register.getUserName()) 
				|| accountService.isAccountEmailExists(register.getEmail())) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		Account account = register.convertToAccount();
		accountService.saveAccount(account);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/account/{id}").buildAndExpand(account.getAccountId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "/account/{id}")
	public ResponseEntity<Account>updateAccount(@PathVariable ("id") Integer id, @RequestBody Register register) {
		
		Account account = accountService.findById(id);
		
		if (account == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		
		if ((accountService.isAccountUserNameExists(register.getUserName()) 
				&& (!account.getUserName().equals(register.getUserName())))
				|| (accountService.isAccountEmailExists(register.getEmail()) 
						&& (!account.getEmail().equals(register.getEmail())))) {
			return new ResponseEntity<Account>(HttpStatus.CONFLICT);
		}
		
		account.setUserName(register.getUserName());
		account.setEmail(register.getEmail());
		account.setPassword(register.getPassword());
		accountService.updateAccount(account);
		
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/account/{id}")
	public ResponseEntity<Account>deleteAccount(@PathVariable ("id") Integer id) {
		
		Account account = accountService.findById(id);
		
		if (account == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		
		accountService.deleteAccountByUsername(account.getUserName());
		return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/access-denied")
	public String accessDenied() {
		return "access_denied1";
	}
}
