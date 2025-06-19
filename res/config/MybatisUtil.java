package config;

import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	
	private static SqlSessionFactory sessionFactory;
	
	static {
		try {
			//1-1. xml설정파일 읽어오기
			//설정파일의 인코딩 정보 설정하기(한글 깨짐 방지용)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("config/mybatis-oracle-config.xml");
			
			//Reader객체를 이용하여 SqlSessionFactory 객체 생성하기
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static SqlSession getInstance() {
		
		return sessionFactory.openSession();
	}

}
