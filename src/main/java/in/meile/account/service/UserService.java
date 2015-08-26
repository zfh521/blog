package in.meile.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhuc.mybatis.service.BaseMySqlService;

import in.meile.account.domain.User;
import in.meile.account.mapper.UserMapper;

/**
 * @author zhuc
 * @create 2013-3-11 下午1:19:03
 */
@Service("userService")
//@Transactional(value = "isap", rollbackFor = Exception.class)
public class UserService extends BaseMySqlService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * @param id
	 * @return
	 */
	public User get(Integer id) {
		return userMapper.get(id);
	}

	/**
	 * @param id
	 * @return
	 */
	public User get2(Integer id) {
		return userMapper.get2(id);
	}

	/**
	 * @return
	 */
	public List<User> findAll() {
		return userMapper.findAll();
	}

	/**
	 * @param user
	 * @throws Exception 
	 */
	public void insert(User user) throws Exception {
		userMapper.insert(user);
		throw new Exception("testException");
	}
}
