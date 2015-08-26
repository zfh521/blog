package in.meile.account.mapper;

import java.util.List;

import in.meile.account.domain.User;

/**
 * @author zhuc
 * @create 2013-3-11 下午1:16:55
 */
public interface UserMapper {

	/**
	 * @param id
	 * @return
	 */
	public User get(Integer id);

	/**
	 * @param id
	 * @return
	 */
	public User get2(Integer id);

	/**
	 * @return
	 */
	public List<User> findAll();

	/**
	 * @param user
	 */
	public void insert(User user);
}
