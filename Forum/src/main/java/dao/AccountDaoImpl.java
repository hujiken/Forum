package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import entity.Account;
import util.constant.DatabaseKey;

@Repository("accountDao")
public class AccountDaoImpl extends AbstractDao<Integer, Account> implements AccountDao {

	@Override
	public Account findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveAccount(Account account) {
		persist(account);
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Override
	public void deleteAccountByUsername(String username) {
		Query query = getSession().createSQLQuery("delete from Account where username = :username");
		query.setString(username, username);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAllAccount() {
		Criteria criteria = createEntityCriteria();
		return (List<Account>)criteria.list();
	}

	@Override
	public Account findAccountByUsername(String username) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(DatabaseKey.Account.USER_NAME, username));
		return (Account) criteria.uniqueResult();
	}

	@Override
	public Account findAccountByEmail(String email) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(DatabaseKey.Account.EMAIL, email));
		return (Account) criteria.uniqueResult();
	}

	@Override
	public Account findAccountByUsernameAndPassword(String username, String password) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq(DatabaseKey.Account.USER_NAME, username));
		criteria.add(Restrictions.eq(DatabaseKey.Account.PASSWORD, password));
		return (Account) criteria.uniqueResult();
	}

}
