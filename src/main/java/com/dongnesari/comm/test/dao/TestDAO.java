package com.dongnesari.comm.test.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dongnesari.comm.test.dto.LoginDTO;
import com.dongnesari.comm.test.dto.SessionDTO;

public class TestDAO {
	private SqlSessionFactory ssf; // SqlSessionFactory는 생성자에서 초기화
	private SqlSession ss;         // SqlSession은 메서드 실행 시점에 생성

	private static TestDAO instance;

	// 생성 시 초기화
	private TestDAO() {
		try {
			Charset cs = Charset.forName("UTF-8");
			Resources.setCharset(cs);
			Reader reader = Resources.getResourceAsReader("config/mybatis-oracle-config.xml");
//			Reader reader = Resources.getResourceAsReader("config/mybatis-mysql-config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 싱글톤 구현
	public static TestDAO getInstance() {
		if (instance == null) instance = new TestDAO();
		return instance;
	}
	
	// 로그인 시도 메서드. 성공하면 LoginSessionDTO를 반환
	// 로그인 실패하면 loginFail, 내부 오류면 loginError 코드 반환
	public SessionDTO login(LoginDTO dto) {
		ss = ssf.openSession(true);
		try {
			SessionDTO sessionDTO = ss.selectOne("test.login", dto);
			if(sessionDTO == null) throw new IllegalArgumentException("loginFail");
			else return sessionDTO;
		} finally { ss.close(); }
	}
}