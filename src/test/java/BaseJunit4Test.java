import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fp.domain.LoveType;
import com.fp.service.LoveTypeService;
import com.fp.service.UserService;

//import sun.net.www.protocol.gopher.GopherClient;

/**
 * Spring的单元测试
 * @author sys
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration
(locations = { "classpath*:applicationContext.xml", "classpath*:spring-context.xml" }) 
 //加载配置文件  
//-
public class BaseJunit4Test {
	@Resource
    ApplicationContext ac;
	
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sf);
		
	}
	@Test
	public void testBeans() throws Exception {
//		UserController userController = (UserController) ac.getBean("userController");
//		System.out.println(userController);
		
//		UserService userService = (UserService) ac.getBean("userServiceImpl");
//		System.out.println(userService);
//		String tea = "AAB" ;
//		char[] x = tea.toCharArray();
//		for (int i = 0; i < x.length; i++) {
//			for (int j = i+1; j < x.length; j++) {
//				if(x[i] == x[j]){
//					System.out.println(x[i]);
//					return;
//				}
//			}
//		}
		Random r = new Random();
		int i;
		List<Integer> list = new ArrayList<Integer>();
		while(list.size() < 10){
	            i = r.nextInt(28);
	            if(!list.contains(i)){
	                list.add(i);
	            }
	    }
		for (Integer integer : list) {
			System.out.print(integer+",");
		}
		 
	}
}
