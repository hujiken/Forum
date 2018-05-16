package dao;

import java.util.List;

import entity.Account;

public interface AccountDao {

	public Account findById(int id);
	
	public void saveAccount(Account account);
	
	public void deleteAccountByUsername(String username);
	
	public List<Account> findAllAccount();
	
	public Account findAccountByUsername(String username);
	
	public Account findAccountByEmail(String email);
}
