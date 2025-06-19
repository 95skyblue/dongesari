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
	
	/**
	 * @author 송태호
	 * @param loginId 클라이언트가 입력한 로그인용 아이디
	 * @return member 테이블에 해당 아이디가 있으면 true
	 * @exception DB 연결이 안되어있으면 에러 던질듯?
	 */
	public boolean checkIdFromMember(String loginId) {
		ss = ssf.openSession(true);
		try {
			int result = ss.selectOne("test.checkIdFromMember", loginId);
			return result == 1;
		} finally { ss.close(); }
	}
	
	/**
	 * @author 송태호
	 * @param loginId 클라이언트가 입력한 로그인용 아이디
	 * @return mem_quitted 테이블에 해당 아이디가 있으면 true
	 * @exception DB 연결이 안되어있으면 에러 던질듯?
	 */
	public boolean checkIdFromMemQuit(String loginId) {
		ss = ssf.openSession(true);
		try {
			int result = ss.selectOne("test.checkIdFromMemQuit", loginId);
			return result == 1;
		} finally { ss.close(); }
	}
	
	public boolean checkNickname(String nickname) {
		ss = ssf.openSession(true);
		try {
			int result = ss.selectOne("test.checkNickname", nickname);
			return result == 1;
		} finally { ss.close(); }
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