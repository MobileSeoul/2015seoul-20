package mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class PoolManager {
	private static PoolManager instance;
	String resource = "mybatis/config/mybatis-config.xml";
	
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	private PoolManager() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public static PoolManager getInstance() {
		if(instance == null) {
			instance = new PoolManager();
		}
		return instance;
	}
	
	public SqlSession getSession() {
		SqlSession session =null;
		session = sqlSessionFactory.openSession();
		return session;
	}
}
