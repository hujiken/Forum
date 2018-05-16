package service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AccountDao;
import dto.Login;
import entity.Account;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	@Override
	public Account findById(int id) {
		return accountDao.findById(id);
	}

	@Override
	public void saveAccount(Account account) {
		accountDao.saveAccount(account);
	}

	@Override
	public void updateAccount(Account account) {
		Account entity = accountDao.findById(account.getId());
		if (entity != null) {
			entity.setEmail(account.getEmail());
			entity.setUsername(account.getUsername());
			entity.setPassword(account.getPassword());
		}
	}

	@Override
	public void deleteAccountByUsername(String username) {
		accountDao.deleteAccountByUsername(username);
	}

	@Override
	public List<Account> findAllAccount() {
		return accountDao.findAllAccount();
	}

	@Override
	public Account findAccountByUsername(String username) {
		return accountDao.findAccountByUsername(username);
	}

	@Override
	public Account findAccountByEmail(String email) {
		return accountDao.findAccountByEmail(email);
	}
	
	@Override
	public boolean isAccountUserNameExists(String username) {
		Account account = findAccountByUsername(username);
		return (account != null);
	}

	@Override
	public boolean isAccountEmailExists(String email) {
		Account account = findAccountByEmail(email);
		return (account != null);
	}

	@Override
	public boolean validateLogin(Login login) {
		return accountDao.findAccountByUsernameAndPassword(login.getUsername(), login.getPassword()) != null;
	}

	

}
