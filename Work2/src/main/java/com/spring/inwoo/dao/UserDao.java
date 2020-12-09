package com.spring.inwoo.dao;

import java.util.HashMap;
import java.util.List;

import com.spring.inwoo.vo.UserVo;

/*
	@Repository
		DAO(Data Access Object)에서 발생하는 예외를,
		스프링의 DataAccessException으로 처리하겠다.
 */

public interface UserDao {
	
	// 사용자 등록
	public void insert(UserVo vo);
	
	// 중복 이메일 체크
	public int getByEmail(String email);
	
	// 인증(로그인), 사용자 정의 예외 발생
	public UserVo getByEmailAndPassword(HashMap<String, String> map);
		
	
	// 사용자 정보 가져오기
	public UserVo getByNo(Long no);
	
	// 이름으로 사용자 정보 가져오기
	public List<UserVo> getByName(String name);
	
	// 사용자 정보 수정
	public void update(UserVo vo);
}









