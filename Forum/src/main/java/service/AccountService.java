package service;

import java.util.List;

import dto.Login;
import entity.Account;

public interface AccountService {
	
	public Account findById(int id);
	
	public void saveAccount(Account account);
	
	public void updateAccount(Account account);
	
	public void deleteAccountByUsername(String username);
	
	public List<Account> findAllAccount();
	
	public Account findAccountByUsername(String username);
	
	public Account findAccountByEmail(String email);
	
	public boolean isAccountUserNameExists(String username);
	
	public boolean isAccountEmailExists(String email);
	
	public boolean validateLogin(Login login);
} 
