package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zhuc.mybatis.service.FlowService;

import in.meile.account.service.UserService;


/**
 * @author zhuc
 * @create 2013-3-11 下午1:47:03
 */
public class MultiTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ac.getBean("userService");
		System.out.println(userService.get(1));
		System.out.println(userService.get2(1));

		FlowService flowService = (FlowService) ac.getBean("flowService");
		System.out.println(flowService.get("94003d29-a7b0-42f0-839c-fa609b209ff1"));

		//		User user = new User();
		//		user.setId(100);
		//		user.setUserName("admin100");
		//		user.setPassword("password100");
		//		user.setTrueName("小李4");
		//		user.setCreateTime(new Date());
		//
		//		userService.insert(user);	//受事务管理,抛出Exception时将回滚  (rollbackFor)
	}

}
