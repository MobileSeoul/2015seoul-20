package mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	private static MybatisManager instance;
	String resource = "mybatis/config/mybatis-config.xml";
	
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	private MybatisManager() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static MybatisManager getInstance() {
		if(instance == null) {
			instance = new MybatisManager();
		}
		return instance;
	}
	
	public SqlSession openSession() {
		SqlSession session =null;
		session = sqlSessionFactory.openSession();
		return session;
	}
}
