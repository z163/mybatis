package com.kkb.mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.kkb.mybatis.po.OrdersExt;
import com.kkb.mybatis.po.User;
import com.kkb.mybatis.po.UserQueryVO;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	/**
	 * @Before注解的方法会在@Test注解的方法之前执行
	 * 
	 * @throws Exception
	 */
	@Before
	public void init() throws Exception {
		//指定全局配置文件路径
		String resource = "SqlMapConfig.xml";
		//加载资源文件（全局配置文件和映射文件）
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//还有构建者模式，去创建SqlSessionFactory对象
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream );
	}
	
	//和Spring整合之后，需要再IoC容器中去管理两个对象：
	//SqlSessionFactory对象/MapperFactoryBean/MapperScannerConfigurer
	@Test
	public void testFindUserById() throws Exception {
		//构造UserMapper对象（sqlSession）
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//需要传的参数就是被代理的Mapper接口
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//调用UserMapper对象的findUserById
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}

	// 根据用户名称模糊查询用户信息
	@Test
	public void testFindUserByUsername() {
		// 数据库会话实例
		SqlSession sqlSession = null;
		try {
			// 创建数据库会话实例sqlSession
			sqlSession = sqlSessionFactory.openSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 查询单个记录，根据用户id查询用户信息
			List<User> list = userMapper.findUserByUsername("小明");
			System.out.println("result size: " + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	//@Test
	//public void testInsert() {
	//	// 数据库会话实例
	//	SqlSession sqlSession = null;
	//	try {
	//		// 创建数据库会话实例sqlSession
	//		sqlSession = sqlSessionFactory.openSession();
	//		// 添加用户信息
	//		User user = new User();
	//		user.setUsername("张小明");
	//		user.setAddress("河南郑州");
	//		user.setSex("1");
	//		// user.setPrice(1999.9f);
	//		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	//		userMapper.insertUser(user);  // 1
	//		//提交事务
	//		sqlSession.commit();
	//		System.out.println("insertion is tested: " + user.getId());
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//	} finally {
	//		if (sqlSession != null) {
	//			sqlSession.close();
	//		}
	//	}
	//}

	// 根据id删除用户
	@Test
	public void testDelete() {
		// 数据库会话实例
		SqlSession sqlSession = null;
		try {
			// 创建数据库会话实例sqlSession
			sqlSession = sqlSessionFactory.openSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			// 删除用户
			userMapper.deleteUserById(41);
			// 提交事务
			sqlSession.commit();
			System.out.println("deletion is tested: " + 41);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	// 更新用户信息
	@Test
	public void testUpdate() {
		// 数据库会话实例
		SqlSession sqlSession = null;
		try {
			// 创建数据库会话实例sqlSession
			sqlSession = sqlSessionFactory.openSession();
			// 添加用户信息
			User user = new User();
			user.setId(26);
			user.setUsername("张z明");
			user.setAddress("河南郑州");
			user.setSex("1");
			// user.setPrice(1999.9f);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.updateUser(user);
			// 提交事务
			sqlSession.commit();
			System.out.println("update is tested");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testFindUserList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//获得mapper的代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		//创建QueryVo对象
		UserQueryVO queryVo = new UserQueryVO();
		//创建user对象
		User user = new User();
		user.setUsername("小明");

		queryVo.setUser(user);
		//根据queryvo查询用户
		List<User> list = userMapper.findUserList(queryVo);
		System.out.println(list);
		sqlSession.close();
	}


	@Test
	public void testFindOrdersList()throws Exception{
		//获取session
		SqlSession session = sqlSessionFactory.openSession();
		//获限mapper接口实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//查询订单信息
		List<OrdersExt> list = userMapper.findOrdersList();
		System.out.println("findOrdersList: " + list);
		//关闭session
		session.close();
	}

	@Test
	public void testfindOrdersAndUserRstMap()throws Exception{
		//获取session
		SqlSession session = sqlSessionFactory.openSession();
		//获限mapper接口实例
		UserMapper userMapper = session.getMapper(UserMapper.class);
		//查询订单信息
		List<OrdersExt> list = userMapper.findOrdersAndUserRstMap();
		System.out.println("findOrdersAndUsersRstMap" + list);
		//关闭session
		session.close();
	}
}
