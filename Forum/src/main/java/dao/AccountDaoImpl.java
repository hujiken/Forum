package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import entity.Account;
import util.constant.DatabaseKey;

@SuppressWarnings("deprecation")
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

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void deleteAccountByUsername(String username) {
		Query query = getSession().createSQLQuery("delete from account where user_name = :username");
		query.setString("username", username);
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
		System.out.println(criteria.toString());
		System.out.println(criteria.uniqueResult());
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
