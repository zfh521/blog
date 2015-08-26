package in.meile.account.mapper;


import in.meile.account.domain.Account;


public interface AccountMapper {

	public Account get(Long id);
	public void insert(Account account);
	public void updateAccountStatus(Account account);
	public Account getByName(Account account);
	public Account getByMail(String email);

}
